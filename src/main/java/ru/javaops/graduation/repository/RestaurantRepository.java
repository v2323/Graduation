package ru.javaops.graduation.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    Optional<Restaurant> getByName(String name);

    Optional<Restaurant> getById(int id);

//    @Query("SELECT r.name, r.description, r.menus FROM  Restaurant r left join Menu m on r.id=m.restaurant.id left join Dish d on m.id=d.menu.id WHERE m.dayOfWeak = LOWER(:today)")
//    Optional<Restaurant> getWithDayOfWeak(String today);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Restaurant> findByIdAndMenus_DayOfWeak(int restId, String day);
//    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
//    Optional<Restaurant> getWithMenus(int id, String day);
//    Optional<Restaurant> getWithMenus(int id);

    //    @Query("SELECT r.name, r.description, r.menus FROM Restaurant r left join Menu m on r.id=m.restaurant.id left join Dish d on m.id=d.menu.id WHERE m.dayOfWeak = LOWER(:today)")
//    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
//    Optional<Restaurant> findByIdAndMenus_DayOfWeakAndMenus_Dishes_Name(int restId, String day, String name);
//    Optional<Restaurant> findByIdAndMenus_DayOfWeak(int restId, String day);
}
