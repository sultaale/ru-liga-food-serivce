CREATE TABLE IF NOT EXISTS restaurant_menu_items
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    restaurant_id bigint NOT NULL,
    name character varying(255),
    price bigint,
    image character varying(255),
    description character varying(255),
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