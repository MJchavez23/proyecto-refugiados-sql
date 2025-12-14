CREATE OR REPLACE FUNCTION guardarIndividuo(
	p_id_hogar INTEGER,
	p_nombre VARCHAR,
	p_apellido VARCHAR,
	p_genero VARCHAR,
	p_fecha_nacimiento DATE,
	p_pais_origen VARCHAR,
	p_idioma_principal VARCHAR,
	p_nivel_educativo VARCHAR,
	p_telefono VARCHAR,
	p_estatus_legal VARCHAR,
	p_tipo_documento VARCHAR,
	p_numero_documento VARCHAR,
	p_discapacidad VARCHAR,
	p_enfermedad_cronica VARCHAR,
	p_embarazada BOOLEAN,
	p_estado_empleo VARCHAR,
	p_representante_hogar BOOLEAN)
RETURNS void AS $$
	BEGIN
		INSERT INTO individuo(
		id_hogar, 
		nombre, 
		apellido, 
		genero, 
		fecha_nacimiento, 
		pais_origen, 
		idioma_principal,
		nivel_educativo,
		telefono,
		estatus_legal,
		tipo_documento,
		numero_documento,
		discapacidad,
		enfermedad_cronica,
		embarazada,
		estado_empleo,
		representante_hogar)
		VALUES (
		p_id_hogar, 
		p_nombre, 
		p_apellido, 
		p_genero, 
		p_fecha_nacimiento, 
		p_pais_origen, 
		p_idioma_principal,
		p_nivel_educativo,
		p_telefono,
		p_estatus_legal,
		p_tipo_documento,
		p_numero_documento,
		p_discapacidad,
		p_enfermedad_cronica,
		p_embarazada,
		p_estado_empleo,
		p_representante_hogar);
	END
$$LANGUAGE plpgsql


