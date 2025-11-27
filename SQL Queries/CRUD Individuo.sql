CREATE OR REPLACE FUNCTION crearIndividuo (
a_idhogar INTEGER,
a_nombre VARCHAR(10),
a_apellido VARCHAR(10),
a_genero char(1),
a_fecha_nacimiento date,
a_pais_origen VARCHAR(20),
a_idioma VARCHAR(20),
a_educacion VARCHAR(20),
a_telefono VARCHAR(10),
a_estatus_legal VARCHAR(20),
a_discapacidad VARCHAR(20), 
a_enfermedad_cronica VARCHAR(20),
a_embarazo BOOL,
a_estado_empleo BOOL
) RETURNS void AS 
$$
BEGIN 
INSERT INTO Individuo(
idhogar,
nombre,
apellido,
genero,
fecha_nacimiento,
pais_origen,
idioma,
educacion,
telefono,
estatus_legal,
discapacidad,
enfermedad_cronica,
embarazo,
estado_empleo
) VALUES(
a_idhogar,
a_nombre,
a_apellido,
a_genero,
a_fecha_nacimiento,
a_pais_origen,
a_idioma,
a_educacion,
a_telefono,
a_estatus_legal,
a_discapacidad,
a_enfermedad_cronica,
a_embarazo,
a_estado_empleo
);
END;
$$ language plpgsql;

CREATE OR REPLACE FUNCTION leer_individuo(opcion INTEGER, a_individuo INTEGER)
RETURNS SETOF Individuo AS 
$$
BEGIN
IF opcion = 1 THEN RETURN QUERY SELECT * FROM Individuo;
ELSEIF opcion = 2 THEN RETURN QUERY SELECT * FROM Individuo WHERE idindividuo = a_individuo;
END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION borrar_individuo(a_idindividuo INTEGER)
RETURNS void AS 
$$
BEGIN
DELETE FROM Individuo WHERE idindividuo = a_idindividuo;
END;

$$ LANGUAGE plpgsql;

--trigger para individuo
CREATE OR REPLACE FUNCTION actualizarBitacora_individuo()
RETURNS TRIGGER AS
$$
DECLARE 
BEGIN
INSERT INTO Bitacora (
idbitacora,
accion,
tabla,
fecha,
usuario
) 
VALUES(
nextval(),
TG_OP,
'Individuo',
CURRENT_DATE,
CURRENT_USER
);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_individuo
AFTER INSERT OR UPDATE OR DELETE ON Individuo
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_individuo();
