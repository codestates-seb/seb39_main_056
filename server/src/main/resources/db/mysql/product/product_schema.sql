DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS category CASCADE;

create table product (
                         product_id bigint,
                         create_date datetime(6),
                         edit_date datetime(6),
                         detail_image longtext,
                         price integer,
                         product_name varchar(255),
                         stock_quantity integer,
                         thumbnail_image longtext,
                         vegetarian_type varchar(255)
) engine=InnoDB;

create table category (
                          category_id bigint,
                          category_name varchar(255),
                          depth bigint,
                          parent_category_id bigint
) engine=InnoDB;