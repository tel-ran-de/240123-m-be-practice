create table if not exists account_entity
(
    id         uuid primary key,
    first_name text not null,
    last_name  text not null
);

create table if not exists wallet_entity
(
    id            uuid primary key,
    currency_code text not null,
    account_id    uuid not null,
    balance       text not null,
    FOREIGN KEY (account_id) references account_entity (id)
);