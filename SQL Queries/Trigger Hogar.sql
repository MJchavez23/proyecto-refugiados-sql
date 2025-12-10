--crear hogar
CREATE OR REPLACE FUNCTION crearHogar (
a_hogar_tamano INTEGER,
a_fecha_llegada_pais DATE ,
a_fecha_llegada_refugio DATE
) RETURNS void AS 
$$
BEGIN 
INSERT INTO Hogar(
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
CREATE OR REPLACE FUNCTION leer_Hogar(a_id_hogar INTEGER)
RETURNS SETOF Hogar AS 
$$
BEGIN
SELECT * FROM Hogar WHERE id_hogar = a_id_hogar;
END;
$$ LANGUAGE plpgsql;

--borrar hogar
CREATE OR REPLACE FUNCTION borrar_Hogar(a_id_hogar INTEGER)
RETURNS void AS 
$$
BEGIN
DELETE FROM Hogar WHERE id_hogar = a_id_hogar;
END;
$$ LANGUAGE plpgsql;

--trigger hogar
CREATE OR REPLACE FUNCTION actualizarBitacora_hogar()
RETURNS TRIGGER AS
$$
DECLARE 
BEGIN
INSERT INTO Bitacora (
usuario
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
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_hogar
AFTER INSERT OR UPDATE OR DELETE ON Hogar
FOR EACH ROW
EXECUTE FUNCTION actualizarBitacora_hogar();
