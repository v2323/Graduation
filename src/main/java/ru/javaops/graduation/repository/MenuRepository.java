package ru.javaops.graduation.repository;

import ru.javaops.graduation.model.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends BaseRepository<Menu> {
    List<Menu> findByDayOfWeak(String dayOfWeak);
}
