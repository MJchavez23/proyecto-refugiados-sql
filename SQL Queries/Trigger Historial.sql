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
AFTER INSERT OR UPDATE OR DELETE ON HistorialServicio
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_historial();


