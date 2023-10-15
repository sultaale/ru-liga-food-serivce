
CREATE TABLE IF NOT EXISTS customers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    phone character varying(15) NOT NULL,
    email character varying(50) NOT NULL,
    address character varying(255) NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
    );

    comment on table customers is 'Данные заказчика';
    comment on column customers.id is 'Индентификатор заказчика';
    comment on column customers.phone is 'Телефон заказчика';
    comment on column customers.email is 'Адрес заказчика';