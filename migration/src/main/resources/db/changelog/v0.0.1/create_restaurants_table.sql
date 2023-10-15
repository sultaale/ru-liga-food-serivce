CREATE TABLE IF NOT EXISTS restaurants
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    address character varying(255) NOT NULL,
    status character varying(15) NOT NULL,
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
    );

    comment on table restaurants is 'Данные ресторана';
    comment on column restaurants.id is 'Индентификатор ресторана';
    comment on column restaurants.address is 'Адрес ресторана';
    comment on column restaurants.status is 'Статус заказа';