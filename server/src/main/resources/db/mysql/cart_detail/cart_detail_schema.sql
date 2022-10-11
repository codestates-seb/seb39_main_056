DROP TABLE IF EXISTS cart_detail CASCADE;

create table cart_detail (
                             cart_detail_id bigint,
                             purchase_quantity integer,
                             cart_id bigint,
                             product_id bigint
) engine=InnoDB;