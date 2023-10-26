create sequence if not exists orders_seq;

CREATE TABLE IF NOT EXISTS orders
(
    id bigint NOT NULL default nextval('orders_seq'),
    customer_id bigint NOT NULL,
    restaurant_id bigint NOT NULL,
    courier_id bigint NOT NULL,
    status character varying(25) default 'CUSTOMER_CREATED',
    "timestamp" timestamp with time zone default now(),
                              CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_orders_courier" FOREIGN KEY (courier_id)
    REFERENCES couriers (id),
    CONSTRAINT "FK_orders_customer" FOREIGN KEY (customer_id)
    REFERENCES customers (id),
    CONSTRAINT "FK_orders_restaurant" FOREIGN KEY (restaurant_id)
    REFERENCES restaurants (id)
    );

    comment on table orders is 'Информация о заказе';
    comment on column orders.id is 'Индентификатор заказа';
    comment on column orders.customer_id is 'Индентификатор заказчика';
    comment on column orders.restaurant_id is 'Индентификатор ресторана';
    comment on column orders.courier_id is 'Индентификатор курьера';
    comment on column orders.status is 'Статус заказа';
    comment on column orders.timestamp is 'Дата создания заказа';
