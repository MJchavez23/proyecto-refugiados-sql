package repository;


import lombok.AllArgsConstructor;
import model.Individuo;
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

    public Optional<Individuo> guardarIndividuo(Individuo individuo) {
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

            return Optional.empty();
        }
        return Optional.of(individuo);
    }

    public Optional<Individuo> buscarIndividuo(TipoDocumento tipoDocumento, String numeroDocumento ) {
        String query = "SELECT * FROM individuo WHERE tipo_documento = ? AND numero_documento = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){

            //Prepara el statement
            statement.setString(1,tipoDocumento.name());
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

    private Individuo crearIndividuo(ResultSet rs) throws SQLException {
        return Individuo.builder()
                .id(rs.getInt("id_individuo"))
                .nombre(rs.getString("nombre"))
                .apellido(rs.getString("apellido"))
                .genero(rs.getString("genero"))
                .fechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class)) //Convierte el tipo SQL.Date a util.LocalDate
                .pais_origen(rs.getString("pais_origen"))
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
            statement.setString(6, individuo.getPais_origen());
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
