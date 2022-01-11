INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS (name, description)
VALUES ('Italian Pizza', 'Small homelike Italian restaurant'),
       ('Japanese Sushi', 'Restaurant specializing in the cooking of sushi and rolls'),
       ('Russian Borsch', 'Restaurant of traditional Russian food');

INSERT INTO MENUS (day_of_weak, rest_id)
VALUES ('MONDAY', 1),
       ('TUESDAY', 1),
       ('WEDNESDAY', 1),
       ('THURSDAY', 1),
       ('FRIDAY', 1),
       ('SATURDAY', 1),
       ('SUNDAY', 1),
       ('MONDAY', 2),
       ('TUESDAY', 2),
       ('WEDNESDAY', 2),
       ('THURSDAY', 2),
       ('FRIDAY', 2),
       ('SATURDAY', 2),
       ('SUNDAY', 2),
       ('MONDAY', 3),
       ('TUESDAY', 3),
       ('WEDNESDAY', 3),
       ('THURSDAY', 3),
       ('FRIDAY', 3),
       ('SATURDAY', 3),
       ('SUNDAY', 3);

INSERT INTO DISHES (name, price, menu_id)
VALUES ('Pepperoni', 25.5, 1),
       ('Margarita', 12.5, 2),
       ('Pasta', 17.5, 3),
       ('Salad', 40.5, 4),
       ('Four seasons', 32.5, 5),
       ('Four cheeses', 11.5, 6),
       ('Calzone', 27.5, 7),
       ('Pepperoni', 25.5, 8),
       ('Margarita', 12.5, 9),
       ('Pasta', 17.5, 10),
       ('Salad', 40.5, 11),
       ('Four seasons', 32.5, 12),
       ('Four cheeses', 11.5, 13),
       ('Calzone', 27.5, 14),
       ('Pepperoni', 25.5, 15),
       ('Margarita', 12.5, 16),
       ('Pasta', 17.5, 17),
       ('Salad', 40.5, 18),
       ('Four seasons', 32.5, 19),
       ('Four cheeses', 11.5, 20),
       ('Calzone', 27.5, 21);
