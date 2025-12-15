import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class VentanaServicio extends JPanel implements ActionListener {

    private VentanaPrincipal principal;
    private JTextField txtNumDocumento;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;
    private JButton btnVolver;

    public VentanaServicio(VentanaPrincipal principal) {
        this.principal = principal;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // --- Inicialización de Componentes ---

        txtNumDocumento = new JTextField(20);
        aplicarFiltroSoloNumeros(txtNumDocumento); // Filtro solo números

        txtDescripcion = new JTextArea(5, 20); // 5 filas, 20 columnas
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);

        btnGuardar = new JButton("Guardar Servicio");
        btnGuardar.addActionListener(this);
        btnGuardar.setPreferredSize(new Dimension(200, 35));

        // --- Posicionamiento ---
        int fila = 0;

        // Botón Volver
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);
        fila++;

        // Título
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("<html><h2>Registro de Servicios Individuales</h2></html>"), gbc);
        fila++;

        // Número de Documento
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Número de Documento:"), gbc);
        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(txtNumDocumento, gbc);
        fila++;

        // Descripción
        gbc.gridx = 1; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Descripción del Servicio:"), gbc);
        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH; // Rellena el espacio
        add(scrollDescripcion, gbc);
        gbc.fill = GridBagConstraints.NONE; // Restaura el relleno
        fila++;

        // Botón Guardar
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);
    }

    // --- FILTRO DE ENTRADA (Solo dígitos) ---
    private void aplicarFiltroSoloNumeros(JTextField field) {
        field.addKeyListener(new KeyAdapterSoloNumeros());
    }

    class KeyAdapterSoloNumeros extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                e.consume();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            String numDoc = txtNumDocumento.getText();
            String descripcion = txtDescripcion.getText();

            if (numDoc.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar el Número de Documento y la Descripción.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aquí se simula el guardado del servicio
            String fechaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
            String registroServicio = String.format("[%s] Documento: %s, Servicio: %s", fechaActual, numDoc, descripcion);

            // Suponiendo que DataManager tiene un método para guardar servicios
            // DataManager.guardarServicio(registroServicio);

            JOptionPane.showMessageDialog(this, "Servicio guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos y volver al menú
            txtNumDocumento.setText("");
            txtDescripcion.setText("");
            principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);

        } else if (e.getSource() == btnVolver) {
            principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
        }
    }
}