package repository;


import lombok.AllArgsConstructor;
import model.Hogar;
import model.Individuo;
import model.Refugio;
import model.enums.EstadoEmpleo;
import model.enums.EstatusLegal;
import model.enums.NivelEducacion;
import model.enums.TipoDocumento;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
public class IndividuoRepo {

    private final Connection connection;

    public Individuo guardarIndividuo(Individuo individuo) {
        String query = "INSERT INTO individuo" +
                "(id_hogar, nombre, apellido, genero, fecha_nacimiento, pais_origen, idioma_principal, nivel_educativo, telefono, estatus_legal, tipo_documento, numero_documento, discapacidad, enfermedad_cronica, embarazada, estado_empleo, representante_hogar)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Preparamos el query

        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            llenarStatement(statement, individuo); //Ingresa los valores del individuo dentro de statement

            statement.executeUpdate(); //Ejecutamos el guardado

            try(ResultSet idGenerado =  statement.getGeneratedKeys()) { //Obtenemos el id generado y lo seteamos en el modelo
                if (idGenerado.next()) {
                    individuo.setId(idGenerado.getInt(1));
                }
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return individuo;
    }

    public Optional<Individuo> buscarIndividuoPorTipoDocumentYNumero(String tipoDocumento, String numeroDocumento ) {
        String query = "SELECT " +
        "i.id_individuo AS ind_id, i.nombre AS ind_nombre, i.apellido, i.genero, " +
        "i.fecha_nacimiento, i.pais_origen, i.idioma_principal, i.nivel_educacion, " +
        "i.telefono, i.estatus_legal, i.tipo_documento, i.numero_documento, " +
        "i.discapacidad, i.enfermedad_cronica, i.embarazada, i.estado_empleo, i.representante_hogar, " +

        "h.id_hogar AS hog_id, h.fecha_llegada_refugio, h.nombre_hogar ," +

        "r.id_refugio AS ref_id, r.nombre AS ref_nombre, r.ciudad, r.pais, r.referencia " +

        "FROM individuo i " +
        "JOIN hogar h ON i.id_hogar = h.id_hogar " +
        "JOIN refugio r ON h.id_refugio = r.id_refugio " +
        "WHERE tipo_documento = ? AND numero_documento = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){

            //Prepara el statement
            statement.setString(1,tipoDocumento);
            statement.setString(2,numeroDocumento);

            //Ejecuta el query y creamos el Individuo en base al ResultSet
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Individuo individuo = crearIndividuo(rs);
                    return Optional.of(individuo);
                }
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Individuo> buscarIndividuoPorNumeroDocumento(String numeroDocumento) {
        String query = "SELECT " +
        "i.id_individuo AS ind_id, i.nombre AS ind_nombre, i.apellido, i.genero, " +
        "i.fecha_nacimiento, i.pais_origen, i.idioma_principal, i.nivel_educacion, " +
        "i.telefono, i.estatus_legal, i.tipo_documento, i.numero_documento, " +
        "i.discapacidad, i.enfermedad_cronica, i.embarazada, i.estado_empleo, i.representante_hogar, " +

        "h.id_hogar AS hog_id, h.fecha_llegada_refugio, h.nombre_hogar, " +

        "r.id_refugio AS ref_id, r.nombre AS ref_nombre, r.ciudad, r.pais, r.referencia " +

        "FROM individuo i " +
        "JOIN hogar h ON i.id_hogar = h.id_hogar " +
        "JOIN refugio r ON h.id_refugio = r.id_refugio " +
        "WHERE i.numero_documento = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1,numeroDocumento);

            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Individuo individuo = crearIndividuo(rs);
                    return Optional.of(individuo);
                }
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    private Individuo crearIndividuo(ResultSet rs) throws SQLException {
        Refugio refugio = Refugio.builder()
                .id(rs.getInt("ref_id"))
                .nombre(rs.getString("ref_nombre"))
                .ciudad(rs.getString("ciudad"))
                .pais(rs.getString("pais"))
                .referenciaUbicacion(rs.getString("referencia_ubicacion"))

                .build();

        Hogar hogar = Hogar.builder()
                .id(rs.getInt("id_hogar"))
                .refugio(refugio)
                .nombreHogar(rs.getString("nombre_hogar"))
                .fechaLlegada(rs.getObject("fecha_llegada_refugio", LocalDate.class))
                .build();

        return Individuo.builder()
                .id(rs.getInt("ind_id"))
                .hogar(hogar)
                .nombre(rs.getString("ind_nombre"))
                .apellido(rs.getString("apellido"))
                .genero(rs.getString("genero"))
                .fechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class)) //Convierte el tipo SQL.Date a util.LocalDate
                .paisOrigen(rs.getString("pais_origen"))
                .idiomaPrincipal(rs.getString("idioma_principal"))
                .nivelEducacion(NivelEducacion.valueOf(rs.getString("nivel_educacion")))
                .telefono(rs.getString("telefono"))
                .estatusLegal(EstatusLegal.valueOf(rs.getString("estatus_legal")))
                .tipoDocumento(TipoDocumento.valueOf(rs.getString("tipo_documento")))
                .numeroDocumento(rs.getString("numero_documento"))
                .discapacidad(rs.getString("discapacidad"))
                .enfermedadCronica(rs.getString("enfermedad_cronica"))
                .embarazada(rs.getBoolean("embarazada"))
                .estadoEmpleo(EstadoEmpleo.valueOf(rs.getString("estado_empleado")))
                .representanteHogar(rs.getBoolean("representante_hogar"))
                .build();
    }

    private void llenarStatement(PreparedStatement statement, Individuo individuo) throws SQLException {
            statement.setInt(1, individuo.getHogar().getId());
            statement.setString(2, individuo.getNombre());
            statement.setString(3, individuo.getApellido());
            statement.setString(4, individuo.getGenero());
            statement.setObject(5, Date.valueOf(individuo.getFechaNacimiento()));
            statement.setString(6, individuo.getPaisOrigen());
            statement.setString(7, individuo.getIdiomaPrincipal());
            statement.setString(8, individuo.getNivelEducacion().name());
            statement.setString(9, individuo.getTelefono());
            statement.setString(10, individuo.getEstatusLegal().name());
            statement.setString(11, individuo.getTipoDocumento().name());
            statement.setString(12, individuo.getNumeroDocumento());
            statement.setString(13, individuo.getDiscapacidad());
            statement.setString(14, individuo.getEnfermedadCronica());
            statement.setBoolean(15, individuo.getEmbarazada());
            statement.setString(16, individuo.getEstadoEmpleo().name());
            statement.setBoolean(17, individuo.getRepresentanteHogar());
    }
}
