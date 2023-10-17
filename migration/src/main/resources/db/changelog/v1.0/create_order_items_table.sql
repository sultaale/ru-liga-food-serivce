create sequence if not exists order_items_seq;

CREATE TABLE IF NOT EXISTS order_items
(
    id bigint NOT NULL default nextval('order_items_seq'),
    order_id bigint NOT NULL,
    restaurant_menu_item_id bigint NOT NULL UNIQUE,
    price numeric default 0.00,
    quantity integer default 0,
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

