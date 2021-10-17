USE `restaurant_db`;

INSERT INTO `user`
(`login`, `password`, `role`, `surname`, `name_user`, `patronymic`, `address`, `phone`, `note`)
VALUES
("deliverer1", "$2a$10$dVryH2Ggq.j32SrCpyExSOBA/ASfGfWYZhe9vA3AEw/fHgpgYCqua", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
 "8-029-447-43-45",
 "Always friendly staff"),
("deliverer2", "deliverer2", 2, "Kovalev", "Pavel", "Petrovich", "Kazinca 17-45, Minsk",
 "8-029-573-76-40",
 "Сonvenient location of tables"),
("user1", "$2a$10$dVryH2Ggq.j32SrCpyExSOBA/ASfGfWYZhe9vA3AEw/fHgpgYCqua", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
 "8-029-598-23-31",
 "Live music complemented the festive evening well"),
("user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
 "8-029-965-31-32",
 "There are enough parking spaces"),
("user3", "user3", 3, "Osipov", "Ivan", "Sergeevich", "Asanalieva 5-15, Minsk",
 "8-029-505-61-50",
 "I was pleased with the very large menu"),
("user4", "user4", 3, "Bogdanova", "Olga", "Andreevna", "Dukorskaya 4-23, Minsk",
 "8-029-975-81-52",
 "Everything is delicious, great snacks"),
("user5", "user5", 3, "Sidorov", "Andrey", "Alekseevich", "Tukhachevsky 16-123, Minsk",
 "8-029-964-67-38",
 "On weekends, it's generally fun-cool cover bands perform"),
("user6", "user6", 3, "Kazhamiakin", "Konstantsin", "Pavlovich", "Tukhachevsky 15-133, Minsk",
 "8-029-364-47-36",
    "On weekends, it's generally fun-cool cover bands perform"),
("user7", "user7", 3, "Petrov", "Andrey", "Borisovich", "Dukorskaya 16-112, Minsk",
 "8-029-575-91-28",
    "Near the house there are construction work");
