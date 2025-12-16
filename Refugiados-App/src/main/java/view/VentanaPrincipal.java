package view;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

@Getter
public class VentanaPrincipal extends JFrame{


    private JPanel mainPanel;
    private CardLayout cardLayout;

    private JMenuItem itemVerBitacora; // Nuevo item
    private JMenuItem itemBuscar;
    private JMenuItem itemRegistrar;
    private JMenuItem itemServicio;
    private JMenuItem itemHistorial;
    private JMenuItem itemSalir;

    public static final String NOMBRE_MENU = "MenuPrincipal";
    public static final String NOMBRE_BITACORA = "BitacoraView"; // Nuevo
    public static final String NOMBRE_BUSQUEDA = "BusquedaIndividuo";
    public static final String NOMBRE_REGISTRO = "RegistroIndividuo";
    public static final String NOMBRE_SERVICIO = "RegistroServicio";
    public static final String NOMBRE_HISTORIAL = "RegistroHistorial";


    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Refugiados - Agencia de Ayuda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 750);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel, BorderLayout.CENTER);

        configurarMenu();

        JPanel panelBienvenida = new JPanel(new GridBagLayout());
        panelBienvenida.add(new JLabel("<html><h1 style='color: #333;'>Bienvenido al Sistema de Gestión</h1><p>Seleccione una opción en el menú superior para comenzar.</p></html>"));
        mainPanel.add(panelBienvenida, NOMBRE_MENU);


        cardLayout.show(mainPanel, NOMBRE_MENU);
    }

    public void iniciar(){
        setVisible(true);
    }

    public void agregarPanel(JPanel panel, String nombre){
        mainPanel.add(panel, nombre);
    }

    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(mainPanel, nombrePanel);
    }

    private void configurarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOperaciones = new JMenu("Operaciones");

        // NUEVO MENÚ BITACORA
        JMenu menuBitacora = new JMenu("BITACORA");
        itemVerBitacora = new JMenuItem("Ver Bitácora / Subir Archivo");
        menuBitacora.add(itemVerBitacora);

        itemBuscar = new JMenuItem("Buscar Individuo", KeyEvent.VK_B);
        itemRegistrar = new JMenuItem("Registrar un Individuo", KeyEvent.VK_R);
        itemServicio = new JMenuItem("Registrar Servicio", KeyEvent.VK_S);
        itemHistorial = new JMenuItem("Historial", KeyEvent.VK_H);
        itemSalir = new JMenuItem("Salir", KeyEvent.VK_X);

        menuOperaciones.add(itemBuscar);
        menuOperaciones.add(itemRegistrar);
        menuOperaciones.add(itemServicio);
        menuOperaciones.add(itemHistorial);
        menuOperaciones.addSeparator();
        menuOperaciones.add(itemSalir);


        menuBar.add(menuBitacora);
        menuBar.add(menuOperaciones);

        setJMenuBar(menuBar);
    }

    public void aplicarListener(ActionListener listener){
        itemVerBitacora.addActionListener(listener);
        itemBuscar.addActionListener(listener);
        itemRegistrar.addActionListener(listener);
        itemServicio.addActionListener(listener);
        itemHistorial.addActionListener(listener);
        itemSalir.addActionListener(listener);
    }

    public void salir() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }

}