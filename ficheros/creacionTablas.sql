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
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    categoria ENUM('TECNOLOGIA', 'ARTE', 'CINE', 'JUEGOS', 'COMIDA', 'MODA'),
    correo_gestor VARCHAR(100),
    FOREIGN KEY (correo_gestor) REFERENCES usuario(correo)
);

INSERT INTO proyecto (titulo, descripcion, categoria, correo_gestor) VALUES
    ('Proyecto Alpha', 'Un proyecto sobre tecnología avanzada.', 'TECNOLOGIA', 'gestor1@correo.com'),
    ('Proyecto Arte V', 'Proyecto visual contemporáneo.', 'ARTE', 'gestor1@correo.com');

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