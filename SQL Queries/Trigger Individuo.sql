

--trigger para individuo
CREATE OR REPLACE FUNCTION actualizarBitacora_individuo()
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
'Individuo'
);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_individuo
AFTER INSERT OR UPDATE OR DELETE ON Individuo
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_individuo();







