create sequence if not exists couriers_seq;

CREATE TABLE IF NOT EXISTS couriers
(
    id bigint NOT NULL default nextval('couriers_seq'),
    phone character varying(14) NOT NULL,
    status character varying(15) NOT NULL,
    coordinates point NOT NULL,
    CONSTRAINT couriers_pkey PRIMARY KEY (id)
    );

    comment on table couriers is 'Данные курьеров';
    comment on column couriers.id is 'Индентификатор курьера';
    comment on column couriers.phone is 'Телефон курьер';
    comment on column couriers.status is 'Статус доставки';
    comment on column couriers.coordinates is 'Координаты курьера';