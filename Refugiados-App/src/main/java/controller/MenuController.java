package controller;

import lombok.RequiredArgsConstructor;
import view.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class MenuController implements ActionListener {
    private final VentanaPrincipal view;
    private final IndividuoController individuoController;
    private final BusquedaController busquedaController;
    private final BitacoraController bitacoraController;
    private final ServicioController servicioController;
    private final HistorialController historialController;

    public void iniciarApp(){
        view.iniciar();
        view.aplicarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.getItemVerBitacora()){
            bitacoraController.iniciar();
        }
        if(source == view.getItemBuscar()){
            busquedaController.iniciar();
        }
        if(source == view.getItemRegistrar()){
            individuoController.iniciar();
        }
        if (source == view.getItemServicio()){
            servicioController.iniciar();
        }
        if (source == view.getItemHistorial()){
            historialController.iniciar();
        }
        if (source == view.getItemSalir()){
            view.salir();
        }
    }
}
