import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaBusqueda extends JFrame implements ActionListener {

    // Componentes del formulario
    private JComboBox<String> cmbTipoDocumento;
    private JTextField txtNumeroDocumento;
    private JButton btnBuscar;
    private JButton btnVolver;

    public VentanaBusqueda() {

        // --- Configuración básica de la ventana ---
        setTitle("Búsqueda de Individuo - Agencia de Ayuda");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);

        // Usaremos GridBagLayout para alinear el formulario
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // --- Inicialización de Componentes ---

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);

        // ComboBox con opciones
        String[] documentos = {"Seleccionar...", "Cédula/DNI", "Pasaporte", "Carnet de Refugiado"};
        cmbTipoDocumento = new JComboBox<>(documentos);

        txtNumeroDocumento = new JTextField(15);

        btnBuscar = new JButton("   Buscar   ");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 18));
        btnBuscar.addActionListener(this);

        // --- Posicionamiento (Layout) de Componentes ---

        // Botón Volver (Fila 0, Columna 0, en la esquina)
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);

        // Etiqueta Tipo de Documento (Fila 1, Columna 1)
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo de Documento:"), gbc);

        // ComboBox Tipo de Documento (Fila 1, Columna 2)
        gbc.gridx = 2; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(cmbTipoDocumento, gbc);

        // Etiqueta Número de Documento (Fila 2, Columna 1)
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Número de Documento:"), gbc);

        // Campo Número de Documento (Fila 2, Columna 2)
        gbc.gridx = 2; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        add(txtNumeroDocumento, gbc);

        // Botón Buscar (Fila 4, centrado, ocupando 2 columnas)
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Estirar el botón
        add(btnBuscar, gbc);

        setVisible(true);
    }

    // --- Lógica de Búsqueda (Usa DataManager) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            String tipo = (String) cmbTipoDocumento.getSelectedItem();
            String numero = txtNumeroDocumento.getText();

            if (tipo.equals("Seleccionar...") || numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un Tipo y Número de Documento.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 1. Cargar todos los registros del archivo
            List<List<String>> registros = DataManager.cargarTodosLosRegistros();
            List<String> resultado = null;

            // 2. Iterar y buscar la coincidencia (El Tipo está en índice 4, el Número está en índice 5)
            for (List<String> registro : registros) {
                if (registro.size() > 5 && registro.get(4).equals(tipo) && registro.get(5).equals(numero)) {
                    resultado = registro;
                    break; // Encontrado
                }
            }

            // 3. Mostrar el resultado
            if (resultado != null) {
                JOptionPane.showMessageDialog(this,
                        "Individuo encontrado:\n" +
                                "Nombre: " + resultado.get(0) + "\n" +
                                "Nacionalidad: " + resultado.get(2) + "\n" +
                                "Género: " + resultado.get(3),
                        "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún individuo con el documento proporcionado.", "Búsqueda Fallida", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnVolver) {
            this.dispose();
        }
    }
}