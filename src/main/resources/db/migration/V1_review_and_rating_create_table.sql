create table product_review
(
    id         bigserial
        constraint review_pkey
            primary key,
    review     text,
    rating     int,
    product_id bigint not null,
    user_id    bigint not null,
    date_create date,
    foreign key (user_id) references users (id),
    foreign key (product_id) references product (id)

);
create index reviews_and_order_fk_user_idx
    on product_review (user_id);
create index reviews_and_order_fk_product_idx
    on product_review (product_id);
