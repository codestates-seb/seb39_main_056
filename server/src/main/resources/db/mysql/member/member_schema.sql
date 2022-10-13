
DROP TABLE IF EXISTS member CASCADE;

create table member (
                        member_id bigint,
                        create_date datetime(6),
                        edit_date datetime(6),
                        city varchar(255),
                        detail_address varchar(255),
                        zip_code varchar(255),
                        email varchar(255),
                        member_name varchar(255),
                        password varchar(255),
                        phone varchar(255),
                        vegetarian_type varchar(255)
) engine=InnoDB;
