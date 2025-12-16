package controller;

import lombok.RequiredArgsConstructor;
import model.Hogar;
import model.Individuo;
import model.Refugio;
import model.enums.*;
import repository.IndividuoRepo;
import services.FileManagerService;
import services.HogarService;
import services.IndividuoService;
import services.RefugioService;
import services.impl.IndividuoServiceImpl;
import view.VentanaPrincipal;
import view.VentanaRegistroIndividuo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class IndividuoController implements ActionListener {
    private final FileManagerService fileManagerService;
    private final IndividuoService service;
    private final VentanaRegistroIndividuo view;
    private final HogarService hogarService;
    private final RefugioService refugioService;


    public void iniciar(){
        try {
            Map<String, Integer> familias = new HashMap<>();
            List<Hogar> hogars = hogarService.buscarTodosHogares();
            for (Hogar hogar : hogars) {
                familias.put(hogar.getNombreHogar(), hogar.getId());
            }
            view.setFamilias(familias);
            view.llenarFamilias();

        } catch (SQLException e) {
            view.mostrarError(e.getMessage());
        }
        view.cambiarPanel();
    }

    public void configurarListener(){
        view.agregarListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getBtnGuardar()){
            List<String> info = view.extraerInformacion();
            if (info.isEmpty()){
                return;
            }
            Individuo individuo = crearIndividuo(info);
            try {
                service.crearIndividuo(individuo);
                view.mostrarMensajeIndividuoGuardado();
            } catch (Exception ex) {
                view.mostrarError("Error: " + ex.getMessage());
            }

            view.volverMenuPrincipal();


        }
        if (source == view.getBtnSubirArchivo()){
            File archivo = view.extraerArchivo();
            if (archivo == null){
                return;
            }
            try {
                List<Refugio> refugios = fileManagerService.extraerRefugios(archivo);
                refugioService.guardarRefugios(refugios);

                List<Hogar> hogares = fileManagerService.extraerHogares(archivo);
                hogarService.guardarTodos(hogares);

                List<Individuo> individuos = fileManagerService.extraerIndividuos(archivo);
                service.crearVariosIndividuos(individuos);

                view.mostrarExito("Todo Guardado correctamente");

            } catch (IOException | SQLException ex) {
                view.mostrarError("Error: " + ex.getMessage());
            }
        }
        if (source == view.getBtnVolver()){
            view.volverMenuPrincipal();
        }
    }



    private Individuo crearIndividuo(List<String> info){
        return Individuo.builder()
                    .nombre(info.get(0))
                    .apellido(info.get(1))
                    .genero(Genero.valueOf(info.get(2)))
                    .fechaNacimiento(LocalDate.parse(info.get(3)))
                    .paisOrigen(info.get(4))
                    .idiomaPrincipal(info.get(5))
                    .nivelEducacion(NivelEducacion.valueOf(info.get(6)))
                    .telefono(info.get(7))
                    .estatusLegal(EstatusLegal.valueOf(info.get(8)))
                    .tipoDocumento(TipoDocumento.valueOf(info.get(9)))
                    .numeroDocumento(info.get(10))
                    .discapacidad(info.get(11))
                    .enfermedadCronica(info.get(12))
                    .embarazada(Boolean.parseBoolean(info.get(13)))
                    .estadoEmpleo(EstadoEmpleo.valueOf(info.get(14)))
                    .representanteHogar(Boolean.parseBoolean(info.get(15)))
                    .hogar(Hogar.builder().id(Integer.valueOf(info.get(16))).build())
                    .build();
    }

}
