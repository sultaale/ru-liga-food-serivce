
CREATE TABLE IF NOT EXISTS couriers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
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