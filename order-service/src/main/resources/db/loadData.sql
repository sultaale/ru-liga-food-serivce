INSERT INTO couriers(
    phone, status, coordinates)
VALUES ('89027802557', 'DELIVERY_PENDING', '56.3070,43.9836'),
       ('89038492398', 'DELIVERY_COMPLETE', '56.3129,43.9931'),
       ('89068369528', 'DELIVERY_PENDING', '56.2971,43.9847'),
       ('89049872294', 'DELIVERY_PENDING', '56.320183,44.031364'),
       ('89108378924', 'DELIVERY_PENDING', '56.333824,43.852204');

INSERT INTO customers(
    phone, email, address)
VALUES ('89027107834', 'mail@mail.com', '56.328818,44.012957'),
       ('89049472720', 'myemail@bk.ru', '56.31551,44.060478'),
       ('89047830275', 'writeme@yandex.ru' ,'56.335865,43.936349'),
       ('89059387592', 'lenka@yandex.ru', '56.278483,43.923557'),
       ('89037486419', 'darkside@gmail.com', '56.369706,43.800569');

INSERT INTO restaurants(name, address, status)
VALUES ('Теремок', '56.3157,43.992287', 'KITCHEN_ACCEPTED'),
       ('Адель', '56.298667,43.937274', 'KITCHEN_PREPARING'),
       ('Поесть бы', '56.296034,44.066021', 'KITCHEN_DENIED');

INSERT INTO restaurant_menu_items(
    restaurant_id, name, price, image, description)
VALUES (1, 'Супер бургер', 563.00, '/picture_1', 'Сочный бургер с говядиной'),
       (2, 'Суп с лисичками', 365.00, '/picture_2', 'Легкий суп с лисичками'),
       (3, 'Салат с курицей', 479.00, '/picture_4', 'Салат с куриной грудкой с соусом'),
       (1, 'Салат с телячьим мясом', 890.00, '/picture_3', 'Салат с подкопченным мясом теленка'),
       (2, 'Копченная стерлядь', 1300.00, '/picture_5', 'Копченная стерлядь с картофелем');