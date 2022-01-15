package ru.javaops.graduation.web.restaurant;

import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.model.Role;
import ru.javaops.graduation.model.User;
import ru.javaops.graduation.util.JsonUtil;
import ru.javaops.graduation.web.AbstractControllerTest;
import ru.javaops.graduation.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static MatcherFactory.Matcher<Restaurant> RESTAURANTS_WITH_MENUS_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("menus").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int ITALIAN_RESTAURANT_ID = 1;
    public static final int RUSSIAN_RESTAURANT_ID = 3;
    public static final int NOT_FOUND = 100;

    public static final Restaurant italianRestaurant = new Restaurant(ITALIAN_RESTAURANT_ID,"Italian Pizza", "Small homelike Italian restaurant");
    public static final Restaurant russianRestaurant = new Restaurant(RUSSIAN_RESTAURANT_ID,"Russian Borsch", "Restaurant of traditional Russian food");

    public static Restaurant getNew() {
        return new Restaurant(null, "Irish pub", "Irish pub with good bear and live music");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(ITALIAN_RESTAURANT_ID, "newName", "newDescription");
    }

}
