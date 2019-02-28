CREATE SCHEMA IF NOT EXISTS`Cupcake`;
USE `Cupcake`;

DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS topping;
DROP TABLE IF EXISTS bottom;
DROP TABLE IF EXISTS users;

CREATE TABLE `Users`(
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

CREATE TABLE `cupcake`.`invoices` (
  `idinvoices` INT NOT NULL,
  `quantity` INT NULL,
  `bname` VARCHAR(45) NULL,
  `tname` VARCHAR(45) NULL,
  `date` DATETIME NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`idinvoices`),
  INDEX `bname_idx` (`bname` ASC) VISIBLE,
  INDEX `tname_idx` (`tname` ASC) VISIBLE,
  CONSTRAINT `bname`
    FOREIGN KEY (`bname`)
    REFERENCES `cupcake`.`bottom` (`bname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tname`
    FOREIGN KEY (`tname`)
    REFERENCES `cupcake`.`topping` (`tname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
