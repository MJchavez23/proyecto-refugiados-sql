

--trigger para historial
CREATE OR REPLACE FUNCTION actualizarBitacora_historial()
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
'Historial Servicios'
);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_historial
AFTER INSERT OR UPDATE OR DELETE ON Historial_Servicios
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_historial();




