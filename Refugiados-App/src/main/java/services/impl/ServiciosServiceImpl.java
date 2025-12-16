package services.impl;

import lombok.RequiredArgsConstructor;
import model.Servicio;
import repository.ServicioRepo;
import services.ServiciosService;

import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class ServiciosServiceImpl implements ServiciosService {

    private final ServicioRepo servicioRepo;

    @Override
    public void crearServicio(Servicio servicio) throws SQLException {
        validarUnico(servicio.getNombreServicio().name(), servicio.getHogar().getId());
        servicioRepo.guardarServicio(servicio);
    }

    @Override
    public Optional<Servicio> buscarServicioPorNumeroDocumento(String numeroDocumento) throws SQLException {
        return servicioRepo.buscarServicioPorNumeroDocumento(numeroDocumento);
    }

    private void validarUnico(String nombre, int idHogar) throws SQLException {
        var r = servicioRepo.buscarServicioPorNombreYHogar(nombre, idHogar);
        if(r.isPresent()) {
            throw new SQLException("Servicio existente para este hogar ya existe");
        }
    }
}
