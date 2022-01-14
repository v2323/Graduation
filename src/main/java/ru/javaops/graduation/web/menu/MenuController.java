package ru.javaops.graduation.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.graduation.model.Menu;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.repository.MenuRepository;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.web.restaurant.RestaurantController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ru.javaops.graduation.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.graduation.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "menus")
public class MenuController {

    static final String REST_URL = "/api/menu";

    @Autowired
    protected MenuRepository repository;

    @GetMapping("/{id}")
    public Optional<Menu> get(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/day/{dayOfWeak}")
    public Optional<Menu> get(@PathVariable String dayOfWeak) {
        return repository.findByDayOfWeak(dayOfWeak);
    }

    @GetMapping("/withDishes/{restId}")
    public List<Menu> getAllWithDishes(@PathVariable int restId) {
        return repository.findAllByRestaurant_id(restId);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.delete(id);
    }

    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu) {
        log.info("create {}", menu);
        checkNew(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(menu.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(menu);
    }

    @PutMapping(value = "admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id) {
        log.info("update {} with id={}", menu, id);
        assureIdConsistent(menu, id);
        repository.save(menu);
    }
}
