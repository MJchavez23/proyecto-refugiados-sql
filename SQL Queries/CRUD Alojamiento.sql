--trigger alojamiento
CREATE OR REPLACE FUNCTION actualizarBitacora_alojamiento()
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
'Alojamiento',
CURRENT_DATE,
CURRENT_USER
);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_alojamiento
AFTER INSERT OR UPDATE OR DELETE ON Alojamiento
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_alojamiento();
