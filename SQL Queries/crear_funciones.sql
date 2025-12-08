

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
        h.id_hogar,          -- Se mapea a hog_id
        h.fecha_llegada_refugio, 
        h.nombre_hogar, 
        r.id_refugio,        -- Se mapea a ref_id
        r.nombre,            -- Se mapea a ref_nombre
        r.ciudad, 
        r.pais, 
        r.referencia 
    FROM individuo i 
    JOIN hogar h ON i.id_hogar = h.id_hogar 
    JOIN refugio r ON h.id_refugio = r.id_refugio;
END 
$$ LANGUAGE plpgsql;





