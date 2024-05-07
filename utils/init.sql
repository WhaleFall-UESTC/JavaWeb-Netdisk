update user set host='%' where user='root' ;
grant all privileges on *.* to 'root'@'%' ;
flush privileges;

create user 'Pan'@'localhost' identified by '123456';
flush privileges;

create database mypan;
use mypan;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

GRANT ALL PRIVILEGES ON mypan.* TO 'Pan'@'localhost';
FLUSH PRIVILEGES;