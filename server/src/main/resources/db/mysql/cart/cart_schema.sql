DROP TABLE IF EXISTS `cart` CASCADE;

CREATE TABLE `cart`
(
    cart_id         bigint     not null,
    created_date    timestamp,
    edit_date       timestamp
);