# DROP DATABASE IF EXISTS dmvc_test;
#
# CREATE DATABASE dmvc_test
#   DEFAULT CHARACTER SET utf8
#   DEFAULT COLLATE utf8_general_ci;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer(id        INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,name      VARCHAR(255)        NOT NULL,contact   VARCHAR(255)        NOT NULL,telephone VARCHAR(255)                 DEFAULT '',email     VARCHAR(255)                 DEFAULT '',remark    TEXT);
INSERT INTO customer (name, contact, telephone, email, remark) VALUES ('customer1', 'Jack', '13512345678', 'jack@diguage.com', null);
INSERT INTO customer (name, contact, telephone, email, remark) VALUES ('customer2', 'Rose', '13612345678', 'rose@diguage.com', null);
