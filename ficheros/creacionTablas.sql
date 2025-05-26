DROP TABLE IF EXISTS inversion;
DROP TABLE IF EXISTS recompensa;
DROP TABLE IF EXISTS proyecto;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    correo VARCHAR(100) PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    tipo ENUM('GESTOR', 'INVERSOR', 'ADMINISTRADOR') NOT NULL,
    bloqueado BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO usuario (correo, nombre, contrasena, tipo, bloqueado) VALUES
    ('gestor1@correo.com', 'Gestor Uno', '1234', 'GESTOR', FALSE),
    ('inversor1@correo.com', 'Inversor Uno', 'abcd', 'INVERSOR', FALSE),
    ('admin1@correo.com', 'Admin Uno', 'admin', 'ADMINISTRADOR', FALSE);

CREATE TABLE proyecto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    cantidad_necesaria DECIMAL(10, 2),
    cantidad_financiada DECIMAL(10, 2) DEFAULT 0.00,
    fecha_inicio DATE DEFAULT (CURRENT_DATE),
    fecha_fin DATE,
    categoria ENUM('TECNOLOGIA', 'ARTE', 'CINE', 'JUEGOS', 'COMIDA', 'MODA'),
    correo_gestor VARCHAR(100),
    FOREIGN KEY (correo_gestor) REFERENCES usuario(correo)
);

INSERT INTO proyecto (nombre, descripcion, cantidad_necesaria, cantidad_financiada, fecha_inicio, fecha_fin, categoria, correo_gestor)
VALUES ('Impresora 3D doméstica', 'Prototipo de impresora accesible', 1000.00, 250.00, '2025-05-01', '2025-08-01', 'TECNOLOGIA', 'gestor1@correo.com'),
       ('Documental sobre energías limpias', 'Rodaje independiente en España', 5000.00, 1200.00, '2025-04-20', '2025-09-30', 'CINE', 'gestor1@correo.com');

CREATE TABLE recompensa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion TEXT,
    cantidad_minima DECIMAL(10, 2),
    id_proyecto INT,
    FOREIGN KEY (id_proyecto) REFERENCES proyecto(id)
);

INSERT INTO recompensa (descripcion, cantidad_minima, id_proyecto) VALUES
    ('Sticker exclusivo', 5.00, 1),
    ('Camiseta del proyecto', 20.00, 1),
    ('Obra firmada', 50.00, 2);

CREATE TABLE inversion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    correo_inversor VARCHAR(100),
    id_proyecto INT,
    cantidad DECIMAL(10, 2),
    fecha DATE DEFAULT (CURRENT_DATE),
    id_recompensa INT,
    FOREIGN KEY (correo_inversor) REFERENCES usuario(correo),
    FOREIGN KEY (id_proyecto) REFERENCES proyecto(id),
    FOREIGN KEY (id_recompensa) REFERENCES recompensa(id)
);

INSERT INTO inversion (correo_inversor, id_proyecto, cantidad, id_recompensa) VALUES
    ('inversor1@correo.com', 1, 15.00, 1);