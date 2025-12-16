
--trigger hogar
CREATE OR REPLACE FUNCTION actualizarBitacora_hogar()
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
'Hogar'
);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_hogar
AFTER INSERT OR UPDATE OR DELETE ON Hogar
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_hogar();


