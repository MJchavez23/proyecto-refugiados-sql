--Conjunto de datos para la base de datos
INSERT INTO Refugio (nombre, ciudad, pais, referencia) VALUES
('Refugio Esperanza', 'Guayaquil', 'Ecuador', 'Cerca del Malecón 2000'),
('Albergue Solidario', 'Bogotá', 'Colombia', 'Carrera 15 #45-20'),
('Hogar de Acogida San Martín', 'Lima', 'Perú', 'Av. Argentina 345'),
('Refugio Bonaerense', 'Buenos Aires', 'Argentina', 'Calle Florida 123'),
('Albergue Andino', 'Quito', 'Ecuador', 'Av. Amazonas N45-10'),
('Casa Refugio Valle', 'Cali', 'Colombia', 'Calle 5 #22-35');

INSERT INTO Hogar (id_refugio, nombre_hogar, fecha_llegada_refugio) VALUES
(1, 'Familia Pérez', '2023-03-15'),
(2, 'Familia Rodríguez', '2023-04-20'),
(3, 'Familia Gómez', '2023-05-10'),
(4, 'Familia Fernández', '2023-06-01'),
(5, 'Familia López', '2023-07-12'),
(6, 'Familia Martínez', '2023-08-05');

INSERT INTO Individuo (id_hogar, nombre, apellido, genero, fecha_nacimiento, pais_origen, idioma_principal, nivel_educativo, telefono, estatus_legal, tipo_documento, numero_documento, discapacidad, enfermedad_cronica, embarazada, estado_empleo, representante_hogar) VALUES
-- Hogar 1 (Familia Pérez en Guayaquil)
(1, 'Carlos', 'Pérez', 'MASCULINO', '1980-05-15', 'Venezuela', 'Español', 'EGB', '+593987654321', 'REFUGIADO', 'PASAPORTE', 'V12345678', NULL, 'Hipertensión', false, 'DESEMPLEADO', true),
(1, 'María', 'Pérez', 'FEMENINO', '1985-08-22', 'Venezuela', 'Español', 'BACHILLERATO', '+593987654322', 'REFUGIADO', 'PASAPORTE', 'V12345679', NULL, NULL, true, 'DESEMPLEADO', false),
(1, 'Pedro', 'Pérez', 'MASCULINO', '2015-03-10', 'Venezuela', 'Español', 'SUPERIOR', NULL, 'REFUGIADO', 'CEDULA_IDENTIDAD', 'CN-001', NULL, NULL, false, NULL, false),

-- Hogar 2 (Familia Rodríguez en Bogotá)
(2, 'Juan', 'Rodríguez', 'MASCULINO', '1978-11-30', 'Venezuela', 'Español', 'SUPERIOR', '+57123456789', 'REFUGIADO', 'PASAPORTE', 'V87654321', 'Visual', NULL, false, 'Temporal', true),
(2, 'Ana', 'Rodríguez', 'FEMENINO', '1982-02-14', 'Venezuela', 'Español', 'EGB', '+57123456790', 'REFUGIADO', 'PASAPORTE', 'V87654322', NULL, 'Diabetes', false, 'DESEMPLEADO', false),

-- Hogar 3 (Familia Gómez en Lima)
(3, 'Luis', 'Gómez', 'MASCULINO', '1990-07-08', 'Colombia', 'Español', 'EGB', '+511987654321', 'REFUGIADO', 'CEDULA_IDENTIDAD', 'COL-123456', NULL, NULL, false, 'DEPENDENCIA', true),
(3, 'Carmen', 'Gómez', 'FEMENINO', '1992-12-25', 'Colombia', 'Español', 'SUPERIOR', '+511987654322', 'REFUGIADO', 'CEDULA_IDENTIDAD', 'COL-123457', NULL, NULL, false, 'DEPENDENCIA', false),

-- Hogar 4 (Familia Fernández en Buenos Aires)
(4, 'Diego', 'Fernández', 'MASCULINO', '1988-04-18', 'Venezuela', 'Español', 'BACHILLERATO', '+541112345678', 'REFUGIADO', 'PASAPORTE', 'V11223344', NULL, NULL, false, 'DESEMPLEADO', true),
(4, 'Laura', 'Fernández', 'FEMENINO', '1991-09-05', 'Venezuela', 'Español', 'SUPERIOR', '+541112345679', 'REFUGIADO', 'PASAPORTE', 'V11223345', NULL, 'Asma', false, 'Temporal', false),

