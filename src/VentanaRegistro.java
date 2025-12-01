import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.text.MaskFormatter;

public class VentanaRegistro extends JFrame implements ActionListener {

    // --- Componentes del Formulario ---
    private JTextField txtNombre;
    private JTextField txtNumDocumento;
    private JTextField txtNacionalidad; // CAMBIO: Ahora es un JTextField (barra de texto)
    private JComboBox<String> cmbTipoDocumento;
    private JTextArea txtNotas;
    private JRadioButton rbMasculino;
    private JRadioButton rbFemenino;
    private ButtonGroup bgGenero;
    private JButton btnGuardar;
    private JButton btnVolver;
    private JFormattedTextField ftfFechaNacimiento; // CAMBIO: Ahora es JFormattedTextField

    public VentanaRegistro() {
        // --- Configuración de la Ventana ---
        setTitle("Registro de Nuevo Individuo - Agencia de Ayuda");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 600);
        setLocationRelativeTo(null);

        // Usaremos GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // Margen interior

        // --- 1. Inicialización de Componentes ---

        // Botones de acción
        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);
        btnGuardar = new JButton("Guardar Registro");
        btnGuardar.addActionListener(this);
        btnGuardar.setPreferredSize(new Dimension(250, 40));

        // Campos de texto y área de texto
        txtNombre = new JTextField(20);
        txtNumDocumento = new JTextField(20);
        txtNotas = new JTextArea(5, 20);

        // CAMBIO: NACIONALIDAD ahora es un campo de texto simple
        txtNacionalidad = new JTextField(20);

        // CAMBIO: FECHA DE NACIMIENTO con formato de máscara '##/##/####'
        ftfFechaNacimiento = crearCampoFecha();

        // Listas desplegables (Tipo de Documento se mantiene)
        cmbTipoDocumento = new JComboBox<>(new String[]{"Seleccionar...", "Cédula/DNI", "Pasaporte", "Carnet de Refugiado"});

        // CAMBIO: Radio Buttons para Género (solo Masculino y Femenino)
        rbMasculino = new JRadioButton("Masculino");
        rbFemenino = new JRadioButton("Femenino");
        bgGenero = new ButtonGroup();
        bgGenero.add(rbMasculino);
        bgGenero.add(rbFemenino);

        // --- 2. Posicionamiento (GridBagLayout) ---

        int fila = 0;

        // Botón Volver
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);
        fila++;

        // Nombre Completo
        fila = agregarFilaFormulario("Nombre Completo:", txtNombre, fila, gbc);

        // Fecha de Nacimiento (Usa el JFormattedTextField)
        fila = agregarFilaFormulario("Fecha de Nacimiento (DD/MM/AAAA):", ftfFechaNacimiento, fila, gbc);

        // Nacionalidad (Usa el JTextField simple)
        fila = agregarFilaFormulario("Nacionalidad:", txtNacionalidad, fila, gbc);

        // Género (Necesita un Panel para agrupar los RadioButtons)
        JPanel panelGenero = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelGenero.add(rbMasculino);
        panelGenero.add(rbFemenino);

        gbc.gridx = 1; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Género:"), gbc);
        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(panelGenero, gbc);
        fila++;

        // Tipo de Documento
        fila = agregarFilaFormulario("Tipo de Documento:", cmbTipoDocumento, fila, gbc);

        // Número de Documento
        fila = agregarFilaFormulario("Número de Documento:", txtNumDocumento, fila, gbc);

        // Notas Adicionales
        gbc.gridx = 1; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Notas Adicionales:"), gbc);

        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JScrollPane(txtNotas), gbc);
        gbc.fill = GridBagConstraints.NONE;
        fila++;

        // Botón Guardar
        fila++;
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        setVisible(true);
    }

    /**
     * Crea y retorna un JFormattedTextField con la máscara ##/##/####
     */
    private JFormattedTextField crearCampoFecha() {
        MaskFormatter mask = null;
        try {
            // # obliga a que sea un dígito (0-9)
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField field = new JFormattedTextField(mask);
        field.setColumns(10); // Tamaño para el formato DDMMAAAA
        return field;
    }

    /**
     * Método auxiliar para colocar la etiqueta y el componente en una fila
     */
    private int agregarFilaFormulario(String etiqueta, JComponent componente, int filaActual, GridBagConstraints gbc) {
        gbc.gridx = 1; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(etiqueta), gbc);

        gbc.gridx = 2; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.WEST;
        add(componente, gbc);

        return filaActual + 1;
    }

    // --- Manejo de Acciones (Actualizado para usar los nuevos campos) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            // 1. Obtener los datos del formulario
            String nombre = txtNombre.getText();
            String fechaNac = ftfFechaNacimiento.getText(); // Usa el JFormattedTextField
            String nacionalidad = txtNacionalidad.getText(); // Usa el JTextField
            String genero = obtenerGeneroSeleccionado();
            String tipoDoc = (String) cmbTipoDocumento.getSelectedItem();
            String numDoc = txtNumDocumento.getText();
            String notas = txtNotas.getText();

            // 2. Validación básica
            if (nombre.isEmpty() || genero.isEmpty() || tipoDoc.equals("Seleccionar...") || numDoc.isEmpty() || fechaNac.contains("_")) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos obligatorios y el formato de fecha (DD/MM/AAAA).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Crear una lista de datos para guardar (IMPORTANTE: Mismo orden que DataManager.ENCABEZADOS)
            List<String> registro = List.of(nombre, fechaNac, nacionalidad, genero, tipoDoc, numDoc, notas);

            // 4. Guardar los datos usando la clase DataManager
            DataManager.guardarRegistro(registro);

            JOptionPane.showMessageDialog(this, "¡Registro guardado exitosamente en el archivo!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } else if (e.getSource() == btnVolver) {
            this.dispose();
        }
    }

    private String obtenerGeneroSeleccionado() {
        if (rbMasculino.isSelected()) return "Masculino";
        if (rbFemenino.isSelected()) return "Femenino";
        return "";
    }
}