DROP SCHEMA IF EXISTS `cupcake`;

CREATE SCHEMA IF NOT EXISTS`cupcake`;
USE `cupcake`;

DROP TABLE IF EXISTS `cupcake`.`ordertails`;
DROP TABLE IF EXISTS `cupcake`.`invoices`;
DROP TABLE IF EXISTS `cupcake`.`topping`;
DROP TABLE IF EXISTS `cupcake`.`bottom`;
DROP TABLE IF EXISTS `cupcake`.`users`;

CREATE TABLE `users`(
`name` VARCHAR(55),
`password` VARCHAR(60),
`balance` BIGINT,
`email` VARCHAR(200),  
PRIMARY KEY (`name`)
);

CREATE TABLE `bottom`(
`bname` VARCHAR(25),
`Price` INT(25),
PRIMARY KEY (`bname`)
);

CREATE TABLE `topping`(
`tname` VARCHAR(25),
`Price` INT(25),
PRIMARY KEY (`tname`)
);

CREATE TABLE `invoices`(
	`name` VARCHAR(45),
    `id` int,
    `date` DATETIME NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (`id`),
     INDEX `cname_idx` (`name` ASC) VISIBLE,
		CONSTRAINT `name`
			FOREIGN KEY (`name`)
			REFERENCES `cupcake`.`users` (`name`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION
);

CREATE TABLE `ordertails`(
	`id` int,
    `bname` VARCHAR(45),
    `tname` VARCHAR(45),
    `qty` int,
     INDEX `invid_idx` (`id` ASC) VISIBLE,
		CONSTRAINT `invid`
			FOREIGN KEY (`id`)
			REFERENCES `cupcake`.`invoices` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION
);

