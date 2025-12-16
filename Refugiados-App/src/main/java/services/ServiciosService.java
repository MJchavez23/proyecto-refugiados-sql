package services;

import model.Servicio;

import java.sql.SQLException;
import java.util.Optional;

public interface ServiciosService {
    void crearServicio(Servicio servicio) throws SQLException;

    Optional<Servicio> buscarServicioPorNumeroDocumento(String numeroDocumento) throws SQLException;
}
