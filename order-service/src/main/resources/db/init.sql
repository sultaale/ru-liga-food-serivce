create sequence if not exists couriers_seq;

CREATE TABLE IF NOT EXISTS couriers
(
    id bigint NOT NULL default nextval('couriers_seq'),
    phone character varying(15) NOT NULL,
    status character varying(25) NOT NULL,
    coordinates character varying(25) NOT NULL,
    CONSTRAINT couriers_pkey PRIMARY KEY (id)
);

comment on table couriers is '������ ��������';
comment on column couriers.id is '�������������� �������';
comment on column couriers.phone is '������� ������';
comment on column couriers.status is '������ ��������';
comment on column couriers.coordinates is '���������� �������';

create sequence if not exists customers_seq;

CREATE TABLE IF NOT EXISTS customers
(
    id bigint NOT NULL default nextval('customers_seq'),
    phone character varying(15) NOT NULL,
    email character varying(50) NOT NULL,
    address character varying(25) NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

comment on table customers is '������ ���������';
comment on column customers.id is '�������������� ���������';
comment on column customers.phone is '������� ���������';
comment on column customers.email is '���������� ���������';

create sequence if not exists restaurants_seq;

CREATE TABLE IF NOT EXISTS restaurants
(
    id bigint NOT NULL default nextval('restaurants_seq'),
    name character varying(255) NOT NULL,
    address character varying(25) NOT NULL,
    status character varying(25),
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
);

comment on table restaurants is '������ ���������';
comment on column restaurants.id is '�������������� ���������';
comment on column restaurants.address is '���������� ���������';
comment on column restaurants.status is '������ ������';

CREATE TABLE IF NOT EXISTS orders
(
    id uuid NOT NULL,
    customer_id bigint NOT NULL,
    restaurant_id bigint NOT NULL,
    courier_id bigint default NULL,
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

comment on table orders is '���������� � ������';
comment on column orders.id is '�������������� ������';
comment on column orders.customer_id is '�������������� ���������';
comment on column orders.restaurant_id is '�������������� ���������';
comment on column orders.courier_id is '�������������� �������';
comment on column orders.status is '������ ������';
comment on column orders.timestamp is '���� �������� ������';

create sequence if not exists restaurant_menu_items_seq;

CREATE TABLE IF NOT EXISTS restaurant_menu_items
(
    id bigint NOT NULL default nextval('restaurant_menu_items_seq'),
    restaurant_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(10,2) default 0.00,
    image character varying(255),
    description character varying(255) NOT NULL,
    CONSTRAINT restaurant_menu_items_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_restaurant_restaurant_menu" FOREIGN KEY (restaurant_id)
        REFERENCES restaurants (id)
);

comment on table restaurant_menu_items is '���� ���������';
comment on column restaurant_menu_items.id is '�������������� ���� ���������';
comment on column restaurant_menu_items.restaurant_id is '�������������� ���������';
comment on column restaurant_menu_items.name is '�������� �����';
comment on column restaurant_menu_items.price is '���� �����';
comment on column restaurant_menu_items.image is '����� ��������';
comment on column restaurant_menu_items.description is '�������� �����';

create sequence if not exists order_items_seq;

CREATE TABLE IF NOT EXISTS order_items
(
    id bigint NOT NULL default nextval('order_items_seq'),
    order_id uuid NOT NULL,
    restaurant_menu_item_id bigint NOT NULL,
    price numeric(10,2) default 0.00,
    quantity integer default 0,
    CONSTRAINT order_items_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_order_items_order" FOREIGN KEY (order_id)
        REFERENCES orders (id),
    CONSTRAINT "FK_order_items_restaurant" FOREIGN KEY (restaurant_menu_item_id)
        REFERENCES restaurant_menu_items (id)
);

comment on table order_items is '������ ������';
comment on column order_items.id is '�������������� ������� ������';
comment on column order_items.order_id is '�������������� ������';
comment on column order_items.restaurant_menu_item_id is '�������������� ����';
comment on column order_items.price is '��������� ������';
comment on column order_items.quantity is '���������� ����';