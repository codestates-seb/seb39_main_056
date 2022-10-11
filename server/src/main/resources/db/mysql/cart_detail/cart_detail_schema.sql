DROP TABLE IF EXISTS `cart_detail` CASCADE;

CREATE TABLE `cart_detail`
(
    cart_detail_id      bigint     not null,
    purchase_quantity   int        not null
);