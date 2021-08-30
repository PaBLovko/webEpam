INSERT INTO `user`
(`login`, `password`, `inSystem`, `role`, `id`)
VALUES
("user1", "EE11CBB19052E40B07AAC0CA060C23EE",/* MD5 хэш пароля "user" */ 1, 1, 2),
("user2", "EE11CBB19052E40B07AAC0CA060C23EE",/* MD5 хэш пароля "user" */ 1, 2, 3);

INSERT INTO `client`
(`login`, `loyaltyPoints`, `block`, `id`, `balance`)
VALUES
("user1", 2,   0,    2,  100),
("user2", 25,   0,    3,  1000);

INSERT INTO `comment`
(`author`, `id`, `date`, `time`, `comment`, `idProduct`, `evaluation`)
VALUES
("Margo", 1, "10.02.2012", "19-30", "Ну такое", 1, "пять"),
("Pablo", 2, "11.02.2012", "21-30", "Агонь", 1, "десять"),
("Kim", 3, "12.02.2012", "10-30", "e-e-e", 1, "двадцать");

INSERT INTO `menu`
(`product`, `cost`, `cookingTime`, `tag`, `example`, `id`, `average`, `votesNumber`)
VALUES
("potato", 10, "11 min", "1", "", 1, 5.65, 123),
("corn", 7, "12 min", "2", "", 2, 1.23, 12),
("buckwheat", 5, "15 min", "3", "", 3, 7.77, 357);

INSERT INTO `order`
(`id`, `productId`, `orderTime`, `customer`, `paymentMethod`, `product`)
VALUES
(1, 1, "11 min", "user1", "cash", "potato"),
(2, 2, "7 min", "user2", "cash", "corn");

INSERT INTO 'client_order_id'
('order_id', 'client_id')
VALUES
(1, 2),
(2, 3);