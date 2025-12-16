package controller;

import lombok.RequiredArgsConstructor;
import model.Hogar;
import model.Servicio;
import model.enums.EstadoServicio;
import model.enums.TipoServicio;
import services.HogarService;
import services.ServiciosService;
import view.VentanaServicio;

import javax.print.DocFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ServicioController implements ActionListener {

    private final VentanaServicio view;
    private final ServiciosService service;
    private final HogarService hogarService;

    public void iniciar(){
        try {
            Map<String, Integer> familias = new HashMap<>();
            List<Hogar> hogars = hogarService.buscarTodosHogares();
            for (Hogar hogar : hogars) {
                familias.put(hogar.getNombreHogar(), hogar.getId());
            }
            view.setFamiliasMap(familias);
            view.cargarFamilias();

        } catch (SQLException e) {
            view.mostrarError(e.getMessage());
        }
        view.cambiarPanel();
    }

    public void configurarListener(){
        view.aplicarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getBtnGuardar()){
            List<String> datos = view.obtenerDatosDelFormulario();
            Servicio servicio = crearServicio(datos);
            try {
                service.crearServicio(servicio);
                view.mostrarMensajeSucess();
            } catch (SQLException ex) {
                view.mostrarError(ex.getMessage());
            }
        }
        if (source == view.getBtnVolver()){
            view.volverMenuPrincipal();
        }
    }

    private Servicio crearServicio(List<String> datos) {
        return Servicio.builder()
                .hogar(Hogar.builder().id(Integer.valueOf(datos.get(0))).build())
                .nombreServicio(TipoServicio.valueOf(datos.get(1)))
                .descripcionServicio(datos.get(2))
                .estadoServicio(EstadoServicio.EN_PROGRESO)
                .build();
    }

}
