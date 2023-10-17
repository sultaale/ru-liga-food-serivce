create sequence if not exists restaurants_seq;

CREATE TABLE IF NOT EXISTS restaurants
(
    id bigint NOT NULL default nextval('restaurants_seq'),
    address character varying(255) NOT NULL,
    status character varying(15) default 'active',
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
    );

    comment on table restaurants is 'Данные ресторана';
    comment on column restaurants.id is 'Индентификатор ресторана';
    comment on column restaurants.address is 'Адрес ресторана';
    comment on column restaurants.status is 'Статус заказа';