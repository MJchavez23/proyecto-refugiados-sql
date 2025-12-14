package repository;

import lombok.RequiredArgsConstructor;
import model.Servicio;
import model.enums.EstadoServicio;
import model.enums.TipoServicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class ServicioRepo {
    private final Connection connection;

    public void guardarServicio(Servicio servicio) throws SQLException {
        String query = "SELECT guardarServicio(?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        llenarStatement(statement, servicio);
        statement.execute();
    }


    public Optional<Servicio> buscarServicioPorNombreYHogar(String nombre, int idHogar) throws SQLException {
        String query = "SELECT buscarPorNombreHogar(?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nombre);
        statement.setInt(2, idHogar);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            Servicio servicio = crearServicio(resultSet);
            return Optional.of(servicio);
        }
        return Optional.empty();
    }

    public Servicio crearServicio(ResultSet resultSet) throws SQLException {
        return Servicio.builder()
                .id(resultSet.getInt("id_servicio"))
                .nombreServicio(TipoServicio.valueOf(resultSet.getString("nombre_servicio")))
                .descripcionServicio(resultSet.getString("descripcion_servicio"))
                .estadoServicio(EstadoServicio.valueOf(resultSet.getString("estado_servicio")))
                .build();
    }

    private void llenarStatement(PreparedStatement statement, Servicio servicio) throws SQLException {
        statement.setString(1, servicio.getNombreServicio().name());
        statement.setInt(2, servicio.getHogar().getId());
        statement.setString(3, servicio.getDescripcionServicio());
        statement.setString(4, servicio.getEstadoServicio().name());
    }

}
