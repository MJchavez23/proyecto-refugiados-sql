--crear hogar
CREATE OR REPLACE FUNCTION crearHogar (
a_idrefugio INTEGER,
a_hogar_tamano INTEGER,
a_fecha_llegada_pais DATE ,
a_fecha_llegada_refugio DATE
) RETURNS void AS 
$$
BEGIN 
INSERT INTO Hogar(
idrefugio,
hogar_tamano,
fecha_llegada_pais,
fecha_llegada_refugio
) VALUES(
a_idrefugio,
a_hogar_tamano,
a_fecha_llegada_pais,
a_fecha_llegada_refugio
);
END;
$$ language plpgsql;

--leer hogar
CREATE OR REPLACE FUNCTION leer_Hogar(a_idhogar INTEGER)
RETURNS SETOF Hogar AS 
$$
BEGIN
SELECT * FROM Hogar WHERE idhogar = a_idhogar;
END;
$$ LANGUAGE plpgsql;

--borrar hogar
CREATE OR REPLACE FUNCTION borrar_Hogar(a_idhogar INTEGER)
RETURNS void AS 
$$
BEGIN
DELETE FROM Hogar WHERE idhogar = a_idhogar;
END;
$$ LANGUAGE plpgsql;

--trigger hogar
CREATE OR REPLACE TRIGGER actualizarBitacora_hogar()
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
