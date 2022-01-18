create table address
(
    id        serial
        constraint address_pk
            primary key,
    city      text    not null,
    street    text    not null,
    house     integer not null,
    apartment integer not null
);

alter table address
    owner to postgres;

create table orders
(
    id         serial
        constraint orders_pk
            primary key,
    status     text    not null,
    address_id integer not null
        constraint orders_address_id_fk
            references address
            on delete cascade,
    user_id    integer not null
        constraint orders_users_id_fk
            references users,
    cart       jsonb   not null,
    date       date    not null
);

alter table orders
    owner to postgres;

create unique index orders_id_uindex
    on orders (id);

INSERT INTO public.address (id, city, street, house, apartment) VALUES (7, 'г. Тверь', 'ул. 2-я Серова', 33, 33);
INSERT INTO public.address (id, city, street, house, apartment) VALUES (8, 'hfhgdf', 'dg', 1212, 121);

INSERT INTO public.orders (id, status, address_id, user_id, cart, date) VALUES (1, 'new', 7, 3, '[{"id": 9, "price": 123, "title": "Латук", "categoryDto": [1]}, {"id": 22, "price": 2324, "title": "Абрикос", "categoryDto": [2]}]', '2021-10-12');
INSERT INTO public.orders (id, status, address_id, user_id, cart, date) VALUES (2, 'new', 8, 3, '[{"id": 22, "price": 2324, "title": "Абрикос", "categoryDto": [2]}, {"id": 6, "price": 180, "title": "Виноград", "categoryDto": [2]}]', '2021-10-12');

