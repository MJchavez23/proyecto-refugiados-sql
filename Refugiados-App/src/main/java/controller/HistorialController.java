package controller;

import lombok.RequiredArgsConstructor;
import model.HistorialServicio;
import model.Servicio;
import repository.HistorialServicioRepo;
import services.HistorialServicioService;
import services.ServiciosService;
import view.VentanaHistorial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HistorialController implements ActionListener {
    private final ServiciosService serviciosService;
    private final HistorialServicioService historialServicioService;
    private final VentanaHistorial view;

    public void iniciar(){
        view.cambiarPanel();
    }

    public void configurarListener(){
        view.agregarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getBtnGuardar()){
            String numeroDocumento = view.obtenerNumeroDocumento();
            try {
                Optional<Servicio> servicio = serviciosService.buscarServicioPorNumeroDocumento(numeroDocumento);
                if (servicio.isPresent()){

                    String descripcionHistorial = view.obtenerHistorialDescripcion();
                    HistorialServicio historialServicio = HistorialServicio.builder()
                        .servicio(Servicio.builder().id(servicio.get().getId()).build())
                            .descripcion(descripcionHistorial)
                        .build();

                    historialServicioService.crearHistorialServicio(historialServicio);
                    view.mostrarExito("Historial de servicio guardado con exito!");
                    return;

                }
                view.mostrarError("Servicio con numero documento " +  numeroDocumento + " no encontrado");
                return;
            } catch (SQLException ex) {
                view.mostrarError(ex.getMessage());
            }
        }
        if(source == view.getBtnVerHistorial()){
            String numeroDocumento =  view.obtenerNumeroDocumento();
            if (numeroDocumento.isBlank()){
                view.mostrarExito("El numero de documento no puede ser vacio");
                return;
            }
            try {
                List<HistorialServicio> historialServicio = historialServicioService.buscarHistorialesPorNumeroDocumento(numeroDocumento);
                view.llenarTabla(historialServicio);

            } catch (SQLException ex) {
                view.mostrarError(ex.getMessage());
            }
        }
        if(source == view.getBtnVolver()){
            view.volverMenuPrincipal();
        }
    }


}
