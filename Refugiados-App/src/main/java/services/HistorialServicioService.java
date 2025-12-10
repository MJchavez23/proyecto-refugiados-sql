package services;

import model.HistorialServicio;

import java.sql.SQLException;
import java.util.List;

public interface HistorialServicioService {
    void crearHistorialServicio(HistorialServicio historialServicio) throws SQLException;
    List<HistorialServicio> buscarHistorialesPorServicio(String idServicio) throws SQLException;
}