-- Hogar 5 (Familia López en Quito)
(5, 'Roberto', 'López', 'MASCULINO', '1975-01-20', 'Colombia', 'Español', 'SUPERIOR', '+593912345678', 'REFUGIADO', 'PASAPORTE', 'COL-987654', 'Motriz', NULL, false, 'DESEMPLEADO', true),
(5, 'Sofía', 'López', 'FEMENINO', '2018-06-30', 'Colombia', 'Español', 'SUPERIOR', NULL, 'REFUGIADO', 'LICENCIA_CONDUCIR', 'CN-002', NULL, NULL, false, 'DEPENDENCIA', false),

-- Hogar 6 (Familia Martínez en Cali)
(6, 'Miguel', 'Martínez', 'MASCULINO', '1983-03-12', 'Venezuela', 'Español', 'EGB', '+57212345678', 'REFUGIADO', 'LICENCIA_CONDUCIR', 'V55667788', NULL, NULL, false, 'DEPENDENCIA', true);

INSERT INTO Alojamiento (id_refugio, id_hogar, fecha_ingreso, fecha_salida) VALUES
(1, 1, '2023-03-15', NULL),
(2, 2, '2023-04-20', NULL),
(3, 3, '2023-05-10', NULL),
(4, 4, '2023-06-01', NULL),
(5, 5, '2023-07-12', NULL),
(6, 6, '2023-08-05', NULL);

INSERT INTO Servicios (nombre_servicio, id_hogar, descripcion_servicio, estado_servicio) VALUES
('RESIDENCIA_TEMPORAL', 1, 'Proceso de solicitud de refugio', 'COMPLETADO'),
('RESIDENCIA_TEMPORAL', 1, 'Control prenatal para María Pérez', 'CANCELADO'),
('CLASES_IDIOMAS', 2, 'Curso de portugués básico', 'EN_PROGESO'),
('ORIENTACION_LABORAL', 3, 'Búsqueda de empleo formal', 'PENDIENTE'),
('APOYO_PSICOLOGICO', 4, 'Sesiones de terapia semanal', 'PENDIENTE')

INSERT INTO Personal (nombre, apellido, fecha_nacimiento, genero, id_refugio) VALUES
('Ana', 'García', '1990-04-15', 'FEMENINO', 1),
('Carlos', 'Mendoza', '1985-07-22', 'MASCULINO', 2),
('Lucía', 'Torres', '1992-11-30', 'FEMENINO', 3),
('Ricardo', 'Fernández', '1988-03-18', 'MASCULINO', 4),
('Patricia', 'Vargas', '1995-09-10', 'FEMENINO', 5),
('Andrés', 'Silva', '1980-12-05', 'MASCULINO', 6);

INSERT INTO Historial_Servicios (id_servicio, id_personal, descripcion, fecha_registro) VALUES
(1, 1, 'Inició trámite de refugio', '2023-03-20'),
(2, 1, 'Control médico prenatal realizado', '2023-04-10'),
(3, 2, 'Inició curso de portugués', '2023-05-05'),
(4, 3, 'Entrevista laboral programada', '2023-06-15'),
(5, 4, 'Primera sesión de terapia', '2023-07-01'),
(6, 5, 'Solicitud de ayuda alimentaria', '2023-08-10'),
(7, 6, 'Taller finalizado con éxito', '2023-09-05'),
(8, 2, 'Documentación escolar entregada', '2023-09-20');

INSERT INTO Registro_Salud (id_individuo, fecha_registro, descripcion) VALUES
(2, '2023-04-10', 'Control prenatal - 4 meses'),
(4, '2023-05-15', 'Control de diabetes - estable'),
(9, '2023-06-20', 'Control de asma - mejora'),
(1, '2023-07-05', 'Control de hipertensión - normal'),
(5, '2023-08-12', 'Consulta general - saludable'),
(11, '2023-09-18', 'Revisión discapacidad motriz');

INSERT INTO Vacunas (id_individuo, tipo_vacuna) VALUES
(3, 'Pentavalente'),
(3, 'Polio'),
(5, 'COVID-19'),
(7, 'COVID-19'),
(8, 'COVID-19'),
(10, 'Pentavalente'),
(10, 'Hepatitis B'),
(12, 'COVID-19');
