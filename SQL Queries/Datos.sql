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
(1, 'Carlos', 'Pérez', 'Masculino', '1980-05-15', 'Venezuela', 'Español', 'Universitario', '+593987654321', 'Refugiado', 'Pasaporte', 'V12345678', NULL, 'Hipertensión', false, 'Desempleado', true),
(1, 'María', 'Pérez', 'Femenino', '1985-08-22', 'Venezuela', 'Español', 'Secundaria', '+593987654322', 'Refugiado', 'Pasaporte', 'V12345679', NULL, NULL, true, 'Desempleada', false),
(1, 'Pedro', 'Pérez', 'Masculino', '2015-03-10', 'Venezuela', 'Español', 'Primaria', NULL, 'Refugiado', 'Certificado Nac', 'CN-001', NULL, NULL, false, NULL, false),

-- Hogar 2 (Familia Rodríguez en Bogotá)
(2, 'Juan', 'Rodríguez', 'Masculino', '1978-11-30', 'Venezuela', 'Español', 'Universitario', '+57123456789', 'Solicitante', 'Pasaporte', 'V87654321', 'Visual', NULL, false, 'Temporal', true),
(2, 'Ana', 'Rodríguez', 'Femenino', '1982-02-14', 'Venezuela', 'Español', 'Técnico', '+57123456790', 'Solicitante', 'Pasaporte', 'V87654322', NULL, 'Diabetes', false, 'Desempleada', false),

-- Hogar 3 (Familia Gómez en Lima)
(3, 'Luis', 'Gómez', 'Masculino', '1990-07-08', 'Colombia', 'Español', 'Secundaria', '+511987654321', 'Regularizado', 'Cédula', 'COL-123456', NULL, NULL, false, 'Empleado', true),
(3, 'Carmen', 'Gómez', 'Femenino', '1992-12-25', 'Colombia', 'Español', 'Universitaria', '+511987654322', 'Regularizado', 'Cédula', 'COL-123457', NULL, NULL, false, 'Empleada', false),

-- Hogar 4 (Familia Fernández en Buenos Aires)
(4, 'Diego', 'Fernández', 'Masculino', '1988-04-18', 'Venezuela', 'Español', 'Técnico', '+541112345678', 'Refugiado', 'Pasaporte', 'V11223344', NULL, NULL, false, 'Desempleado', true),
(4, 'Laura', 'Fernández', 'Femenino', '1991-09-05', 'Venezuela', 'Español', 'Universitaria', '+541112345679', 'Refugiado', 'Pasaporte', 'V11223345', NULL, 'Asma', false, 'Temporal', false),

-- Hogar 5 (Familia López en Quito)
(5, 'Roberto', 'López', 'Masculino', '1975-01-20', 'Colombia', 'Español', 'Universitario', '+593912345678', 'Solicitante', 'Pasaporte', 'COL-987654', 'Motriz', NULL, false, 'Desempleado', true),
(5, 'Sofía', 'López', 'Femenino', '2018-06-30', 'Colombia', 'Español', NULL, NULL, 'Solicitante', 'Certificado Nac', 'CN-002', NULL, NULL, false, NULL, false),

-- Hogar 6 (Familia Martínez en Cali)
(6, 'Miguel', 'Martínez', 'Masculino', '1983-03-12', 'Venezuela', 'Español', 'Secundaria', '+57212345678', 'Regularizado', 'Pasaporte', 'V55667788', NULL, NULL, false, 'Empleado', true);

INSERT INTO Alojamiento (id_refugio, id_hogar, fecha_ingreso, fecha_salida) VALUES
(1, 1, '2023-03-15', NULL),
(2, 2, '2023-04-20', NULL),
(3, 3, '2023-05-10', NULL),
(4, 4, '2023-06-01', NULL),
(5, 5, '2023-07-12', NULL),
(6, 6, '2023-08-05', NULL);

INSERT INTO Servicios (nombre_servicio, id_hogar, descripcion_servicio, estado_servicio) VALUES
('Asesoría Legal', 1, 'Proceso de solicitud de refugio', 'En proceso'),
('Atención Médica', 1, 'Control prenatal para María Pérez', 'Completado'),
('Clases de Idiomas', 2, 'Curso de portugués básico', 'Activo'),
('Orientación Laboral', 3, 'Búsqueda de empleo formal', 'Activo'),
('Apoyo Psicológico', 4, 'Sesiones de terapia semanal', 'Activo'),
('Ayuda Alimentaria', 5, 'Entrega de canasta básica mensual', 'Pendiente'),
('Curso de Capacitación', 6, 'Taller de informática básica', 'Completado'),
('Asistencia Educativa', 2, 'Inscripción escolar para niños', 'En proceso');

INSERT INTO Personal (nombre, apellido, fecha_nacimiento, genero, id_refugio) VALUES
('Ana', 'García', '1990-04-15', 'Femenino', 1),
('Carlos', 'Mendoza', '1985-07-22', 'Masculino', 2),
('Lucía', 'Torres', '1992-11-30', 'Femenino', 3),
('Ricardo', 'Fernández', '1988-03-18', 'Masculino', 4),
('Patricia', 'Vargas', '1995-09-10', 'Femenino', 5),
('Andrés', 'Silva', '1980-12-05', 'Masculino', 6);

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
