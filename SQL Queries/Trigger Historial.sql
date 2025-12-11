--No se deben eliminar datos del historial medico

--Crear el historial 
CREATE OR REPLACE FUNCTION crear_historial(
a_id_servicio INTEGER,
a_id_individuo INTEGER,
a_id_personal INTEGER,
a_fecha_servicio DATE,
a_resultado VARCHAR(255),
a_seguimiento BOOLEAN,
fecha_seguimiento BOOLEAN
)
RETURNS void AS 
$$
BEGIN INSERT INTO Historial_Servicios (
id_individuo, 
id_personal,
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
CREATE OR REPLACE FUNCTION leer_Historial(a_id_historial INTEGER)
RETURNS SETOF Historial_Servicios AS 
$$
BEGIN
SELECT * FROM Historial_Servicios WHERE id_historial = a_id_historial;
END;
$$ LANGUAGE plpgsql;

--trigger para historial
CREATE OR REPLACE FUNCTION actualizarBitacora_historial()
RRETURNS TRIGGER AS
$$
DECLARE 
BEGIN
INSERT INTO Bitacora (
usuario
fecha,
accion,
tabla
) 
VALUES(
CURRENT_USER,
CURRENT_DATE,
TG_OP,
'Historial Servicios'
);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_historial
AFTER INSERT OR UPDATE OR DELETE ON Historial_Servicios
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_historial();

