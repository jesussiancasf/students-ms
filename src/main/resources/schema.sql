CREATE TABLE IF NOT EXISTS estudiantes (
    id VARCHAR PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    estado VARCHAR(20),
    edad INT
);

INSERT INTO estudiantes (id, nombre, apellido, estado, edad)
VALUES ('1', 'Luis', 'Siancas', 'activo', 20);