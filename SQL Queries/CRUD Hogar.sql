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

CREATE OR REPLACE FUNCTION leer_Hogar(opcion INTEGER, a_hogar INTEGER)
RETURNS SETOF Hogar AS 
$$
BEGIN
IF opcion = 1 THEN RETURN QUERY SELECT * FROM Hogar;
ELSEIF opcion = 2 THEN RETURN QUERY SELECT * FROM Hogar WHERE idhogar = a_hogar;
END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION borrar_Hogar(a_idhogar INTEGER)
RETURNS void AS 
$$
BEGIN
DELETE FROM Hogar WHERE idhogar = a_idhogar;
END;
$$ LANGUAGE plpgsql;