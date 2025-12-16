package services.impl;

import lombok.RequiredArgsConstructor;
import model.HistorialServicio;
import repository.HistorialServicioRepo;
import services.HistorialServicioService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class HistorialServicioServiceImpl implements HistorialServicioService {

    private final HistorialServicioRepo historialServicioRepo;

    @Override
    public void crearHistorialServicio(HistorialServicio historialServicio) throws SQLException {
        validarGuardado(historialServicio);
        historialServicioRepo.guardarHistorialServicio(historialServicio);
    }

    @Override
    public List<HistorialServicio> buscarHistorialesPorNumeroDocumento(String numeroDocuemento) throws SQLException {
        return historialServicioRepo.buscarHistorialesPorNumeroDocumento(numeroDocuemento);
    }

    private void validarGuardado(HistorialServicio historialServicio) {
        historialServicio.setFechaServicio(LocalDate.now());
    }


}
