 plpgsql;

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


