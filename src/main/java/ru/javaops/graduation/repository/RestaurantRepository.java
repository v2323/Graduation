package ru.javaops.graduation.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    Optional<Restaurant> getById(int id);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Restaurant> findByIdAndMenus_DayOfWeak(int restId, String day);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Restaurant> findAllByMenus_DayOfWeak(String day);

}
