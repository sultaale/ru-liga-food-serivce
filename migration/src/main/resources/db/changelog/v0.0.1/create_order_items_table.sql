
CREATE TABLE IF NOT EXISTS order_items
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    order_id bigint NOT NULL,
    restaurant_menu_item_id bigint NOT NULL,
    price bigint,
    quantity integer,
    CONSTRAINT order_items_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_order_items_order" FOREIGN KEY (order_id)
    REFERENCES orders (id),
    CONSTRAINT "FK_order_items_restaurant" FOREIGN KEY (restaurant_menu_item_id)
    REFERENCES restaurant_menu_items (id)
    );

    comment on table order_items is 'Детали заказа';
    comment on column order_items.id is 'Индентификатор деталей заказа';
    comment on column order_items.order_id is 'Индентификатор заказа';
    comment on column order_items.restaurant_menu_item_id is 'Индентификатор меню';
    comment on column order_items.price is 'Стоимость заказа';
    comment on column order_items.quantity is 'Количество блюд';

