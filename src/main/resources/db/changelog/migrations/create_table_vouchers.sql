--liquibase formatted sql

--changeset eugene:vouchers_create
create table vouchers (
    id UUID primary key not null,
    name varchar(16) not null,
    description varchar(128),
    price bigint not null,
    destination_country varchar(32) not null,
    destination_region varchar(32)
);