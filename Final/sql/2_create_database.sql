CREATE DATABASE IF NOT EXISTS `restaurant_db` DEFAULT CHARACTER SET utf8;

CREATE USER IF NOT EXISTS 'restaurant_app'@'localhost'
IDENTIFIED BY 'restaurant_password';

GRANT SELECT, INSERT, UPDATE, DELETE
ON restaurant_db.*
TO 'restaurant_app'@'localhost';