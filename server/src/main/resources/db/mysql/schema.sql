CREATE TABLE IF NOT EXISTS PRODUCT
(
    product_id bigint NOT NULL AUTO_INCREMENT,
    product_name varchar NOT NULL,
    price int NOT NULL,
    stock_quantity int NOT NULL,
    thumbnail_image clob,
    detail_image clob,
    vegetarian_type varchar,
    create_date timestamp,
    edit_date timestamp,
    PRIMARY KEY (product_id)
    );

CREATE TABLE IF NOT EXISTS MEMBER (
                                      member_id bigint NOT NULL AUTO_INCREMENT,
                                      email varchar NOT NULL UNIQUE,
                                      member_name varchar NOT NULL,
                                      password varchar,
                                      phone varchar NOT NULL,
                                      zip_code varchar,
                                      city varchar,
                                      detail_address varchar,
                                      vegetarian_type varchar,
                                      create_date timestamp,
                                      edit_date timestamp,
                                      PRIMARY KEY (member_id)
    );

CREATE TABLE IF NOT EXISTS MEMBER_ROLES (
                                            member_member_id bigint not null,
                                            roles varchar(255)
    );

CREATE TABLE IF NOT EXISTS CART (
   cart_id bigint NOT NULL AUTO_INCREMENT,
   create_date timestamp,
   edit_date timestamp,
   PRIMARY KEY (cart_id)
   FOREIGN KEY (mamber_id) REFERENCES member(member_id)
);

CREATE TABLE IF NOT EXISTS CART_DETAIL (
    cart_detail_id bigint NOT NULL AUTO_INCREMENT,
    purchase_quantity int NOT NULL,
    PRIMARY KEY (cart_detail_id),
    FOREIGN KEY (cart_id) REFERENCES cart (cart_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE IF NOT EXISTS ORDERS (
                                      orders_id bigint NOT NULL AUTO_INCREMENT,
                                      create_date timestamp,
                                      edit_date timestamp,
                                      orders_status varchar,
                                      PRIMARY KEY (orders_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
    );

CREATE TABLE IF NOT EXISTS ORDER_PRODUCT (
    orders_product_id bigint NOT NULL AUTO_INCREMENT,
    orders_qantity integer NOT NULL,
    orders_price integer NOT NULL,
    PRIMARY KEY (orders_product_id),
    FOREIGN KEY (orders_id) REFERENCES orders (orders_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE IF NOT EXISTS CATEGORY
(
    category_id bigint NOT NULL AUTO_INCREMENT,
    category_name varchar,
    depth bigint,
    parent_category_id bigint,
    PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS VEGETARIAN
(
    vegetarian_type int NOT NULL,
    level integer,
    PRIMARY KEY (vegetarian_type)
);






