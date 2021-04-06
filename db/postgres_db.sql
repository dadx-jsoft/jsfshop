DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id int NOT NULL DEFAULT NEXTVAL ('category_seq'),
  name varchar(45) NOT NULL,
  PRIMARY KEY (id)
);
INSERT INTO category VALUES (1,'DIEN THOAI'),(2,'MAY TINH BANG'),(3,'LAPTOP'),(4,'PHá»¤ KIá»†N');

DROP TABLE IF EXISTS manufacturer;
CREATE TABLE manufacturer (
  id int NOT NULL DEFAULT NEXTVAL ('manufacturer_seq'),
  name varchar(45) NOT NULL,
  country varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);
INSERT INTO manufacturer VALUES (1,'Apple',NULL),(2,'Samsung',NULL),(3,'Nokia',NULL),(4,'Sony',NULL),(5,'Oppo',NULL);

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
  id int NOT NULL DEFAULT NEXTVAL ('user_seq'),
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  username varchar(100) NOT NULL,
  password varchar(100) DEFAULT NULL,
  u_role varchar(45) DEFAULT 'USER',
  PRIMARY KEY (id),
  CONSTRAINT username_UNIQUE UNIQUE  (username)
);
INSERT INTO "user" VALUES (1,'duong xuan da','user@gmail.com','user','ee11cbb19052e40b07aac0ca060c23ee','USER'),(3,'DÆ°Æ¡ng Thanh Háº£i','admin@gmail.com','admin','21232f297a57a5a743894a0e4a801fc3','USER');

DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
  id int NOT NULL DEFAULT NEXTVAL ('payment_seq'),
  created_date timestamp(0) DEFAULT NULL,
  user_id int DEFAULT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_payment_user FOREIGN KEY (user_id) REFERENCES "user" (id)
);
INSERT INTO payment VALUES (1,'2021-04-04 00:00:00',1);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id int NOT NULL DEFAULT NEXTVAL ('product_seq'),
  name varchar(45) NOT NULL,
  price decimal(10,0) DEFAULT '0',
  category_id int NOT NULL,
  image varchar(255) DEFAULT NULL,
  description text,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
);
INSERT INTO product VALUES (1,'Iphone 7 Plus',16,1,'upload/Screenshot_2.png','64GB, Apple'),(2,'Galaxy note 10',24,1,'upload/Screenshot_2.png','64GB, Samsung'),(3,'Ipad pro 11',18,2,'upload/Screenshot_2.png','128GB, Apple'),(4,'Sangsung tab s6',20,2,'upload/Screenshot_2.png','128GB, Samsung'),(5,'Iphone 13',5000,1,'upload/Screenshot_2.png','khong co gi'),(7,'Iphone 13',5000,1,'upload/Screenshot_2.png','khong co gi'),(8,'Iphone 7 Plus',16,1,'upload/Screenshot_2.png','64GB, Apple'),(9,'Iphone 7 Plus',16,1,'upload/Screenshot_2.png','64GB, Apple'),(10,'Iphone 7 Plus',16,1,'upload/Screenshot_2.png','64GB, Apple'),(11,'Iphone 7 Plus',16,1,'upload/Screenshot_2.png','64GB, Apple');

DROP TABLE IF EXISTS payment_detail;
CREATE TABLE payment_detail (
  id int NOT NULL DEFAULT NEXTVAL ('payment_detail_seq'),
  payment_id int NOT NULL,
  product_id int NOT NULL,
  price decimal(10,0) DEFAULT NULL,
  count int DEFAULT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_detail_payment FOREIGN KEY (payment_id) REFERENCES payment (id),
  CONSTRAINT fk_detail_product FOREIGN KEY (product_id) REFERENCES product (id)
);
INSERT INTO payment_detail VALUES (1,1,2,24,2),(2,1,3,18,1);

DROP TABLE IF EXISTS prod_manufacturer;
CREATE TABLE prod_manufacturer (
  id int NOT NULL DEFAULT NEXTVAL ('prod_manufacturer_seq'),
  product_id int NOT NULL,
  manufacturer_id int NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES manufacturer (id),
  CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id)
);
INSERT INTO prod_manufacturer VALUES (1,5,1),(3,7,5),(4,9,3),(6,11,2),(8,10,2);
