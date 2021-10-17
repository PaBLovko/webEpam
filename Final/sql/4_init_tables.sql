USE `restaurant_db`;
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
"$2a$10$dVryH2Ggq.j32SrCpyExSOBA/ASfGfWYZhe9vA3AEw/fHgpgYCqua",
1,
"Kazhamiakin",
"Pavel",
"Nikolayevich",
"Yakub Kolos 28-306, Minsk",
"8-044-786-19-32",
"admin"
);