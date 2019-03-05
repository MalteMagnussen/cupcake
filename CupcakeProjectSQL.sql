DROP SCHEMA IF EXISTS `Cupcake`;

CREATE SCHEMA IF NOT EXISTS`Cupcake`;
USE `Cupcake`;

DROP TABLE IF EXISTS `Cupcake`.`ordertails`;
DROP TABLE IF EXISTS `Cupcake`.`invoices`;
DROP TABLE IF EXISTS `Cupcake`.`topping`;
DROP TABLE IF EXISTS `Cupcake`.`bottom`;
DROP TABLE IF EXISTS `Cupcake`.`users`;

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
