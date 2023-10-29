create sequence if not exists customers_seq;

CREATE TABLE IF NOT EXISTS customers
(
    id bigint NOT NULL default nextval('customers_seq'),
    phone character varying(15) NOT NULL,
    email character varying(50) NOT NULL,
    address character varying(25) NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
    );

    comment on table customers is 'Данные заказчика';
    comment on column customers.id is 'Индентификатор заказчика';
    comment on column customers.phone is 'Телефон заказчика';
    comment on column customers.email is 'Адрес заказчика';