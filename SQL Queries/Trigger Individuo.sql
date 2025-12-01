--trigger para individuo
CREATE OR REPLACE FUNCTION actualizarBitacora_individuo()
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
'Individuo',
CURRENT_DATE,
CURRENT_USER
);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_individuo
AFTER INSERT OR UPDATE OR DELETE ON Individuo
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_individuo();

