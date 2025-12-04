package services;

import model.HistorialServicio;

import java.sql.SQLException;

public interface HistorialServicioService {
    HistorialServicio crearHistorialServicio(HistorialServicio historialServicio) throws SQLException;
}
