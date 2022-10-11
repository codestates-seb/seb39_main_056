DROP TABLE IF EXISTS `product` CASCADE;
DROP TABLE IF EXISTS `category` CASCADE;

CREATE TABLE `product`
(
    product_id      bigint      not null,
    product_name    varchar     not null,
    price           int         not null,
    stock_quantity  int         not null,
    thumbnail_image clob,
    detail_image    clob,
    vegetarian_type varchar,
    create_date     timestamp,
    edit_date       timestamp
);

CREATE TABLE `category`
(
    category_id bigint not null ,
    category_name varchar,
    depth bigint,
    parent_category_id bigint
);