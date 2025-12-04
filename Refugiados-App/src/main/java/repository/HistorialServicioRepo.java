package repository;

import lombok.AllArgsConstructor;
import model.HistorialServicio;

import java.sql.*;

@AllArgsConstructor
public class HistorialServicioRepo {

    private final Connection connection;

    public HistorialServicio guardarHistorialServicio(HistorialServicio historialServicio) throws SQLException {
        String query = "INSERT INTO historial_servicio(id_servicio, id_hogar, id_personal, fecha_servicio, estado_servicio, descripcion, ultima_modificacion)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement statementLleno = llenarStatement(preparedStatement, historialServicio);
        statementLleno.executeUpdate();
        try (ResultSet idGenerado = statementLleno.getGeneratedKeys()) {
            historialServicio.setId(idGenerado.getInt(1));
        }
        return historialServicio;
    }

    private PreparedStatement llenarStatement(PreparedStatement preparedStatement, HistorialServicio historialServicio) throws SQLException {
        preparedStatement.setInt(1, 1); //Unico que servicio q se necesita
        preparedStatement.setInt(2, historialServicio.getHogar().getId());
        preparedStatement.setInt(3, 1); //Solo hay id de personal valida(no cambiar)
        preparedStatement.setObject(4, Date.valueOf(historialServicio.getFechaServicio()));
        preparedStatement.setString(5, historialServicio.getEstadoServicio().name());
        preparedStatement.setString(6, historialServicio.getDescripcion());
        preparedStatement.setObject(7, Date.valueOf(historialServicio.getUltimaModificacion()));
        return preparedStatement;
    }


}
