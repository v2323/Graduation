package ru.javaops.graduation.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.repository.RestaurantRepository;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.javaops.graduation.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.graduation.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "restaurants")
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    @Autowired
    protected RestaurantRepository repository;

    @GetMapping("/{id}")
    public Optional<Restaurant> get(@PathVariable int id) {
        String today = LocalDateTime.now().getDayOfWeek().name();
        return repository.findByIdAndMenus_DayOfWeak(id, today);
    }

    @GetMapping()
    @Cacheable
    public List<Restaurant> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id", "name"));
    }

    @GetMapping("/withMenu")
    @Cacheable
    public List<Restaurant> getAllWithMenus() {
        String today = LocalDateTime.now().getDayOfWeek().name();
        return repository.findAllByMenus_DayOfWeak(today);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteExisted(id);
    }

    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }
}

