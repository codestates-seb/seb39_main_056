DROP TABLE IF EXISTS order_product CASCADE;

create table order_product (
                               orders_product_id bigint,
                               orders_price integer,
                               orders_quantity integer,
                               orders_id bigint,
                               product_id bigint
) engine=InnoDB;