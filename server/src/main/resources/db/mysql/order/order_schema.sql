DROP TABLE IF EXISTS orders CASCADE;

create table orders (
                        orders_id bigint,
                        create_date datetime(6),
                        edit_date datetime(6),
                        orders_status varchar(255),
                        member_id bigint
) engine=InnoDB;