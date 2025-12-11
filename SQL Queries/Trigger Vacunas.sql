--crear vacunas
CREATE OR REPLACE FUNCTION crear_vacuna(
a_id_individuo INTEGER,
a_tipo_vacuna VARCHAR(20)
)
RETURNS void AS
$$
BEGIN
INSERT INTO Vacunas(
id_individuo,
tipo_vacuna
) VALUES(
a_id_individuo,
a_tipo_vacuna
);
END;
$$ LANGUAGE plpgsql;

--leer vacunas
CREATE OR REPLACE FUNCTION leer_vacunas(a_id_vacunas INTEGER)
RETURNS SETOF Vacunas AS
$$
BEGIN 
SELECT * FROM Vacunas WHERE id_vacunas = a_id_vacunas;
END;
$$ LANGUAGE plpgsql;

--borrar vacunas
CREATE OR REPLACE FUNCTION borrar_vacunas(a_id_vacunas INTEGER)
RETURNS void AS 
$$
BEGIN
DELETE FROM Vacunas WHERE id_vacunas = a_id_vacunas;
END;
$$ LANGUAGE plpgsql;

--trigger vacunas
CREATE OR REPLACE FUNCTION actualizarBitacora_vacunas()
RETURNS TRIGGER AS
$$
DECLARE 
BEGIN
INSERT INTO Bitacora (
usuario,
fecha,
accion,
tabla
) 
VALUES(
CURRENT_USER,
CURRENT_DATE,
TG_OP,
'Vacunas'
);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_vacunas
AFTER INSERT OR UPDATE OR DELETE ON Vacunas
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_vacunas();

