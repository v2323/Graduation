package ru.javaops.graduation.repository;

import ru.javaops.graduation.model.Menu;
import ru.javaops.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends BaseRepository<Menu>{
    Optional<Menu> findByDayOfWeak(String dayOfWeak);

    List<Menu> findAllByRestaurant_id(int restId);
}
