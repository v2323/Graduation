package ru.javaops.graduation.web.menu;

import ru.javaops.graduation.model.Menu;
import ru.javaops.graduation.web.MatcherFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    public static MatcherFactory.Matcher<Menu> MENUS_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("restaurant", "dishes").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final MatcherFactory.Matcher<Menu> MENUS_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    public static final int ITALIAN_MENU_ID = 1;
    public static final int RUSSIAN_MENU_ID = 8;
    public static final int MENU_NOT_FOUND = 100;

    public static final Menu italianMenu = new Menu(ITALIAN_MENU_ID, "MONDAY");
    public static final Menu russianMenu = new Menu(RUSSIAN_MENU_ID, "MONDAY");

    public static final List<Menu> menus = List.of(
            new Menu(1, "MONDAY"),
            new Menu(2, "TUESDAY"),
            new Menu(3, "WEDNESDAY"),
            new Menu(4, "THURSDAY"),
            new Menu(5, "FRIDAY"),
            new Menu(6, "SATURDAY"),
            new Menu(7, "SUNDAY"),
            new Menu(8, "MONDAY"),
            new Menu(9, "TUESDAY"),
            new Menu(10, "WEDNESDAY"),
            new Menu(11, "THURSDAY"),
            new Menu(12, "FRIDAY"),
            new Menu(13, "SATURDAY"),
            new Menu(14, "SUNDAY"),
            new Menu(15, "MONDAY"),
            new Menu(16, "TUESDAY"),
            new Menu(17, "WEDNESDAY"),
            new Menu(18, "THURSDAY"),
            new Menu(19, "FRIDAY"),
            new Menu(20, "SATURDAY"),
            new Menu(21, "SUNDAY"));

    public static Menu getNewMenu() {
        return new Menu(null, "FRIDAY");
    }

    public static Menu getUpdatedMenu() {
        return new Menu(ITALIAN_MENU_ID, "SUNDAY");
    }
}
