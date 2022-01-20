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
            new Dish(2, "Bruschetta", 28.5),
            new Dish(3, "Margarita", 12.5),
            new Dish(4, "Pasta", 17.5),
            new Dish(5, "Salad", 40.5),
            new Dish(6, "Four seasons", 32.5),
            new Dish(7, "Four cheeses", 11.5),
            new Dish(8, "Calzone", 27.5),
            new Dish(9, "Philadelphia", 25.5),
            new Dish(10, "California", 12.5),
            new Dish(11, "Ramen soup", 17.5),
            new Dish(12, "Sushi", 40.5),
            new Dish(13, "Sashimi", 32.5),
            new Dish(14, "Maki", 11.5),
            new Dish(15, "Dragon roll", 27.5),
            new Dish(16, "Borsch", 25.5),
            new Dish(17, "Salade Olivier", 21.5),
            new Dish(18, "Ukha", 12.5),
            new Dish(19, "Pelmeni", 17.5),
            new Dish(20, "Beef Stroganoff", 40.5),
            new Dish(21, "Pancakes", 32.5),
            new Dish(22, "Goulash", 11.5),
            new Dish(23, "Solyanka", 27.5)
    );

    public static Dish getNewDish() {
        return new Dish(null, "Bolognese", 14.7);
    }

    public static Dish getUpdatedDish() {
        return new Dish(PEPPERONI_ID, "Lasagna", 31.0);
    }
}
