package controller;

import lombok.RequiredArgsConstructor;
import model.Bitacora;
import services.BitacoraService;
import services.FileManagerService;
import services.IndividuoService;
import view.BitacoraPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class BitacoraController implements ActionListener {
    private final FileManagerService fileManagerService;
    private final BitacoraPanel view;
    private final IndividuoService individuoService;
    private final BitacoraService bitacoraService;

    public void iniciar(){
        view.cambiarPanel();
        cargarBitacoras();
    }

    public void configurarListener(){
        view.agregarListener(this);
    }

    public void cargarBitacoras(){
        view.limpiarTabla();
        try {
            List<Bitacora> bitacoras = bitacoraService.buscarTodasBitacoras();
            for (Bitacora bitacora : bitacoras) {
                Object[] fila = {
                        bitacora.getFecha(),
                        bitacora.getAccion(),
                        bitacora.getUsuario(),
                        bitacora.getTabla()
                };
                view.agregarFila(fila);
            }
        } catch (SQLException e) {
            view.mostrarError(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getBtnSubirArchivo()){
            File archivo = view.extraerArchivo();
            if (archivo == null){
                return;
            }
//            try {
//                List<Individuo> individuos = fileManagerService.extraerIndividuos(archivo);
//                individuoService.crearVariosIndividuos(individuos);
//
//            } catch (IOException | SQLException ex) {
//                view.mostrarError("Error: " + ex.getMessage());
//            }

        }
        if (source == view.getBtnVolver()){
            view.volverMenuPrincipal();
        }
    }

}
