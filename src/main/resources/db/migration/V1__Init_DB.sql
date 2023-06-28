drop table if exists address;

create table address(
    id           bigserial not null,
    city_name    varchar(255),
    country_name varchar(255),
    postal_code  varchar(255),
    state_name   varchar(255),
    street_name  varchar(255),
    primary key (id));
create table basket(
    id                    bigserial not null,
    discounted_difference integer,
    end_sum               integer,
    quantity_of_products  integer,
    sum                   integer,
    user_id               bigint,
    primary key (id));

create table categories(
    id     bigserial not null,
    name   varchar(255),
    subcat varchar(255),
    primary key (id));

create table delivery_men(
    id           bigserial not null,
    last_name    varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    primary key (id));

create table discounts(
    id             bigserial not null,
    date_of_finish date,
    date_of_start  date,
    percent        integer   not null,
    primary key (id));

create table feedbacks(
    id                 bigserial not null,
    admin_replay       varchar(255),
    created_at         timestamp(6),
    feedback           varchar(255),
    media              varchar(255),
    product_evaluation smallint  not null,
    product_id         bigint,
    user_id            bigint,
    primary key (id));

create table news(
    id             bigserial not null,
    date_of_finish date,
    date_of_start  date,
    primary key (id));

create table order_list(
    id bigserial not null,
    primary key (id));

create table orders(
    id               bigserial not null,
    count_of_product smallint,
    order_status     varchar(255),
    shipping         varchar(255),
    total_sum        integer,
    type_payment     varchar(255),
    delivery_man_id  bigint,
    user_id          bigint,
    primary key (id)
);
create table payments(
    id             bigserial not null,
    amount         float4,
    created_date   date,
    payment_method varchar(255),
    primary key (id)
);
create table products(
    id                   bigserial not null,
    pdf                  varchar(2500),
    appointment          varchar(255),
    brand                varchar(255),
    capacity_battery     varchar(255),
    color                varchar(255),
    cpu                  varchar(255),
    current_price        integer,
    date_of_issue        varchar(255),
    description          varchar(255),
    display_inch         varchar(255),
    guarantee            varchar(255),
    image                varchar(255),
    name                 varchar(255),
    os                   varchar(255),
    price                integer   not null,
    quantity_of_products bigint,
    quantity_of_sim      bigint,
    ram                  varchar(255),
    rom                  varchar(255),
    sim                  varchar(255),
    weight               varchar(255),
    basket_id            bigint,
    category_id          bigint,
    discount_id          bigint,
    news_id              bigint,
    order_id             bigint,
    order_list_id        bigint,
    promotion_id         bigint,
    wishlist_id          bigint,
    primary key (id)
);
create table promotions(
    id             bigserial not null,
    date_of_finish date,
    date_of_start  date,
    primary key (id)
);
create table users(
    id            bigserial not null,
    created_date  date,
    email         varchar(255),
    first_name    varchar(255),
    last_name     varchar(255),
    password      varchar(255),
    phone_number  varchar(255),
    role          varchar(255),
    address_id    bigint,
    basket_id     bigint,
    order_list_id bigint,
    payments_id   bigint,
    wishlist_id   bigint,
    primary key (id)
);
create table wishlist(
    id      bigserial not null,
    user_id bigint,
    primary key (id)
);
