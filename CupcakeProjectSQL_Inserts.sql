INSERT INTO `cupcake`.`bottom`
(`bname`,
`Price`)
VALUES
('Chocolate', 5.00), ('Vanilla', 5.00), ('Nutmeg', 5.00), ('Pistacio', 6.00), ('Almond', 7.00);

INSERT INTO `cupcake`.`topping`
(`tname`,
`Price`)
VALUES
('Chocolate', 5.00), ('Blueberry', 5.00), ('Rasberry', 5.00), ('Crispy', 6.00), ('Strawberry', 6.00), ('Rum/Raisin', 7.00), ('Orange', 8.00), ('Lemon', 8.00), ('Blue cheese', 9.00);

INSERT INTO `Cupcake`.`users`
(`name`,
`password`,
`balance`,
`email`)
VALUES
('test',
'test',
0,
<<<<<<< HEAD
'test@gmail.com');

INSERT INTO `Cupcake`.`users`
(`name`,
`password`,
`balance`,
`email`)
VALUES
=======
'test@gmail.com'),
>>>>>>> origin/master
('admin',
'1234',
0,
'cupcake@gmail.com');

COMMIT;
