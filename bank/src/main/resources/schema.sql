create table if not exists account_entity
(
    id         uuid primary key,
    first_name text not null,
    last_name  text not null
);
