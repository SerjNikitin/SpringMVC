create table market.product
(
    id          serial
        constraint product_pk
            primary key,
    title       text    not null,
    price       integer not null,
    category_id integer not null
        constraint product_category_id_fk
            references market.category
);

alter table market.product
    owner to postgres;


create table market.category
(
    id    serial
        constraint category_pk
            primary key,
    title text not null
);

alter table market.category
    owner to postgres;

