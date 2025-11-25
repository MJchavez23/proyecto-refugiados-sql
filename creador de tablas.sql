--Este Script es para crear las tablas

CREATE TABLE Refugio (
idrefugio SERIAL PRIMARY KEY,
nombre_refugio VARCHAR(50) NOT NULL, 
ciudad_refugio VARCHAR(20) NOT NULL
);

CREATE TABLE Hogar (
idhogar SERIAL PRIMARY KEY,
idrefugio INTEGER NOT NULL,
hogar_tamano INTEGER NOT NULL, 
fecha_llegada_pais DATE NOT NULL,
fecha_llegada_refugio DATE NOT NULL,
FOREIGN KEY(idrefugio) REFERENCES Refugio(idrefugio)
);

CREATE TABLE Individuo (
idindividuo SERIAL PRIMARY KEY,
idhogar INTEGER,
nombre VARCHAR(10) NOT NULL,
apellido VARCHAR(10) NOT NULL,
genero char(1) NOT NULL,
fecha_nacimiento date NOT NULL,
pais_origen VARCHAR(20) NOT NULL,
idioma VARCHAR(20) NOT NULL,
educacion VARCHAR(20),
telefono VARCHAR(10),
estatus_legal VARCHAR(20),
discapacidad VARCHAR(20), 
enfermedad_cronica VARCHAR(20),
embarazo BOOL,
estado_empleo BOOL,
FOREIGN KEY(idhogar) REFERENCES Hogar(idhogar)
);

CREATE TABLE Alojamiento (
idhabitacion SERIAL PRIMARY KEY,
idrefugio INTEGER NOT NULL,
idhogar INTEGER NOT NULL,
fecha_ingreso DATE NOT NULL,
fecha_salida DATE,
FOREIGN KEY(idrefugio) REFERENCES Refugio(idrefugio),
FOREIGN KEY (idhogar) REFERENCES Hogar(idhogar)
);

CREATE TABLE Servicio (
idservicio SERIAL PRIMARY KEY, 
servicio VARCHAR(40) NOT NULL,
tipo VARCHAR(40) NOT NULL
);

CREATE TABLE Personal (
idpersonal SERIAL PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellido VARCHAR(20) NOT NULL,
fecha_nacimiento DATE NOT NULL,
genero CHAR(1) NOT NULL,
idrefugio INTEGER NOT NULL,
FOREIGN KEY(idrefugio) REFERENCES Refugio(idrefugio)
);

CREATE TABLE HistorialServicio (
idservicio INTEGER NOT NULL, 
idindividuo INTEGER NOT NULL, 
idpersonal INTEGER NOT NULL,
fecha_servicio DATE NOT NULL, 
resultado VARCHAR(120) NOT NULL,
seguimiento BOOL,
fecha_seguimiento DATE,
FOREIGN KEY (idservicio) REFERENCES Servicio(idservicio),
FOREIGN KEY(idindividuo) REFERENCES Individuo(idindividuo),
FOREIGN KEY (idpersonal) REFERENCES Personal(idpersonal)
);

CREATE TABLE SaludRegistro (
idregistro SERIAL PRIMARY KEY,
idindividuo INTEGER NOT NULL,
fecha_registro DATE NOT NULL,
medicamento VARCHAR(40) NOT NULL,
FOREIGN KEY(idindividuo) REFERENCES Individuo(idindividuo)
);

CREATE TABLE Vacunas (
idvacuna SERIAL PRIMARY KEY,
nombre_vacuna VARCHAR(20) NOT NULL,
enfermedad VARCHAR(20) NOT NULL,
idindividuo INTEGER NOT NULL,
FOREIGN KEY(idindividuo) REFERENCES Individuo(idindividuo)
);
