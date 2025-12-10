package services.impl;

import lombok.RequiredArgsConstructor;
import model.HistorialServicio;
import repository.HistorialServicioRepo;
import services.HistorialServicioService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HistorialServicioServiceImpl implements HistorialServicioService {

    private final HistorialServicioRepo historialServicioRepo;

    @Override
    public void crearHistorialServicio(HistorialServicio historialServicio) throws SQLException {
        validarGuardado(historialServicio);
        historialServicioRepo.guardarHistorialServicio(historialServicio);
    }

    @Override
    public List<HistorialServicio> buscarHistorialesPorServicio(String idServicio) throws SQLException {
        return historialServicioRepo.buscarHistorialesPorServicio(idServicio);
    }

    private void validarGuardado(HistorialServicio historialServicio) {
        historialServicio.setFechaServicio(LocalDate.now());
    }


}