INSERT INTO `dish`
(`name_dish`, `weight`, `price`, `description`,`picture`)
VALUES
("Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"),
("Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
("Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg"),
("Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg"),
("Barbecue", 700, 35.00, "Fried potatoes, smoked brisket, leeks and mushrooms.","image/HotPan.jpg"),
("Pancakes", 950, 20.00, "Potato pancakes stuffed with minced pork are fried.","image/PotatoPancakes.jpg"),
("Belish", 400, 36.00, "Puff pastry pie with spiced lamb filling.","image/Belish.jpg"),
("Kvass", 20,5.00, "Fermentation kvass from rye bread","image/BreadKvass.jpg"),
("Carp", 300, 23.00, "Carp, fried to a crisp, with boiled potatoes, deep-fried, ketchup, herbs, spices.","image/FriedCarp.jpg"),
("Sorcerers", 300, 25.00, "Served in a pot with coriander and garlic-sour cream sauce.","image/TatarSorcerers.jpg"),
("Broccoli", 50, 5.00, "Boiled broccoli cabbage, with melted butter.","image/BroccoliButter.jpg"),
("Dumplings", 200, 11.00, "Cottage cheese filling, sour cream and berry sauce.","image/DumplingsCheese.jpg"),
("Ice-cream", 50,8.00, "Cream ice cream with seasonal fruits, nuts and chocolate","image/IceCream.jpg"),
("Potato", 70,5.00, "Mashed potatoes as a delicious side dish.","image/MashedPotato.jpg"),
("Dessert", 70,5.00, "Сheesecake the way you love it","image/DessertCheese.jpg"),
("Vegetables", 60,9.00, "Tomatoes, peppers, eggplant with herbs and lemon-orange sauce.","image/BakedVegetables.jpg"),
("Varenik", 500, 15.00, "Branded dough with cherry filling determines the dessert character of the dish.","image/Dumplings.jpg");

INSERT INTO `comment`
(`user_id`, `comment_date`, `review`)
VALUES
(5, "2021-01-01T11:00:00", "Many thanks to the chefs for such a delicious meal. Insanely delicious dishes. And dessert pancakes are super)!"),
(6, "2021-01-01T13:00:00", "Very tasty and everything is at the highest level, especially the service. I was in this restaurant, the waiter told me everything in detail, advised me what is better to take and did not regret it, special thanks."),
(7, "2021-01-01T11:00:00", "Very, very, very tasty!!!! The freshest dishes, just eat and still want!!!Delivery is a long time, but for the sake of this yummy is worth waiting)))) Perhaps you are the best in the city!"),
(8, "2021-01-02T11:00:00", "We tried a lot of restaurants, now we order only here."),
(5, "2021-01-03T11:00:00", "We order only from you always, others do not even want to. Always fresh products, excellent taste. I would also like to ask that they add drinks to the menu!!! Thank you for being ther"),
(6, "2021-01-01T11:00:00", "orders are VERY tasty, the quality is excellent, but there are two small buts: - the delivery lead time would be shorter, an hour and a half is too much - there is a lack of a discount system for regular customers"),
(7, "2021-01-07T11:00:00", "You guys are cool and we only order from you! Please stay the same and delight us with delicious You-space, guys!)"),
(8, "2021-01-05T11:00:00", "Guys, keep it up ! The coolest food in town !! Price and quality !!! Good and fast service !"),
(5, "2021-01-06T11:00:00", "I am very careful about home delivery of food. For the first time today, we ordered a salad and soup from you. It was absolutely delicious! As in a good restaurant, we will be your customers with my husband. Thanks!!");

INSERT INTO `order`
(`user_id`, `total`, `preparationTime`, `deliveryTime`, `status`)
VALUES
(4, 62.00, "2021-01-02T09:00:00", "2021-01-02T15:00:00", "delivered"),
(5, 46.00, "2021-01-03T09:00:00", "2021-01-03T18:00:00", "delivered"),
(6, 71.00, "2021-01-04T09:00:00", "2021-01-04T17:30:00", "delivered"),
(7, 80.00, "2021-01-05T09:30:00", "2021-01-05T18:30:00", "delivered"),
(8, 40.00, "2020-01-05T10:30:00", "2020-01-05T19:30:00", "delivered"),
(9, 38.00, "2021-01-05T09:30:00", "2021-01-05T19:30:00", "not delivered"),
(10, 113.00, "2021-01-06T10:30:00", "2021-01-06T19:30:00", "delivered"),
(3, 56.00, "2021-01-06T10:30:00", "2021-01-06T19:30:00", "delivered"),
(4, 40.00, "2021-01-07T10:30:00", "2021-01-07T20:30:00", "delivered"),
(4, 60.00, "2021-01-08T10:00:00", "2021-01-08T20:00:00", "not delivered"),
(6, 57.00, "2021-01-08T10:00:00", "2021-01-08T20:35:00", "delivered"),
(4, 75.00, "2021-01-09T10:00:00", "2021-01-09T20:35:00", "delivered"),
(4, 56.00, "2021-01-10T10:00:00", "2021-01-10T18:00:00", "delivered"),
(8, 59.00, "2021-01-10T10:00:00", "2021-01-10T19:00:00", "delivered"),
(7, 50.00, "2021-01-10T10:00:00", "2021-01-10T20:00:00", "delivered"),
(7, 50.00, "2021-01-11T10:00:00", "2021-01-11T19:00:00", "delivered"),
(6, 60.00, "2021-01-12T10:00:00", "2021-01-12T19:00:00", "delivered"),
(6, 45.00, "2021-01-13T10:00:00", "2021-01-13T19:00:00", "delivered"),
(8, 44.00, "2021-01-13T10:00:00", "2021-01-13T19:30:00", "delivered"),
(4, 40.00, "2021-01-13T10:00:00", "2021-01-13T20:00:00", "delivered");

INSERT INTO `order_product`
(`order_id`, `dish_id`, `order_amount`, `order_cost`)
VALUES
(1, 16, 2, 62.00),
(2, 1, 1, 14.00),
(2, 2, 1, 25.00),
(3, 3, 1, 25.00),
(3, 4, 1, 26.00),
(3, 5, 1, 22.00),
(4, 6, 1, 37.00),
(4, 7, 1, 38.00),
(4, 8, 1, 15.00),
(5, 9, 2, 50.00),
(6, 10, 2, 28.00),
(7, 11, 1, 32.00),
(7, 12, 1, 25.00),
(7, 13, 1, 19.00),
(7, 14, 1, 47.00),
(8, 15, 2, 46.00),
(9, 5, 1, 30.00),
(9, 9, 1, 30.00),
(10, 8, 1, 35.00),
(10, 12, 1, 25.00),
(11, 2, 1, 32.00),
(11, 12, 1, 45.00),
(12, 2, 1, 23.00),
(12, 3, 1, 24.00),
(12, 6, 1, 21.00),
(13, 15, 2, 53.00),
(14, 15, 1, 24.00),
(14, 16, 1, 34.00),
(15, 8, 2, 54.00),
(16, 8, 2, 53.00),
(17, 8, 1, 24.00),
(17, 12, 1, 32.00),
(18, 8, 1, 21.00),
(18, 9, 1, 23.00),
(19, 2, 2, 44.00),
(20, 5, 2, 42.00);

INSERT INTO `basket`
(`user_login`, `total`)
VALUES
("user1", 0.0),
("user2", 0.0),
("user3", 0.0),
("user4", 0.0),
("user5", 0.0),
("user6", 0.0),
("user7", 0.0);
