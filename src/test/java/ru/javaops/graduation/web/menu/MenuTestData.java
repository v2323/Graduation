package ru.javaops.graduation.web.menu;

import ru.javaops.graduation.model.Menu;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.web.MatcherFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    public static MatcherFactory.Matcher<Restaurant> MENUS_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("restaurant","dishes.menu").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int ITALIAN_MENU_ID = 1;
    public static final int RUSSIAN_MENU_ID = 8;
    public static final int NOT_FOUND = 100;

    public static final Menu italianMenu = new Menu(ITALIAN_MENU_ID,"MONDAY");
    public static final Menu russianMenu = new Menu(RUSSIAN_MENU_ID,"MONDAY");

    public static Menu getNew() {
        return new Menu (null,"FRIDAY");
    }

    public static Menu getUpdated() {
        return new Menu(ITALIAN_MENU_ID, "SUNDAY");
    }
}
