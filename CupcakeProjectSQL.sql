DROP SCHEMA IF EXISTS `Cupcake`;

CREATE SCHEMA `Cupcake`;
USE `Cupcake`;

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users`(
`name` VARCHAR(55),
`password` VARCHAR(60),
`balance` BIGINT,
PRIMARY KEY (`name`)
);

DROP TABLE IF EXISTS `buttom`;

CREATE TABLE `buttom`(
`bname` VARCHAR(25),
`Price` INT(25),
PRIMARY KEY (`bname`)
);

DROP TABLE IF EXISTS `toppings`;

CREATE TABLE `toppings`(
`tname` VARCHAR(25),
`Price` INT(25),
PRIMARY KEY (`tname`)
);

DROP TABLE IF EXISTS `invoices`;

CREATE TABLE `invoices`(
`inDate` DATE DEFAULT,
`invID` INT,
`cName` VARCHAR(25) 
INDEX bname_idx (bname ASC) VISIBLE,
INDEX tname_idx (tname ASC) VISIBLE,
	CONSTRAINT bname
		FOREIGN KEY (bname)
		REFERENCES Cupcake.`buttom` (bname)
		ON DELETE CASCADE
		ON UPDATE NO ACTION
	CONSTRAINT tname
		FOREIGN KEY (tname)
		REFERENCES Cupcake.`toppings` (tname)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);
