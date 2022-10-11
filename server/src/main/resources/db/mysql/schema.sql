CREATE TABLE IF NOT EXISTS `member` (
                                      member_id bigint not null,
                                      email varchar not null,
                                      member_name varchar not null,
                                      password varchar,
                                      phone varchar not null,
                                      zip_code varchar,
                                      city varchar,
                                      detail_address varchar,
                                      vegetarian_type varchar,
                                      create_date timestamp,
                                      edit_date timestamp,
                                      PRIMARY KEY (member_id)
    );

CREATE TABLE IF NOT EXISTS PRODUCT
(
    product_id bigint not null AUTO_INCREMENT,
    product_name varchar not null,
    price int not null,
    stock_quantity int not null,
    thumbnail_image clob,
    detail_image clob,
    vegetarian_type varchar,
    create_date timestamp,
    edit_date timestamp,
    PRIMARY KEY (product_id)
    );

CREATE TABLE IF NOT EXISTS MEMBER_ROLES (
                                            member_member_id bigint not null,
                                            roles varchar(255)
    );

CREATE TABLE IF NOT EXISTS CART (
   cart_id bigint not null AUTO_INCREMENT,
   create_date timestamp,
   edit_date timestamp,
   PRIMARY KEY (cart_id),
   FOREIGN KEY (mamber_id) REFERENCES member(member_id)
);

CREATE TABLE IF NOT EXISTS CART_DETAIL (
    cart_detail_id bigint not null AUTO_INCREMENT,
    purchase_quantity int not null,
    PRIMARY KEY (cart_detail_id),
    FOREIGN KEY (cart_id) REFERENCES cart (cart_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE IF NOT EXISTS ORDERS (
                                      orders_id bigint not null AUTO_INCREMENT,
                                      create_date timestamp,
                                      edit_date timestamp,
                                      orders_status varchar,
                                      PRIMARY KEY (orders_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
    );

CREATE TABLE IF NOT EXISTS ORDER_PRODUCT (
    orders_product_id bigint not null AUTO_INCREMENT,
    orders_quantity int not null,
    orders_price int not null,
    PRIMARY KEY (orders_product_id),
    FOREIGN KEY (orders_id) REFERENCES orders (orders_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE IF NOT EXISTS CATEGORY
(
    category_id bigint not null AUTO_INCREMENT,
    category_name varchar,
    depth bigint,
    parent_category_id bigint,
    PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS VEGETARIAN
(
    vegetarian_type int not null,
    level int,
    PRIMARY KEY (vegetarian_type)
);






