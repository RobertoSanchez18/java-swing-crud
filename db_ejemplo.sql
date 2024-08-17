CREATE DATABASE db_ejemplo;
USE db_ejemplo;

CREATE TABLE persona
(
	id int auto_increment,
    nombre varchar(100),
    correo varchar(100),
    telefono varchar(100),
    primary key(id)
);

SELECT * FROM persona;

INSERT INTO persona
(nombre, correo, telefono)
VALUES
("Jose", "jose.ramos@gmail.com", "908999007"),
("Miguel", "miguel.flores@gmail.com", "987667555"),
("Maria", "maria.barrios@gmail.com", "975152134"),
("Pepe", "pepe.casas@gmail.com", "900001124");

