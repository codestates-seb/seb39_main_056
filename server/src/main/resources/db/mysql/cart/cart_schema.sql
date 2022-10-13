DROP TABLE IF EXISTS cart CASCADE;

create table cart (
                      cart_id bigint,
                      create_date datetime(6),
                      edit_date datetime(6),
                      member_id bigint
) engine=InnoDB;