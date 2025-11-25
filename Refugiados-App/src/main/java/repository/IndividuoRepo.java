package repository;


import lombok.AllArgsConstructor;
import model.Individuo;

import java.sql.*;
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
            statement.setString(10, individuo.getEstatusLegal());
            statement.setString(11, individuo.getTipoDocumento().name());
            statement.setString(12, individuo.getNumeroDocumento());
            statement.setString(13, individuo.getDiscapacidad());
            statement.setString(14, individuo.getEnfermedadCronica());
            statement.setBoolean(15, individuo.getEmbarazada());
            statement.setString(16, individuo.getEstadoEmpleo().name());
            statement.setBoolean(17, individuo.getRepresentanteHogar());
    }
}
