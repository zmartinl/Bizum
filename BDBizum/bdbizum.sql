//1 PASO CREAR BASE DE DATOS Y TABLAS.

CREATE DATABASE bdbizum;

CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    telefono CHAR(9) NOT NULL,
    email VARCHAR(150) NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    saldo INT NOT NULL,
);

CREATE TABLE registrobizum (
    idTransacion INT PRIMARY KEY AUTO_INCREMENT,
    idEmisor INT NOT NULL,
    idReceptor INT NOT NULL,
    concepto VARCHAR(500) NULL,
    importe INT NOT NULL,
    FOREIGN KEY (idEmisor) REFERENCES usuarios(id),
    FOREIGN KEY (idReceptor) REFERENCES usuarios(id)
);


//2 PASO INSERTAR DATOS EN LA TABLA USUARIOS.

USE bdbizum;

INSERT INTO usuarios (nombre, telefono, email, saldo) VALUES ('Zeus', '634227116', 'zmartinllera1@gmail.com', 1000),
INSERT INTO usuarios (nombre, telefono, email, saldo) VALUES ('Javier', '692223705', 'javier@gmail.com', 721);
INSERT INTO usuarios (nombre, telefono, email, saldo) VALUES ('Huevi', '722474217', 'huevi@gmail.com', 370);
INSERT INTO usuarios (nombre, telefono, email, saldo) VALUES ('Paco', '657432899', 'fgarcia@gmail.com', 850);
INSERT INTO usuarios (nombre, telefono, email, saldo) VALUES ('Josele', '657432890', 'josele@gmail.com', 863);