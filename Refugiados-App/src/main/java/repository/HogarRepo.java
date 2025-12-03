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


    public Optional<Hogar> buscarHogarPorId(Integer id) throws SQLException {
        String query = "SELECT h.id_hogar, r.id_refugio AS ref_id_refugio, h.nombre_hogar, h.fecha_llegada_refugio, r.nombre AS nombre_refugio, r.ciudad, r.pais, r.referencia" +
                "FROM hogar h JOIN refugio r ON h.id_refugio = r.id_refugio WHERE h.id_hogar = ?;";

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
        String query = "SELECT h.id_hogar, r.id_refugio AS ref_id_refugio, h.nombre_hogar, h.fecha_llegada_refugio, r.nombre AS nombre_refugio, r.ciudad, r.pais, r.referencia" +
                "FROM hogar h JOIN refugio r ON h.id_refugio = r.id_refugio;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            Hogar hogar = crearHogar(rs);
            hogares.add(hogar);
        }
        return hogares;
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
