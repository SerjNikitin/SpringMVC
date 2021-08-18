create table market.product
(
    id serial
        constraint product_pk
            primary key,
    title varchar not null,
    price int not null
)