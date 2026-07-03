CREATE DATABASE IF NOT EXISTS imc_app
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE imc_app;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(120) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(64) NOT NULL,
    edad INT NOT NULL,
    sexo VARCHAR(15) NOT NULL,
    estatura DECIMAL(3,2) NOT NULL,
    CONSTRAINT chk_edad CHECK (edad >= 15),
    CONSTRAINT chk_estatura CHECK (estatura >= 1.00 AND estatura <= 2.50)
);

CREATE TABLE IF NOT EXISTS calculos_imc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    imc DECIMAL(5,2) NOT NULL,
    categoria VARCHAR(40) NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_peso CHECK (peso > 0),
    CONSTRAINT fk_calculos_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE CASCADE
);