CREATE OR REPLACE FUNCTION buscarIndividuoPorNumeroDeDocumento(numeroDocumentoBuscar VARCHAR)
RETURNS TABLE (

    ind_id INTEGER,
    ind_nombre VARCHAR,
    apellido VARCHAR,
    genero VARCHAR,
    fecha_nacimiento DATE,
    pais_origen VARCHAR,
    idioma_principal VARCHAR,
    nivel_educativo VARCHAR,
    telefono VARCHAR,
    estatus_legal VARCHAR,
    tipo_documento VARCHAR,
    numero_documento VARCHAR,
    discapacidad VARCHAR,
    enfermedad_cronica VARCHAR,
    embarazada BOOLEAN,
    estado_empleo VARCHAR,
    representante_hogar BOOLEAN,
    
    hog_id INTEGER,
    fecha_llegada_refugio DATE,
    nombre_hogar VARCHAR,
    
    ref_id INTEGER,
    ref_nombre VARCHAR,
    ciudad VARCHAR,
    pais VARCHAR,
    referencia VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        i.id_individuo,      
        i.nombre,            
        i.apellido, 
        i.genero, 
        i.fecha_nacimiento, 
        i.pais_origen, 
        i.idioma_principal, 
        i.nivel_educativo, 
        i.telefono, 
        i.estatus_legal, 
        i.tipo_documento, 
        i.numero_documento, 
        i.discapacidad, 
        i.enfermedad_cronica, 
        i.embarazada, 
        i.estado_empleo, 
        i.representante_hogar, 
        h.id_hogar,
        h.fecha_llegada_refugio, 
        h.nombre_hogar, 
        r.id_refugio,        
        r.nombre,            
        r.ciudad, 
        r.pais, 
        r.referencia 
    FROM individuo i 
    JOIN hogar h ON i.id_hogar = h.id_hogar 
    JOIN refugio r ON h.id_refugio = r.id_refugio 
    WHERE i.numero_documento = numeroDocumentoBuscar;
END 
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION buscarTodosIndividuos()
RETURNS TABLE (
    
	ind_id INTEGER,
    ind_nombre VARCHAR,
    apellido VARCHAR,
    genero VARCHAR,
    fecha_nacimiento DATE,
    pais_origen VARCHAR,
    idioma_principal VARCHAR,
    nivel_educativo VARCHAR,
    telefono VARCHAR,
    estatus_legal VARCHAR,
    tipo_documento VARCHAR,
    numero_documento VARCHAR,
    discapacidad VARCHAR,
    enfermedad_cronica VARCHAR,
    embarazada BOOLEAN,
    estado_empleo VARCHAR,
    representante_hogar BOOLEAN,
    
    hog_id INTEGER,
    fecha_llegada_refugio DATE,
    nombre_hogar VARCHAR,
    
	ref_id INTEGER,
    ref_nombre VARCHAR,
    ciudad VARCHAR,
    pais VARCHAR,
    referencia VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        i.id_individuo,     
        i.nombre,            
        i.apellido, 
        i.genero, 
        i.fecha_nacimiento, 
        i.pais_origen, 
        i.idioma_principal, 
        i.nivel_educativo, 
        i.telefono, 
        i.estatus_legal, 
        i.tipo_documento, 
        i.numero_documento, 
        i.discapacidad, 
        i.enfermedad_cronica, 
        i.embarazada, 
        i.estado_empleo, 
        i.representante_hogar, 
        h.id_hogar,          
        h.fecha_llegada_refugio, 
        h.nombre_hogar, 
        r.id_refugio,        
        r.nombre,
        r.ciudad, 
        r.pais, 
        r.referencia 
    FROM individuo i 
    JOIN hogar h ON i.id_hogar = h.id_hogar 
    JOIN refugio r ON h.id_refugio = r.id_refugio;
END 
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION obtener_detalle_hogar(p_id_hogar INT)
RETURNS TABLE (
    id_hogar INT,
    ref_id_refugio INT,
    nombre_hogar VARCHAR,
    fecha_llegada_refugio DATE,
    nombre_refugio VARCHAR,
    ciudad VARCHAR,
    pais VARCHAR,
    referencia VARCHAR
) 
AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        h.id_hogar, 
        r.id_refugio AS ref_id_refugio, 
        h.nombre_hogar, 
        h.fecha_llegada_refugio, 
        r.nombre AS nombre_refugio, 
        r.ciudad, 
        r.pais, 
        r.referencia
    FROM hogar h 
    JOIN refugio r ON h.id_refugio = r.id_refugio 
    WHERE h.id_hogar = p_id_hogar;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION obtener_todos_hogares_detallados()
RETURNS TABLE (
    id_hogar INT,
    ref_id_refugio INT,
    nombre_hogar VARCHAR,
    fecha_llegada_refugio DATE,
    nombre_refugio VARCHAR,
    ciudad VARCHAR,
    pais VARCHAR,
    referencia VARCHAR
) 
AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        h.id_hogar, 
        r.id_refugio AS ref_id_refugio, 
        h.nombre_hogar, 
        h.fecha_llegada_refugio, 
        r.nombre AS nombre_refugio, 
        r.ciudad, 
        r.pais, 
        r.referencia
    FROM hogar h 
    JOIN refugio r ON h.id_refugio = r.id_refugio;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION buscarTodosBitacoras()
RETURNS TABLE (
    usuario_resultado VARCHAR,
    fecha_resultado TIMESTAMP,
    accion_resultado VARCHAR,
    tabla_resultado VARCHAR
) 
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        b.usuario, 
        b.fecha, 
        b.accion, 
        b.tabla
    FROM 
        bitacora b
    ORDER BY 
        b.fecha DESC;
END;
$$;

CREATE OR REPLACE FUNCTION guardarHistorialServicio(
    _id_servicio INTEGER,
    _id_personal INTEGER,
    _descripcion VARCHAR,
    _fecha_registro DATE
)
RETURNS VOID 
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO historial_servicios (
        id_servicio, 
        id_personal, 
        descripcion, 
        fecha_registro
    )
    VALUES (
        _id_servicio, 
        _id_personal, 
        _descripcion, 
        _fecha_registro
    );
END;
$$;

CREATE OR REPLACE FUNCTION historialPorServicioId(_id_servicio INTEGER)
RETURNS TABLE (
    id_historial_servicio INTEGER,
    id_servicio INTEGER,        
    id_personal INTEGER,
    descripcion VARCHAR,        
    fecha_registro DATE,

    nombre_servicio VARCHAR,
    id_hogar INTEGER,
    descripcion_servicio VARCHAR,
    estado_servicio VARCHAR
) 
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        hs.id_historial_servicio,
        hs.id_servicio,
        hs.id_personal,
        hs.descripcion,
        hs.fecha_registro,

        s.nombre_servicio,
        s.id_hogar,
        s.descripcion_servicio,
        s.estado_servicio
    FROM 
        historial_servicios hs
    INNER JOIN 
        servicios s ON hs.id_servicio = s.id_servicio
    WHERE 
        hs.id_servicio = _id_servicio
    ORDER BY 
        hs.fecha_registro DESC;
END;
$$;

CREATE OR REPLACE FUNCTION guardarServicio(
    _nombre_servicio VARCHAR,
    _id_hogar INTEGER,
    _descripcion_servicio VARCHAR,
    _estado_servicio VARCHAR
)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO servicios (
        nombre_servicio, 
        id_hogar, 
        descripcion_servicio, 
        estado_servicio
    )
    VALUES (
        _nombre_servicio, 
        _id_hogar, 
        _descripcion_servicio, 
        _estado_servicio
    );
END;
$$;

CREATE OR REPLACE FUNCTION buscarPorNombreHogar(
    _nombre_busqueda VARCHAR,
    _id_hogar_busqueda INTEGER
)
RETURNS TABLE (
    id_servicio INTEGER,
    nombre_servicio VARCHAR,
    id_hogar INTEGER,
    descripcion_servicio VARCHAR,
    estado_servicio VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        s.id_servicio,
        s.nombre_servicio,
        s.id_hogar,
        s.descripcion_servicio,
        s.estado_servicio
    FROM 
        servicios s
    WHERE 
        s.id_hogar = _id_hogar_busqueda
        AND s.nombre_servicio ILIKE _nombre_busqueda;
END;
$$;



