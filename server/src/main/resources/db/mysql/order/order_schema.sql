DROP TABLE IF EXISTS `orders` CASCADE;

CREATE TABLE `orders`
(
    orders_id         bigint     not null,
    created_date    timestamp,
    edit_date       timestamp,
    orders_status   varchar     not null
);