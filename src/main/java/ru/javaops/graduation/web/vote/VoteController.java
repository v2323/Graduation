package ru.javaops.graduation.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.repository.VoteRepository;
import ru.javaops.graduation.util.UserUtil;
import ru.javaops.graduation.web.SecurityUtil;
import ru.javaops.graduation.web.restaurant.RestaurantController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ru.javaops.graduation.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.graduation.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "restaurants")
public class VoteController {
    static final String REST_URL = "/api/votes";

    @Autowired
    protected VoteRepository repository;

    @GetMapping("/{id}")
    public Optional<Vote> get(@PathVariable int id) {
        int authUserId = SecurityUtil.authId();
        return repository.findByRestaurant_IdAndUserId(id, authUserId);
    }

//    @GetMapping("/withMenu")
//    public List<Restaurant> getAllWithMenus() {
//        String today = "TUESDAY";
//        return repository.findAllByMenus_DayOfWeak(today);
//    }

//    @DeleteMapping("/admin/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int id) {
//        repository.delete(id);
//    }
//
    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @CacheEvict(allEntries = true)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote) {
        log.info("create {}", vote);
        checkNew(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
//
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @CacheEvict(allEntries = true)
//    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
//        log.info("update {} with id={}", restaurant, id);
//        assureIdConsistent(restaurant, id);
//        repository.save(restaurant);
//    }
}
