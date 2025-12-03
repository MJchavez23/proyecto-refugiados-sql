--Este Script es para crear las tablas

CREATE TABLE Refugio (
id_refugio SERIAL PRIMARY KEY,
nombre VARCHAR(50) NOT NULL, 
ciudad VARCHAR(20) NOT NULL,
pais VARCHAR(20) NOT NULL,
referencia VARCHAR(50) NOT NULL
);

CREATE TABLE Hogar (
id_hogar SERIAL PRIMARY KEY,
id_refugio INTEGER NOT NULL,
nombre_hogar VARCHAR(100) NOT NULL,
fecha_llegada_refugio DATE NOT NULL,
FOREIGN KEY(id_refugio) REFERENCES Refugio(id_refugio)
);

CREATE TABLE Individuo (
id_individuo SERIAL PRIMARY KEY,
id_hogar INTEGER,
nombre VARCHAR(10) NOT NULL,
apellido VARCHAR(10) NOT NULL,
genero VARCHAR(10) NOT NULL,
fecha_nacimiento DATE NOT NULL,
pais_origen VARCHAR(20) NOT NULL,
idioma_principal VARCHAR(20) NOT NULL,
nivel_educacion VARCHAR(20),
telefono VARCHAR(20),
estatus_legal VARCHAR(20),
tipo_documento VARCHAR(20),
numero_documento VARCHAR(20),
discapacidad VARCHAR(20), 
enfermedad_cronica VARCHAR(20),
embarazada BOOL,
estado_empleo VARCHAR(20),
representante_hogar BOOL,
FOREIGN KEY(id_hogar) REFERENCES Hogar(id_hogar)
);

CREATE TABLE Alojamiento (
id_habitacion SERIAL PRIMARY KEY,
id_refugio INTEGER NOT NULL,
id_hogar INTEGER NOT NULL,
fecha_ingreso DATE NOT NULL,
fecha_salida DATE,
FOREIGN KEY(id_refugio) REFERENCES Refugio(id_refugio),
FOREIGN KEY (id_hogar) REFERENCES Hogar(id_hogar)
);

CREATE TABLE Servicios (
id_servicio SERIAL PRIMARY KEY, 
tipo_servicio VARCHAR(40) NOT NULL,
area_servicio VARCHAR(40) NOT NULL
);

CREATE TABLE Personal (
id_personal SERIAL PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellido VARCHAR(20) NOT NULL,
fecha_nacimiento DATE NOT NULL,
genero VARCHAR(10) NOT NULL,
id_refugio INTEGER NOT NULL,
FOREIGN KEY(id_refugio) REFERENCES Refugio(id_refugio)
);

CREATE TABLE Historial_Servicio (
id_historial_servicio SERIAL PRIMARY KEY,
id_servicio INTEGER NOT NULL,
id_individuo INTEGER NOT NULL,
id_personal INTEGER NOT NULL,
fecha_servicio DATE NOT NULL, 
estado_servicio VARCHAR(120) NOT NULL,
descripcion VARCHAR(50) NOT NULL, 
ultima_modificacion DATE,
FOREIGN KEY (id_servicio) REFERENCES Servicios(id_servicio),
FOREIGN KEY(id_individuo) REFERENCES Individuo(id_individuo),
FOREIGN KEY (id_personal) REFERENCES Personal(id_personal)
);

CREATE TABLE Registro_Salud (
id_registro_salud SERIAL PRIMARY KEY,
id_individuo INTEGER NOT NULL,
fecha_registro DATE NOT NULL,
descripcion VARCHAR(40) NOT NULL,
FOREIGN KEY(id_individuo) REFERENCES Individuo(id_individuo)
);

CREATE TABLE Vacunas (
id_vacunas SERIAL PRIMARY KEY,
id_individuo INTEGER NOT NULL,
tipo_vacuna VARCHAR(20) NOT NULL,
FOREIGN KEY(id_individuo) REFERENCES Individuo(id_individuo)
);

CREATE TABLE Bitacora(
usuario VARCHAR(50),
fecha TIMESTAMP,
accion VARCHAR(50),
tabla VARCHAR(50)	
);


