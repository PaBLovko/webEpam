USE `restaurant_db`;

/*
	 * 1 - administrator (Role.ADMINISTRATOR)
	 * 2 - deliverer (Role.DELIVERER)
	 * 3 - visitor (Role.VISITOR)
	 */

CREATE TABLE `user`
(
    `id_user`    INTEGER      NOT NULL AUTO_INCREMENT,
    `login`      VARCHAR(60)  NOT NULL UNIQUE,
    `password`   VARCHAR(60)  NOT NULL,
    `role`       TINYINT      NOT NULL CHECK (`role` IN (1, 2, 3)),
    `surname`    VARCHAR(100) NOT NULL,
    `name_user`  VARCHAR(100) NOT NULL,
    `patronymic` VARCHAR(100) NOT NULL,
    `address`    VARCHAR(100) NOT NULL,
    `phone`      VARCHAR(40)  NOT NULL,
    `note`       VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (`id_user`)
);

CREATE TABLE `dish`
(
    `id_dish`     INTEGER       NOT NULL AUTO_INCREMENT,
    `name_dish`   VARCHAR(50)   NOT NULL,
    `weight`      INTEGER       NOT NULL,
    `price`       DOUBLE        NOT NULL,
    `description` VARCHAR(2000) NOT NULL,
    `picture`     VARCHAR(255)  NOT NULL,
    CONSTRAINT pk_dish PRIMARY KEY (`id_dish`)
);

CREATE TABLE `order`
(
    `id_order`        INTEGER NOT NULL AUTO_INCREMENT,
    `user_id`         INTEGER NOT NULL,
    `total`           DOUBLE  NOT NULL,
    `preparationTime` DATETIME,
    `deliveryTime`    DATETIME,
    `status`          varchar(50),
    CONSTRAINT pk_order PRIMARY KEY (`id_order`),
    CONSTRAINT fk_order_user FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `basket`
(
    `id_basket` INTEGER NOT NULL AUTO_INCREMENT,
    `user_login` VARCHAR(50) NOT NULL,
    `total`     DOUBLE  NOT NULL,
    CONSTRAINT pk_basket PRIMARY KEY (`id_basket`),
    CONSTRAINT fk_basket_user FOREIGN KEY (`user_login`)
        REFERENCES `user` (`login`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comment`
(
    `id_comment`   INTEGER       NOT NULL AUTO_INCREMENT,
    `user_id`      INTEGER       NOT NULL,
    `comment_date` DATETIME,
    `review`       VARCHAR(2000) NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (`id_comment`),
    CONSTRAINT fk_comment_user FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `order_product`
(
    `id_order_product` INTEGER NOT NULL AUTO_INCREMENT,
    `order_id`         INTEGER NOT NULL,
    `dish_id`          INTEGER NOT NULL,
    `order_amount` INTEGER NOT NULL,
    `order_cost` DOUBLE NOT NULL,
    CONSTRAINT pk_order_product PRIMARY KEY (`id_order_product`),
    CONSTRAINT fk_order_product_order FOREIGN KEY (`order_id`)
        REFERENCES `order`(`id_order`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_order_product_dish FOREIGN KEY (`dish_id`)
        REFERENCES `dish`(`id_dish`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `basket_product`(
    `id_basket_product` INTEGER NOT NULL AUTO_INCREMENT,
    `basket_id`         INTEGER NOT NULL,
    `dish_id`           INTEGER NOT NULL,
    `basket_amount` INTEGER NOT NULL,
    `basket_cost` DOUBLE NOT NULL,
    CONSTRAINT pk_basket_product PRIMARY KEY (`id_basket_product`),
    CONSTRAINT fk_basket_product_basket FOREIGN KEY (`basket_id`)
        REFERENCES `basket` (`id_basket`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_basket_product_order FOREIGN KEY (`dish_id`)
        REFERENCES `dish` (`id_dish`) ON DELETE CASCADE ON UPDATE CASCADE
);