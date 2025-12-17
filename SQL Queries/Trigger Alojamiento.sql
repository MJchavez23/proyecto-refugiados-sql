--trigger alojamiento
CREATE OR REPLACE FUNCTION actualizarBitacora_alojamiento()
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
'Alojamiento'
);
RETURN NEW
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_alojamiento
AFTER INSERT OR UPDATE OR DELETE ON Alojamiento
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_alojamiento();



