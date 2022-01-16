INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@yandex.ru', '{noop}password2');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);

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
       ('Philadelphia', 25.5, 8),
       ('California', 12.5, 9),
       ('Ramen soup', 17.5, 10),
       ('Sushi', 40.5, 11),
       ('Sashimi', 32.5, 12),
       ('Maki', 11.5, 13),
       ('Dragon roll', 27.5, 14),
       ('Borsch', 25.5, 15),
       ('Ukha', 12.5, 16),
       ('Pelmeni', 17.5, 17),
       ('Beef Stroganoff', 40.5, 18),
       ('Pancakes', 32.5, 19),
       ('Goulash', 11.5, 20),
       ('Solyanka', 27.5, 21);

INSERT INTO VOTES (rest_id, user_id, vote_date)
VALUES (1, 1, '2022-01-14'),
       (2, 2, '2022-01-13');
