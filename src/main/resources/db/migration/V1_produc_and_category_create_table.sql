create table product
(
    count_product integer default 0 not null,
    id            bigserial
        constraint product_pkey
            primary key,
    title         text              not null,
    price         integer           not null,
    image         text
);

alter table product
    owner to postgres;

INSERT INTO public.product (id, title, price, image, count_product) VALUES (5, 'Фейхоа', 180, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg', 0);
INSERT INTO public.product (id, title, price, image, count_product) VALUES (17, 'Шампиньоны', 212, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg', 0);
INSERT INTO public.product (id, title, price, image, count_product) VALUES (10, 'Цукини', 155, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg', 0);
INSERT INTO public.product (id, title, price, image, count_product) VALUES (15, 'Персики', 232, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg', 6);
INSERT INTO public.product (id, title, price, image, count_product) VALUES (18, 'Огурцы', 343, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg', 12);
INSERT INTO public.product (id, title, price, image, count_product) VALUES (12, 'Томаты', 232, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg', 6);

create table category
(
    id        bigserial
        constraint category_pkey
            primary key,
    title     text not null,
    parent_id integer
        constraint category_parent_id_fkey
            references category
);

alter table category
    owner to postgres;

create index category_parent_id_idx
    on category (parent_id);

INSERT INTO public.category (id, title, parent_id) VALUES (1, 'Овощи', null);
INSERT INTO public.category (id, title, parent_id) VALUES (2, 'Фрукты', null);
INSERT INTO public.category (id, title, parent_id) VALUES (3, 'Рыба с/с', null);
INSERT INTO public.category (id, title, parent_id) VALUES (4, 'Колбасы с/к', null);

create table product_category
(
    product_id  bigint not null
        constraint product_category_product_id_fkey
            references product,
    category_id bigint not null
        constraint product_category_category_id_fkey
            references category,
    constraint product_category_pkey
        primary key (product_id, category_id)
);

alter table product_category
    owner to postgres;

INSERT INTO public.product_category (product_id, category_id) VALUES (5, 2);
INSERT INTO public.product_category (product_id, category_id) VALUES (12, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (15, 2);
INSERT INTO public.product_category (product_id, category_id) VALUES (17, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (18, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (10, 1);
