-- PK

ALTER TABLE member
    ADD CONSTRAINT PK_MEMBER PRIMARY KEY (member_id);

ALTER TABLE orders
    ADD CONSTRAINT PK_ORDERS PRIMARY KEY (orders_id);

ALTER TABLE cart
    ADD CONSTRAINT PK_CART PRIMARY KEY (cart_id);

ALTER TABLE cart_detail
    ADD CONSTRAINT PK_CART_DETAIL PRIMARY KEY (cart_detail_id);

ALTER TABLE order_product
    ADD CONSTRAINT PK_ORDER_PRODUCT PRIMARY KEY (orders_product_id);

ALTER TABLE product
     ADD CONSTRAINT PK_PRODUCT PRIMARY KEY (product_id);

ALTER TABLE category
     ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (category_id);

ALTER TABLE vegetarian
     ADD CONSTRAINT PK_VEGETARIAN PRIMARY KEY (vegetarian_type);


-- AUTO INCREMENT

ALTER TABLE member
    MODIFY member_id BIGINT NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE orders
    MODIFY orders_id BIGINT NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE cart
    MODIFY cart_id BIGINT NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE cart_detail
    MODIFY cart_detail_id BIGINT NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE order_product
    MODIFY order_product_id BIGINT NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE product
    MODIFY product_id BIGINT NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE category
    MODIFY category_id BIGINT NOT NULL AUTO_INCREMENT FIRST;


-- FK

ALTER TABLE cart
    ADD FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE cart_detail
    ADD FOREIGN KEY (cart_id) REFERENCES cart (cart_id);

ALTER TABLE cart_detail
    ADD FOREIGN KEY (product_id) REFERENCES product (proudct_id);

ALTER TABLE orders
    ADD FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE order_product
    ADD FOREIGN KEY (orders_id) REFERENCES orders (orders_id);

ALTER TABLE order_product
    ADD FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE roles
    ADD FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE category
    ADD FOREIGN KEY (parent_category_id) REFERENCES category (category_id);

-- UNIQUE
ALTER TABLE member
    ADD UNIQUE (email);
-- INDEX