package ru.javaops.graduation.web.dish;

import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.model.Menu;
import ru.javaops.graduation.model.Restaurant;
import ru.javaops.graduation.web.MatcherFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int PEPPERONI_ID = 1;
    public static final int BORSCH_ID = 15;
    public static final int NOT_FOUND = 100;

    public static final Dish pepperoni = new Dish(PEPPERONI_ID,"Pepperoni", 25.5);
    public static final Dish russianMenu = new Dish(BORSCH_ID,"Borsch", 25.5);

    public static Dish getNew() {
        return new Dish (null,"Bolognese",14.7);
    }

    public static Dish getUpdated() {
        return new Dish(PEPPERONI_ID, "Lasagna",31.0);
    }
}
