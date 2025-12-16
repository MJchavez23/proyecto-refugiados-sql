package repository;

import lombok.RequiredArgsConstructor;
import model.Refugio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class RefugioRepo {
    private final Connection connection;


    public void guardarRefugio(Refugio refugio) throws SQLException {
        String query = "SELECT guardarRefugio(?, ?, ? , ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        llenarStatement(preparedStatement, refugio);
        preparedStatement.execute();

    }


    private void llenarStatement(PreparedStatement preparedStatement, Refugio refugio) throws SQLException {
        preparedStatement.setString(1, refugio.getNombre());
        preparedStatement.setString(2, refugio.getCiudad());
        preparedStatement.setString(3, refugio.getPais());
        preparedStatement.setString(4, refugio.getReferenciaUbicacion());

    }
}
