package ru.javaops.graduation.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.model.User;
import ru.javaops.graduation.repository.RestaurantRepository;
import ru.javaops.graduation.repository.UserRepository;
import ru.javaops.graduation.web.user.AdminUserController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    @Autowired
    protected RestaurantRepository repository;

    @GetMapping("/{id}")
    public Optional<Restaurant> get(@PathVariable int id) {
//        String today = LocalDate.now().getDayOfWeek().name();
        String today = "TUESDAY";
        int menuId=2;
        return repository.findByIdAndMenus_DayOfWeak(id, today);
//        return repository.getWithMenus(id, today);
//        return repository.getWithMenus(id);
//        return repository.getWithMenus(id,menuId);
    }

//    @GetMapping("/{name}")
//    public Optional<Restaurant> getByName(@PathVariable String name) {
//        return repository.getByName(name);
//    }

//    @GetMapping
//    public List<Restaurant> getAll() {
//        log.info("getAll");
//        return repository.findAll();
//    }

//    @GetMapping
//    public Optional<Restaurant> getAll() {
//        log.info("getAll");
//        String today = "TUESDAY";
//        return repository.getWithMenus(id,today);
//    }
}
