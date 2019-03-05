USE `Cupcake`;

DROP TABLE `invoices`;

DROP TABLE IF EXISTS `invoices`;

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
