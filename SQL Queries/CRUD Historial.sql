--No se deben eliminar datos del historial medico

--Crear el historial 
CREATE OR REPLACE FUNCTION crear_historial(
a_idservicio INTEGER,
a_idindividuo INTEGER,
a_idpersonal INTEGER,
a_fecha_servicio DATE,
a_resultado VARCHAR(120),
a_seguimiento BOOL,
fecha_seguimiento BOOL
)
RETURNS void AS 
$$
BEGIN INSERT INTO HistorialServicio (
idindividuo, 
idpersonal,
fecha_servicio, 
resultado,
seguimiento,
fecha_seguimiento
) 
VALUES (
a_idindividuo,
a_idpersonal,
a_fecha_servicio, 
a_resultado,
a_seguimiento,
a_fecha_seguimiento
);
END;
$$ LANGUAGE plpgsql;

--Leer el historial
CREATE OR REPLACE FUNCTION leer_Historial(a_idhistorial INTEGER)
RETURNS SETOF HistorialServicio AS 
$$
BEGIN
SELECT * FROM HistorialServicio WHERE idhistorial = a_idhistorial;
END;
$$ LANGUAGE plpgsql;

--trigger para historial
CREATE OR REPLACE FUNCTION actualizarBitacora_historial()
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
'HistorialServicio',
CURRENT_DATE,
CURRENT_USER
);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_historial
AFTER INSERT OR UPDATE OR DELETE ON Historial
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_historial();
