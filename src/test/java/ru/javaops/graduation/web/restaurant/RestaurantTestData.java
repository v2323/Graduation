package ru.javaops.graduation.web.restaurant;

import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.web.MatcherFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static MatcherFactory.Matcher<Restaurant> RESTAURANTS_WITH_MENUS_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("menus.restaurant", "menus").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANTS_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus");

    public static final int ITALIAN_RESTAURANT_ID = 1;
    public static final int JAPANESE_RESTAURANT_ID = 2;
    public static final int RUSSIAN_RESTAURANT_ID = 3;
    public static final int RESTAURANT_NOT_FOUND = 100;

    public static final Restaurant italianRestaurant = new Restaurant(ITALIAN_RESTAURANT_ID, "Italian Pizza", "Small homelike Italian restaurant");
    public static final Restaurant japaneseRestaurant = new Restaurant(JAPANESE_RESTAURANT_ID, "Japanese Sushi", "Restaurant specializing in the cooking of sushi and rolls");
    public static final Restaurant russianRestaurant = new Restaurant(RUSSIAN_RESTAURANT_ID, "Russian Borsch", "Restaurant of traditional Russian food");

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "Irish pub", "Irish pub with good bear and live music");
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(ITALIAN_RESTAURANT_ID, "newName", "newDescription");
    }

}
