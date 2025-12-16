package controller;

import lombok.RequiredArgsConstructor;
import model.Individuo;
import services.IndividuoService;
import view.VentanaBusqueda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@RequiredArgsConstructor
public class BusquedaController implements ActionListener {

    private final VentanaBusqueda view;
    private final IndividuoService individuoService;

    public void iniciar(){
        view.cambiarPanel();
    }
    public void configurarListener(){
        view.agregarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.getBtnBuscar()){
            String numero = view.extraerNumeroDocumento();
            if (numero.isEmpty()) {
                return;
            }

            try {
                Optional<Individuo> ind = individuoService.buscarIndividuoPorNumeroDocumento(numero);
                if (ind.isEmpty()) {
                    view.mostrarError("No se encontro un Individuo con numero de documento " + numero);
                    return;
                }
                view.mostrarIndividuo(ind.get());
            } catch (Exception ex) {
                view.mostrarError("Error: " + ex.getMessage());
            }
        }
        if(source == view.getBtnVolver()){
            view.volverMenuPrincipal();
        }
    }
}
