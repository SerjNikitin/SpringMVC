create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    enabled  boolean default true,
    primary key (id)
);

create table role
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE user_role
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references role (id)
);

insert into role (name)
values ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

INSERT INTO public.users (id, username, password, email, enabled) VALUES (2, 'ggg', '$2a$08$2RO4rUMaLLW4z4aFpSqchelNnPgJp4T/1OAvEvALpkDbQvD7eavxe', 'ggg@ggg', true);
INSERT INTO public.users (id, username, password, email, enabled) VALUES (3, 'fff', '$2a$08$JYwknbmooZXl5a.Yb8ABbeNGMX/sKyIe0PgvwN9fYilcYeGzPLLD.', 'fff@fff', true);
INSERT INTO public.users (id, username, password, email, enabled) VALUES (5, 'rrr', '$2a$08$OstLi7a0.S0FC1nERZsC8uuU0pbGQPWRVmkel.DFaNWUcm08KeWOy', 'rrr@rrr', true);

INSERT INTO public.user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO public.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (5, 1);

