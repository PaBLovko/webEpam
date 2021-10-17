DROP DATABASE IF EXISTS `test_restaurant_db`;

CREATE DATABASE IF NOT EXISTS `test_restaurant_db` DEFAULT CHARACTER SET utf8;

CREATE USER IF NOT EXISTS 'test_restaurant_app'@'localhost'
    IDENTIFIED BY 'test_restaurant_password';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON test_restaurant_db.*
    TO 'test_restaurant_app'@'localhost';

USE `test_restaurant_db`;

/*
	 * 1 - administrator (Role.ADMINISTRATOR)
	 * 2 - deliverer (Role.DELIVERER)
	 * 3 - visitor (Role.VISITOR)
	 */

CREATE TABLE `user`
(
    `id_user`    INTEGER      NOT NULL AUTO_INCREMENT,
    `login`      VARCHAR(50)  NOT NULL UNIQUE,
    `password`   VARCHAR(50)  NOT NULL,
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

USE `test_restaurant_db`;
INSERT INTO `user`
    (`login`,
    `password`,
    `role`,
    `surname`,
    `name_user`,
    `patronymic`,
    `address`,
    `phone`,
    `note`)
VALUES (
        "admin",
        "admin",
        1,
        "Kazhamiakin",
        "Pavel",
        "Nikolaevich",
        "Yakub Kolos 28-306, Minsk",
        "8-044-786-19-32",
        "admin"
);

USE `test_restaurant_db`;

INSERT INTO `user`
(`login`, `password`, `role`, `surname`, `name_user`, `patronymic`, `address`, `phone`, `note`)
VALUES
       ("deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
        "8-029-447-43-45",
        "deliverer1"),
       ("user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
        "8-029-598-23-31",
        "Live music complemented the festive evening well"),
       ("user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
        "8-029-965-31-32",
        "Always friendly staff");
 INSERT INTO `dish`
 (`name_dish`, `weight`, `price`, `description`,`picture`)
 VALUES
        ("Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"),
        ("Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
        ("Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg"),
        ("Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg");

INSERT INTO `comment`
(`user_id`, `comment_date`, `review`)
VALUES
       (3, "2021-01-01T11:00:00", "tasty"),
       (3, "2021-01-02T13:00:00", "excellently"),
       (4, "2021-01-03T11:00:00", "dishes always wonderful");

INSERT INTO `order`
(`user_id`, `total`, `preparationTime`, `deliveryTime`, `status`)
VALUES
       (3, 62.00, "2021-01-03T08:00:00", "2021-01-03T14:00:00", "delivered"),
       (3, 46.00, "2021-01-04T08:00:00", "2021-01-04T17:00:00", "delivered"),
       (4, 71.00, "2021-01-05T08:00:00", "2021-01-05T15:30:00", "delivered");

INSERT INTO `order_product`
(`order_id`, `dish_id`, `order_amount`, `order_cost`)
VALUES
       (1, 4, 2, 62.00),
       (2, 1, 1, 14.00),
       (2, 2, 1, 25.00),
       (3, 3, 1, 25.00),
       (3, 4, 1, 26.00),
       (3, 3, 1, 22.00);

INSERT INTO `basket`
(`user_login`, `total`)
VALUES
       ("user1", 0.0),
       ("user2", 0.0);