package services.impl;

import lombok.RequiredArgsConstructor;
import model.HistorialServicio;
import repository.HistorialServicioRepo;
import services.HistorialServicioService;

import java.sql.SQLException;

@RequiredArgsConstructor
public class HistorialServicioServiceImpl implements HistorialServicioService {

    private final HistorialServicioRepo historialServicioRepo;

    @Override
    public HistorialServicio crearHistorialServicio(HistorialServicio historialServicio) throws SQLException {
        return historialServicioRepo.guardarHistorialServicio(historialServicio);
    }
}
