
CREATE TABLE IF NOT EXISTS couriers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    phone character varying(14) NOT NULL,
    status character varying(15) NOT NULL,
    coordinates point NOT NULL,
    CONSTRAINT couriers_pkey PRIMARY KEY (id)
    );

    comment on table couriers is '������ ��������';
    comment on column couriers.id is '�������������� �������';
    comment on column couriers.phone is '������� ������';
    comment on column couriers.status is '������ ��������';
    comment on column couriers.coordinates is '���������� �������';