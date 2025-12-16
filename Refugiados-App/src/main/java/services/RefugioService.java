package services;

import model.Refugio;

import java.sql.SQLException;
import java.util.List;

public interface RefugioService {
    void guardarRefugio(Refugio refugio) throws SQLException;
    void guardarRefugios(List<Refugio> refugios) throws SQLException;
}
