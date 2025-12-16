package controller;

import lombok.RequiredArgsConstructor;
import model.Bitacora;
import services.BitacoraService;
import view.BitacoraPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class BitacoraController implements ActionListener {
    private final BitacoraPanel view;
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

        if (source == view.getBtnVolver()){
            view.volverMenuPrincipal();
        }
    }

}
