USE `library_db`;

CREATE TABLE menu
(
    product     VARCHAR(45)              NOT NULL,
    cost        INT                      NOT NULL,
    cookingTime VARCHAR(45)              NOT NULL,
    tag         VARCHAR(45)              NOT NULL,
    example     VARCHAR(95)              NULL,
    id          INT AUTO_INCREMENT
        PRIMARY KEY,
    average     FLOAT(3, 2) DEFAULT 0.00 NOT NULL,
    votesNumber INT         DEFAULT 0    NOT NULL
);

CREATE TABLE comment
(
    author     VARCHAR(45) NOT NULL,
    id         INT AUTO_INCREMENT
        PRIMARY KEY,
    date       VARCHAR(45) NOT NULL,
    time       VARCHAR(45) NOT NULL,
    comment    VARCHAR(45) NOT NULL,
    idProduct  INT         NOT NULL,
    evaluation VARCHAR(45) NOT NULL,
    CONSTRAINT comment_menu_id_fk
        FOREIGN KEY (idProduct) REFERENCES menu (id)
            ON DELETE CASCADE
);

CREATE TABLE `order`
(
    id            INT AUTO_INCREMENT
        PRIMARY KEY,
    productId     INT         NOT NULL,
    orderTime     VARCHAR(45) NOT NULL,
    customer      VARCHAR(45) NOT NULL,
    paymentMethod VARCHAR(45) NOT NULL,
    product       VARCHAR(45) NULL,
    CONSTRAINT order_menu_id_fk
        FOREIGN KEY (productId) REFERENCES menu (id)
);

CREATE TABLE user
(
    id       INT AUTO_INCREMENT
        PRIMARY KEY,
    login    VARCHAR(50)                NOT NULL,
    password VARCHAR(80)                NOT NULL,
    inSystem TINYINT     DEFAULT 0      NOT NULL,
    role     VARCHAR(45) DEFAULT 'user' NOT NULL
);

CREATE TABLE client
(
    login         VARCHAR(45)       NOT NULL,
    loyaltyPoints INT     DEFAULT 0 NOT NULL,
    block         TINYINT DEFAULT 0 NOT NULL,
    id            INT AUTO_INCREMENT
        PRIMARY KEY,
    balance       INT     DEFAULT 0 NOT NULL
);

CREATE TABLE client_order_id
(
    order_id  INT NOT NULL,
    client_id INT NOT NULL
);



