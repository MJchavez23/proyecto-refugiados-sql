package view;

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

        // Usaremos GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // --- Inicialización de Componentes ---

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);

        // ComboBox con opciones (debe coincidir con VentanaRegistro)
        String[] documentos = {"Seleccionar...", "Cédula/DNI", "Pasaporte", "Carnet de Refugiado", "Otro"};
        cmbTipoDocumento = new JComboBox<>(documentos);

        txtNumeroDocumento = new JTextField(15);

        btnBuscar = new JButton("   Buscar   ");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 18));
        btnBuscar.addActionListener(this);

        // --- Posicionamiento (Layout) de Componentes ---

        // Botón Volver
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);

        // Etiqueta Tipo de Documento
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo de Documento:"), gbc);

        // ComboBox Tipo de Documento
        gbc.gridx = 2; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(cmbTipoDocumento, gbc);

        // Etiqueta Número de Documento
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Número de Documento:"), gbc);

        // Campo Número de Documento
        gbc.gridx = 2; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        add(txtNumeroDocumento, gbc);

        // Botón Buscar
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnBuscar, gbc);

        setVisible(true);
    }

    // --- Lógica de Búsqueda (Usa DataManager y los nuevos índices) ---
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btnBuscar) {
//            String tipo = (String) cmbTipoDocumento.getSelectedItem();
//            String numero = txtNumeroDocumento.getText();
//
//            if (tipo.equals("Seleccionar...") || numero.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Debe seleccionar un Tipo y Número de Documento.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
////            List<List<String>> registros = DataManager.cargarTodosLosRegistros();
//            List<String> resultado = null;
//
//            // ÍNDICES BASADOS EN EL NUEVO FORMATO DE 17 CAMPOS:
//            final int INDICE_TIPO_DOC = 9;  // Tipo Documento está en la posición 10
//            final int INDICE_NUM_DOC = 10;  // Número Documento está en la posición 11
//
//            final int MINIMO_CAMPOS_REQUERIDO = 11; // Necesitamos al menos el índice 10 (11 campos)
//
//            // 2. Iterar y buscar la coincidencia
//            for (List<String> registro : registros) {
//                if (registro.size() >= MINIMO_CAMPOS_REQUERIDO &&
//                        registro.get(INDICE_TIPO_DOC).equals(tipo) &&
//                        registro.get(INDICE_NUM_DOC).equals(numero)) {
//                    resultado = registro;
//                    break; // Encontrado
//                }
//            }
//
//            // 3. Mostrar el resultado (Usando Nombres y Apellidos)
//            if (resultado != null) {
//                JOptionPane.showMessageDialog(this,
//                        "Individuo encontrado:\n" +
//                                "Nombres: " + resultado.get(0) + "\n" +
//                                "Apellidos: " + resultado.get(1) + "\n" +
//                                "País Origen: " + resultado.get(4),
//                        "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(this, "No se encontró ningún individuo con el documento proporcionado.", "Búsqueda Fallida", JOptionPane.WARNING_MESSAGE);
//            }
//
//        } else if (e.getSource() == btnVolver) {
//            this.dispose();
//        }
    }
}