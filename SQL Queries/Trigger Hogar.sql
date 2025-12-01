--trigger hogar
CREATE OR REPLACE FUNCTION actualizarBitacora_hogar()
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
'Hogar',
CURRENT_DATE,
CURRENT_USER
);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_hogar
AFTER INSERT OR UPDATE OR DELETE ON Hogar
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_hogar();


