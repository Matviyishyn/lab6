create table if not exists user_metadata
(
    id           bigserial primary key,
    email        varchar(50) unique not null,
    password     varchar(30) unique not null,
    is_verified  boolean            not null,
    access_level varchar(50)        not null
);

create table if not exists customer
(
    id               bigserial primary key,
    status           varchar(50) not null,
    user_metadata_id bigserial   not null,
    foreign key (user_metadata_id) references user_metadata (id) on delete cascade
);

create table if not exists customer_identity
(
    id          bigserial primary key,
    customer_id bigserial   not null,
    name        varchar(50) not null,
    surname     varchar(50) not null,
    phone       varchar(50) not null,
    address     varchar(50) not null,
    foreign key (customer_id) references customer (id) on delete cascade
);

create table if not exists country
(
    id    bigserial primary key,
    label varchar(50) not null unique
);

create table if not exists statistic
(
    id bigserial primary key
);

create table if not exists shop
(
    id               bigserial primary key,
    status           varchar(50)  default 'INACTIVE' not null,
    country_id       bigserial,
    statistic_id     bigserial                       not null,
    brand            varchar(100) default 'unknown'  not null,
    shop_description varchar(300) default ''         not null,
    user_metadata_id bigserial                       not null,
    foreign key (user_metadata_id) references user_metadata (id) on delete cascade,
    foreign key (country_id) references country (id),
    foreign key (statistic_id) references statistic (id)
);

create table if not exists loyalty_shop
(
    bonus_id bigserial unique not null,
    shop_id  bigserial,
    foreign key (shop_id) references shop (id) on delete cascade
);

create table if not exists orders
(
    id          bigserial primary key   not null,
    customer_id bigserial               not null,
    receipt     varchar(1000) default 0 not null,
    foreign key (customer_id) references customer (id)
);

create table if not exists item_category
(
    id   bigserial primary key not null,
    name varchar(50) unique    not null
);

create table if not exists chocolate_type
(
    id    bigserial primary key,
    label varchar(50) unique
);

create table if not exists component_info
(
    id    bigserial primary key,
    label varchar(50)
);

create table if not exists sweet
(
    id                bigserial primary key,
    sugar             double precision     default 0.0,
    brand             varchar(20) not null default '',
    chocolate_type_id bigserial   not null,
    foreign key (chocolate_type_id) references chocolate_type (id)
);

create table if not exists components
(
    sweet_id          bigserial not null,
    component_info_id bigserial not null,
    foreign key (sweet_id) references sweet (id) on delete cascade,
    foreign key (component_info_id) references component_info (id)
);

create table if not exists item_common_data
(
    id           bigserial primary key          not null,
    shop_id      bigserial                      not null,
    category_id  bigserial                      not null,
    price        double precision default 0.0   not null,
    weight       double precision default 0.0,
    is_available boolean          default false not null,
    label        varchar(100)     default ''    not null,
    description  varchar(300)     default '',
    foreign key (shop_id) references shop (id) on delete cascade,
    foreign key (category_id) references item_category (id) on delete set default
);

create table if not exists box
(
    id           bigserial primary key,
    color_code   int              default '000000' not null,
    scale        double precision default 0.0      not null,
    width        double precision default 0.0      not null,
    length       double precision default 0.0      not null,
    color        varchar(50)      default 'black'  not null,
    item_data_id bigserial                         not null,
    foreign key (item_data_id) references item_common_data (id) on delete cascade
);

create table if not exists chocolate
(
    id           bigserial                      not null,
    sweet_id     bigserial                      not null,
    item_data_id bigserial                      not null,
    is_candy     boolean          default false not null,
    amount       double precision default 0.0   not null,
    priceByOne   double precision default 0.0   not null,
    foreign key (sweet_id) references sweet (id) on delete cascade,
    foreign key (item_data_id) references item_common_data (id) on delete cascade
);

create table if not exists cookies
(
    id           bigserial                    not null,
    sweet_id     bigserial                    not null,
    item_data_id bigserial                    not null,
    amount       double precision default 0.0 not null,
    priceByOne   double precision default 0.0 not null,
    form         varchar(100)                 not null,
    foreign key (sweet_id) references sweet (id) on delete cascade,
    foreign key (item_data_id) references item_common_data (id) on delete cascade
);