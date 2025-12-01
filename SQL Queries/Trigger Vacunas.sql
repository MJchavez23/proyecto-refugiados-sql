--trigger vacunas
CREATE OR REPLACE FUNCTION actualizarBitacora_vacunas()
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
'Vacunas',
CURRENT_DATE,
CURRENT_USER
);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_vacunas
AFTER INSERT OR UPDATE OR DELETE ON Vacunas
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_vacunas();
