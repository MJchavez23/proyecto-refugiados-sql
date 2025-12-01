import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton btnBuscar;
    private JButton btnRegistrar;
    private JButton btnServicio;

    public VentanaPrincipal() {
        // --- Configuración de la Ventana Principal ---
        setTitle("Sistema de Gestión de Refugiados - Agencia de Ayuda");
        // EXIT_ON_CLOSE cierra toda la aplicación al cerrar esta ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // Panel para organizar los botones en una cuadrícula
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 50, 0)); // 1 fila, 3 columnas, 50px de espacio
        panelBotones.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        // --- Inicialización y Enlazado de Botones ---

        btnBuscar = crearBoton("Buscar un Individuo");
        btnBuscar.addActionListener(this);

        btnRegistrar = crearBoton("Registrar un Individuo");
        btnRegistrar.addActionListener(this);

        btnServicio = crearBoton("Registrar Servicio");
        btnServicio.addActionListener(this);

        panelBotones.add(btnBuscar);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnServicio);

        add(panelBotones, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Método auxiliar para crear botones grandes (estilo de tu diseño)
     */
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setPreferredSize(new Dimension(200, 200));
        return boton;
    }

    // Lógica que se ejecuta al hacer clic en un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            new VentanaBusqueda(); // Abre la ventana de búsqueda

        } else if (e.getSource() == btnRegistrar) {
            new VentanaRegistro(); // Abre la ventana de registro

        } else if (e.getSource() == btnServicio) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de Registrar Servicio en desarrollo.");
        }
    }

    // Método main para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
