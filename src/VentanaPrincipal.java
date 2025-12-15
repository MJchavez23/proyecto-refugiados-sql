import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends JFrame implements ActionListener {

    // --- Componentes para CardLayout ---
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Items del Menú
    private JMenuItem itemVerBitacora; // Nuevo item
    private JMenuItem itemBuscar;
    private JMenuItem itemRegistrar;
    private JMenuItem itemServicio;
    private JMenuItem itemSalir;

    // Constantes para identificar las "páginas"
    public static final String NOMBRE_MENU = "MenuPrincipal";
    public static final String NOMBRE_BITACORA = "BitacoraView"; // Nuevo
    public static final String NOMBRE_BUSQUEDA = "BusquedaIndividuo";
    public static final String NOMBRE_REGISTRO = "RegistroIndividuo";
    public static final String NOMBRE_SERVICIO = "RegistroServicio"; // Nuevo

    public VentanaPrincipal() {
        // --- 1. Configuración de la Ventana ---
        setTitle("Sistema de Gestión de Refugiados - Agencia de Ayuda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 750);
        setLocationRelativeTo(null);

        // --- 2. Configurar CardLayout ---
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel, BorderLayout.CENTER);

        // --- 3. Ensamblar Menús y Componentes ---
        configurarMenu();

        // AÑADIR LOS PANELES AL CONTENEDOR PRINCIPAL

        // Panel de Bienvenida (inicial)
        JPanel panelBienvenida = new JPanel(new GridBagLayout());
        panelBienvenida.add(new JLabel("<html><h1 style='color: #333;'>Bienvenido al Sistema de Gestión</h1><p>Seleccione una opción en el menú superior para comenzar.</p></html>"));
        mainPanel.add(panelBienvenida, NOMBRE_MENU);

        // Paneles funcionales (que extienden JPanel)
        mainPanel.add(new BitacoraPanel(this), NOMBRE_BITACORA); // Nuevo
        mainPanel.add(new VentanaBusqueda(this), NOMBRE_BUSQUEDA);
        mainPanel.add(new VentanaRegistro(this), NOMBRE_REGISTRO);
        mainPanel.add(new VentanaServicio(this), NOMBRE_SERVICIO); // Nuevo

        // Mostrar el menú principal al inicio
        cardLayout.show(mainPanel, NOMBRE_MENU);

        setVisible(true);
    }

    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(mainPanel, nombrePanel);
    }

    private void configurarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOperaciones = new JMenu("Operaciones");
        JMenu menuAyuda = new JMenu("Ayuda");

        // NUEVO MENÚ BITACORA
        JMenu menuBitacora = new JMenu("BITACORA");
        itemVerBitacora = new JMenuItem("Ver Bitácora / Subir Archivo");
        itemVerBitacora.addActionListener(this);
        menuBitacora.add(itemVerBitacora);

        itemBuscar = new JMenuItem("Buscar Individuo", KeyEvent.VK_B);
        itemRegistrar = new JMenuItem("Registrar un Individuo", KeyEvent.VK_R);
        itemServicio = new JMenuItem("Registrar Servicio", KeyEvent.VK_S);
        itemSalir = new JMenuItem("Salir", KeyEvent.VK_X);

        itemBuscar.addActionListener(this);
        itemRegistrar.addActionListener(this);
        itemServicio.addActionListener(this);
        itemSalir.addActionListener(this);

        menuOperaciones.add(itemBuscar);
        menuOperaciones.add(itemRegistrar);
        menuOperaciones.add(itemServicio);
        menuOperaciones.addSeparator();
        menuOperaciones.add(itemSalir);

        menuAyuda.add(new JMenuItem("Acerca de..."));

        // Orden de la barra de menú
        menuBar.add(menuBitacora);
        menuBar.add(menuOperaciones);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemVerBitacora) {
            mostrarPanel(NOMBRE_BITACORA);

        } else if (e.getSource() == itemBuscar) {
            mostrarPanel(NOMBRE_BUSQUEDA);

        } else if (e.getSource() == itemRegistrar) {
            mostrarPanel(NOMBRE_REGISTRO);

        } else if (e.getSource() == itemServicio) {
            mostrarPanel(NOMBRE_SERVICIO); // Muestra el nuevo panel de servicio

        } else if (e.getSource() == itemSalir) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}