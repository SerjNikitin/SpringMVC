
CREATE TABLE IF NOT EXISTS product
(
    id       bigserial primary key not null,
    title    text                  not null,
    price    integer               not null,
    image    text
);

CREATE TABLE IF NOT EXISTS category
(
    id        bigserial primary key not null,
    title     text                  not null,
    parent_id int,
    foreign key (parent_id) references category (id)
);

create index category_parent_id_idx on category (parent_id);


CREATE TABLE IF NOT EXISTS product_category
(
    product_id  bigint not null,
    category_id bigint not null,

    primary key (product_id, category_id),
    foreign key (product_id) references product (id),
    foreign key (category_id) references category (id)
);

INSERT INTO public.category (id, title, parent_id) VALUES (1, 'Овощи', null);
INSERT INTO public.category (id, title, parent_id) VALUES (2, 'Фрукты', null);
INSERT INTO public.category (id, title, parent_id) VALUES (3, 'Рыба с/с', null);
INSERT INTO public.category (id, title, parent_id) VALUES (4, 'Колбасы с/к', null);

INSERT INTO public.product (id, title, price, image) VALUES (5, 'Фейхоа', 180, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (6, 'Виноград', 180, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (8, 'Перец красный', 189, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (9, 'Латук', 123, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (12, 'Томаты', 232, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (14, 'Абрикос', 232, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (15, 'Персики', 232, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (17, 'Шампиньоны', 212, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (18, 'Огурцы', 343, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');
INSERT INTO public.product (id, title, price, image) VALUES (19, 'Лосось с/с', 1500, '\data\images\product\e495ff3eb9c55bd0bcdde0f468b679f4.jpg');

INSERT INTO public.product_category (product_id, category_id) VALUES (5, 2);
INSERT INTO public.product_category (product_id, category_id) VALUES (6, 2);
INSERT INTO public.product_category (product_id, category_id) VALUES (8, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (9, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (12, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (14, 2);
INSERT INTO public.product_category (product_id, category_id) VALUES (15, 2);
INSERT INTO public.product_category (product_id, category_id) VALUES (17, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (18, 1);
INSERT INTO public.product_category (product_id, category_id) VALUES (19, 3);
