package repository;

import lombok.RequiredArgsConstructor;
import model.Bitacora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BitacoraRepo {

    private final Connection connection;


    public List<Bitacora> buscarTodosBitacoras() throws SQLException {
        String query = "SELECT * FROM buscarTodosBitacoras();";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Bitacora> bitacoras = new ArrayList<>();
        while (resultSet.next()) {
            Bitacora bit = crearBitacora(resultSet);
            bitacoras.add(bit);
        }
        return bitacoras;
    }

    private Bitacora crearBitacora(ResultSet resultSet) throws SQLException {
        return Bitacora.builder()
                .usuario(resultSet.getString("usuario"))
                .fecha(resultSet.getObject("fecha", LocalDate.class))
                .accion(resultSet.getString("accion"))
                .tabla(resultSet.getString("tabla"))
                .build();
    }
}
