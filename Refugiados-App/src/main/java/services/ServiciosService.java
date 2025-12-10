package services;

import model.Servicio;

import java.sql.SQLException;

public interface ServiciosService {
    void crearServicio(Servicio servicio) throws SQLException;
}
