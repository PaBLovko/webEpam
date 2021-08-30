CREATE DATABASE `library_db` DEFAULT CHARACTER SET utf8;

create user 'library_app'@localhost
        identified by 'library_password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `library_db`.*
    TO library_app@localhost;

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `library_db`.*
    TO library_app@'%';
#IDENTIFIED BY 'library_password';