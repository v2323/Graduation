package ru.javaops.graduation.web.dish;

import ru.javaops.graduation.model.Dish;
import ru.javaops.graduation.web.MatcherFactory;

import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int PEPPERONI_ID = 1;
    public static final int BORSCH_ID = 15;
    public static final int DISH_NOT_FOUND = 100;

    public static final Dish pepperoni = new Dish(PEPPERONI_ID, "Pepperoni", 25.5);
    public static final Dish russianMenu = new Dish(BORSCH_ID, "Borsch", 25.5);

    public static final List<Dish> dishes = List.of(
            new Dish(1, "Pepperoni", 25.5),
            new Dish(2, "Margarita", 12.5),
            new Dish(3, "Pasta", 17.5),
            new Dish(4, "Salad", 40.5),
            new Dish(5, "Four seasons", 32.5),
            new Dish(6, "Four cheeses", 11.5),
            new Dish(7, "Calzone", 27.5),
            new Dish(8, "Philadelphia", 25.5),
            new Dish(9, "California", 12.5),
            new Dish(10, "Ramen soup", 17.5),
            new Dish(11, "Sushi", 40.5),
            new Dish(12, "Sashimi", 32.5),
            new Dish(13, "Maki", 11.5),
            new Dish(14, "Dragon roll", 27.5),
            new Dish(15, "Borsch", 25.5),
            new Dish(16, "Ukha", 12.5),
            new Dish(17, "Pelmeni", 17.5),
            new Dish(18, "Beef Stroganoff", 40.5),
            new Dish(19, "Pancakes", 32.5),
            new Dish(20, "Goulash", 11.5),
            new Dish(21, "Solyanka", 27.5)
    );

    public static Dish getNewDish() {
        return new Dish(null, "Bolognese", 14.7);
    }

    public static Dish getUpdatedDish() {
        return new Dish(PEPPERONI_ID, "Lasagna", 31.0);
    }
}
