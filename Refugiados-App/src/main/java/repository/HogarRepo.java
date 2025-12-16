package repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.Hogar;
import model.Refugio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HogarRepo {

    private final Connection connection;


    public void guardarHogar(Hogar hogar) throws SQLException {
        String query = "SELECT guardarHogar(?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        llenarStatement(preparedStatement, hogar);
        preparedStatement.execute();
    }


    public Optional<Hogar> buscarHogarPorId(Integer id) throws SQLException {
        String query = "SELECT * FROM obtener_detalle_hogar(?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1,id);

        ResultSet rs = statement.executeQuery();

        if(rs.next()){
            Hogar hogar = crearHogar(rs);
            return Optional.of(hogar);
        }

        return Optional.empty();
    }

    public List<Hogar> buscarTodosHogares() throws SQLException {
        List<Hogar> hogares = new ArrayList<>();
        String query = "SELECT * FROM obtener_todos_hogares_detallados()";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            Hogar hogar = crearHogar(rs);
            hogares.add(hogar);
        }
        return hogares;
    }

     private void llenarStatement(PreparedStatement preparedStatement, Hogar hogar) throws SQLException {
        preparedStatement.setInt(1, hogar.getRefugio().getId());
        preparedStatement.setString(2, hogar.getNombreHogar());
        preparedStatement.setObject(3, Date.valueOf(hogar.getFechaLlegada()));
    }

    private Hogar crearHogar(ResultSet rs) throws SQLException {
        Refugio refugio = Refugio.builder()
                .id(rs.getInt("ref_id_refugio"))
                .nombre(rs.getString("nombre_refugio"))
                .ciudad(rs.getString("ciudad"))
                .pais(rs.getString("pais"))
                .referenciaUbicacion(rs.getString("referencia"))
                .build();

        return Hogar.builder()
                .id(rs.getInt("id_hogar"))
                .refugio(refugio)
                .nombreHogar(rs.getString("nombre_hogar"))
                .fechaLlegada(rs.getObject("fecha_llegada_refugio", LocalDate.class))
                .build();
    }


}
