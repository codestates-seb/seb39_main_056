DROP TABLE IF EXISTS `order_product` CASCADE;

CREATE TABLE `order_product`
(
    orders_product_id     bigint     not null,
    orders_quantity       int        not null,
    orders_price          int        not null
);