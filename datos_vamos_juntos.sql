
USE vamos_juntos;

SHOW TABLES;

DESC usuario;
DESC evento;
DESC coche;
DESC reserva

SELECT * FROM usuario;
SELECT * FROM evento;
SELECT * FROM coche;
SELECT * FROM reserva;

# USUARIO
INSERT INTO usuario(nombre, apellido, dni, email) VALUES("tomas enrique", "estrada torres", "43588075Q", "tomasenriquea@hotmail.com");
INSERT INTO usuario(nombre, apellido, dni, email) VALUES("luis", "lavado quispe", "25874634A", "luislavado@hotmail.com");
INSERT INTO usuario(nombre, apellido, dni, email) VALUES("esther", "galdeano", "985700254B", "ester@live.com");
INSERT INTO usuario(nombre, apellido, dni, email) VALUES("federica", "berti", "95368802C", "fede@gmail.com");
INSERT INTO usuario(nombre, apellido, dni, email) VALUES("raul", "garcia", "33669801D", "raulgarcia@hotmail.com");

# COCHE
INSERT INTO coche(telf_propietario, tipo_vehiculo, modelo, anyo, matricula, punto_salida, num_plazas_libres, num_plazas_ocupadas, info_complementaria_coche, id_usuario) 
VALUES("931789654", "furgoneta", "B1", "1990", "1001ABC", "valencia", 5, 2, "viaje de valiencia a madrid", 1);
 

# EVENTO
INSERT INTO evento(nombre_evento, recinto, ciudad, fecha, hora, path_imagen, info_complementaria_evento) 
VALUES("", "", "", '', '', "", "");


# RESERVA
INSERT INTO reserva(id_evento, id_coche) VALUES();













