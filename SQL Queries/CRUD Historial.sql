--No se deben eliminar datos del historial medico

CREATE OR REPLACE FUNCTION crear_historial(
a_idindividuo INTEGER, 
a_idpersonal INTEGER,
a_fecha_servicio DATE, 
a_resultado VARCHAR(120),
a_seguimiento BOOL,
a_fecha_seguimiento DATE
)
RETURNS void AS 
$$
BEGIN INSERT INTO HistorialServicio (
idindividuo, 
idpersonal,
fecha_servicio, 
resultado,
seguimiento,
fecha_seguimiento
) 
VALUES (
a_idindividuo,
a_idpersonal,
a_fecha_servicio, 
a_resultado,
a_seguimiento,
a_fecha_seguimiento
);
END;
$$ LANGUAGE plpgsql;


