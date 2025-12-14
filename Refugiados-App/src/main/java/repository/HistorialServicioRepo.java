package repository;

import lombok.AllArgsConstructor;
import model.HistorialServicio;
import model.Personal;
import model.Servicio;
import model.enums.TipoServicio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HistorialServicioRepo {

    private final Connection connection;

    public void guardarHistorialServicio(HistorialServicio historialServicio) throws SQLException {
        String query = "SELECT guardarHistorial(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        llenarStatement(preparedStatement, historialServicio);
        preparedStatement.execute();
    }

    public List<HistorialServicio> buscarHistorialesPorServicio(String idServicio) throws SQLException {
        String query = "SELECT * FROM historialPorServicioId(?)";

        List<HistorialServicio>  historialServicios = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idServicio);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            HistorialServicio hs = crearHistorial(resultSet);
            historialServicios.add(hs);
        }
        return historialServicios;
    }

    private HistorialServicio crearHistorial(ResultSet resultSet) throws SQLException {
        Servicio servicio = Servicio.builder()
                .id(resultSet.getInt("id_servicio"))
                .nombreServicio(TipoServicio.valueOf(resultSet.getString("nombre_servicio")))
                .descripcionServicio(resultSet.getString("descripcion_servicio"))
                .build();

        Personal personal = Personal.builder()
                .id(1)
                .nombre("Admin")
                .build();


        return HistorialServicio.builder()
                .id(resultSet.getInt("id_historial_servicio"))
                .personal(personal)
                .servicio(servicio)
                .descripcion(resultSet.getString("descripcion"))
                .fechaServicio(resultSet.getObject("fecha_registro", LocalDate.class))
                .build();
    }

    private void llenarStatement(PreparedStatement preparedStatement, HistorialServicio historialServicio) throws SQLException {
        preparedStatement.setInt(1, historialServicio.getServicio().getId()); //Unico servicio q se necesita
        preparedStatement.setInt(2, 1); //Solo hay id de personal valida(no cambiar)
        preparedStatement.setString(3, historialServicio.getDescripcion());
        preparedStatement.setObject(4, Date.valueOf(historialServicio.getFechaServicio()));
    }


}
