DROP TABLE IF EXISTS `member` CASCADE;
DROP TABLE IF EXISTS `member_roles` CASCADE;

CREATE TABLE `member`
(
    member_id       bigint   not null,
    email           varchar  not null,
    member_name     varchar  not null,
    password        varchar,
    phone           varchar  not null,
    zip_code        varchar,
    city            varchar,
    detail_address  varchar,
    vegetarian_type varchar,
    create_date     timestamp,
    edit_date       timestamp
);

CREATE TABLE `member_roles` (
    member_member_id bigint not null,
    roles            varchar
);