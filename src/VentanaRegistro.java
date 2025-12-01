import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.text.MaskFormatter;

public class VentanaRegistro extends JFrame implements ActionListener {

    // --- 1. Componentes del Formulario ---
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtPaisOrigen;
    private JTextField txtIdiomaPrincipal;
    private JTextField txtTelefono;
    private JTextField txtNumDocumento;
    private JTextField txtDiscapacidadTipo; // Nuevo: Tipo de Discapacidad
    private JTextField txtEnfermedadTipo;   // Nuevo: Tipo de Enfermedad

    private JFormattedTextField ftfFechaNacimiento; // XX/XX/XXXX

    private JComboBox<String> cmbNivelEducativo; // Nuevo
    private JComboBox<String> cmbEstatusLegal;   // Nuevo
    private JComboBox<String> cmbTipoDocumento;
    private JComboBox<String> cmbEstadoEmpleo;   // Nuevo

    private JRadioButton rbGeneroM;             // Género Masculino
    private JRadioButton rbGeneroF;             // Género Femenino
    private ButtonGroup bgGenero;

    private JRadioButton rbDiscapacidadSi;      // Discapacidad Si
    private JRadioButton rbDiscapacidadNo;
    private ButtonGroup bgDiscapacidad;

    private JRadioButton rbEnfermedadSi;        // Enfermedad Crónica Si
    private JRadioButton rbEnfermedadNo;
    private ButtonGroup bgEnfermedad;

    private JRadioButton rbEmbarazadaSi;        // Embarazada Si
    private JRadioButton rbEmbarazadaNo;
    private ButtonGroup bgEmbarazada;

    private JButton btnGuardar;
    private JButton btnVolver;

    public VentanaRegistro() {
        // --- Configuración de la Ventana ---
        setTitle("Registro de Nuevo Individuo - Agencia de Ayuda");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 750); // Aumento del tamaño para más campos
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);

        // --- 2. Inicialización de Componentes ---

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);
        btnGuardar = new JButton("Guardar Registro");
        btnGuardar.addActionListener(this);
        btnGuardar.setPreferredSize(new Dimension(250, 40));

        // Inicializar campos de texto
        txtNombres = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtPaisOrigen = new JTextField(20);
        txtIdiomaPrincipal = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtNumDocumento = new JTextField(20);
        txtDiscapacidadTipo = new JTextField(20);
        txtEnfermedadTipo = new JTextField(20);

        ftfFechaNacimiento = crearCampoFecha(); // Con formato ##/##/####

        // ComboBoxes
        cmbNivelEducativo = new JComboBox<>(new String[]{"Seleccionar...", "Ninguno", "Primaria", "Secundaria", "Técnico", "Universitario"});
        cmbEstatusLegal = new JComboBox<>(new String[]{"Seleccionar...", "Regular", "Irregular", "Solicitante de Asilo", "Refugiado"});
        cmbTipoDocumento = new JComboBox<>(new String[]{"Seleccionar...", "Cédula/DNI", "Pasaporte", "Carnet de Refugiado", "Otro"});
        cmbEstadoEmpleo = new JComboBox<>(new String[]{"Seleccionar...", "Empleado", "Desempleado", "Estudiante", "Ama de casa", "Jubilado"});

        // Grupos de Radio Buttons
        bgGenero = new ButtonGroup();
        rbGeneroM = new JRadioButton("Masculino");
        rbGeneroF = new JRadioButton("Femenino");
        bgGenero.add(rbGeneroM);
        bgGenero.add(rbGeneroF);

        bgDiscapacidad = new ButtonGroup();
        rbDiscapacidadSi = new JRadioButton("Sí");
        rbDiscapacidadNo = new JRadioButton("No");
        rbDiscapacidadNo.setSelected(true); // Default: No
        bgDiscapacidad.add(rbDiscapacidadSi);
        bgDiscapacidad.add(rbDiscapacidadNo);

        bgEnfermedad = new ButtonGroup();
        rbEnfermedadSi = new JRadioButton("Sí");
        rbEnfermedadNo = new JRadioButton("No");
        rbEnfermedadNo.setSelected(true); // Default: No
        bgEnfermedad.add(rbEnfermedadSi);
        bgEnfermedad.add(rbEnfermedadNo);

        bgEmbarazada = new ButtonGroup();
        rbEmbarazadaSi = new JRadioButton("Sí");
        rbEmbarazadaNo = new JRadioButton("No");
        rbEmbarazadaNo.setSelected(true); // Default: No
        bgEmbarazada.add(rbEmbarazadaSi);
        bgEmbarazada.add(rbEmbarazadaNo);


        // --- 3. Posicionamiento (GridBagLayout) ---

        int fila = 0;

        // Botón Volver
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);
        fila++;

        // Fila 1: Nombres y Apellidos
        fila = agregarFilaFormulario("Nombres:", txtNombres, fila, gbc);
        fila = agregarFilaFormulario("Apellidos:", txtApellidos, fila, gbc);

        // Fila 2: Género y Fecha Nacimiento
        fila = agregarFilaRadio("Género:", rbGeneroM, rbGeneroF, fila, gbc);
        fila = agregarFilaFormulario("Fecha Nacimiento (DD/MM/AAAA):", ftfFechaNacimiento, fila, gbc);

        // Fila 3: País Origen e Idioma Principal
        fila = agregarFilaFormulario("País Origen:", txtPaisOrigen, fila, gbc);
        fila = agregarFilaFormulario("Idioma Principal:", txtIdiomaPrincipal, fila, gbc);

        // Fila 4: Nivel Educativo y Teléfono
        fila = agregarFilaFormulario("Nivel Educativo:", cmbNivelEducativo, fila, gbc);
        fila = agregarFilaFormulario("Teléfono:", txtTelefono, fila, gbc);

        // Fila 5: Estatus Legal y Tipo Documento
        fila = agregarFilaFormulario("Estatus Legal:", cmbEstatusLegal, fila, gbc);
        fila = agregarFilaFormulario("Tipo Documento:", cmbTipoDocumento, fila, gbc);

        // Fila 6: Número Documento y Estado Empleo
        fila = agregarFilaFormulario("Número Documento:", txtNumDocumento, fila, gbc);
        fila = agregarFilaFormulario("Estado Empleo:", cmbEstadoEmpleo, fila, gbc);

        // Fila 7: Discapacidad (Si/No) y Tipo
        fila = agregarFilaRadio("Discapacidad:", rbDiscapacidadSi, rbDiscapacidadNo, fila, gbc);
        fila = agregarFilaFormulario("Tipo de Discapacidad:", txtDiscapacidadTipo, fila, gbc);

        // Fila 8: Enfermedad Crónica (Si/No) y Tipo
        fila = agregarFilaRadio("Enfermedad Crónica:", rbEnfermedadSi, rbEnfermedadNo, fila, gbc);
        fila = agregarFilaFormulario("Tipo de Enfermedad:", txtEnfermedadTipo, fila, gbc);

        // Fila 9: Embarazada (Si/No)
        fila = agregarFilaRadio("Embarazada:", rbEmbarazadaSi, rbEmbarazadaNo, fila, gbc);

        // Fila 10: Botón Guardar (Centrado y ancho completo)
        fila++;
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        setVisible(true);
    }

    /**
     * Crea un JFormattedTextField con la máscara ##/##/####.
     */
    private JFormattedTextField crearCampoFecha() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField field = new JFormattedTextField(mask);
        field.setColumns(10);
        return field;
    }

    /**
     * Método auxiliar para colocar la etiqueta y el componente en una fila
     */
    private int agregarFilaFormulario(String etiqueta, JComponent componente, int filaActual, GridBagConstraints gbc) {
        // Etiqueta
        gbc.gridx = 1; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(etiqueta), gbc);

        // Componente
        gbc.gridx = 2; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.WEST;
        add(componente, gbc);

        return filaActual + 1;
    }

    /**
     * Método auxiliar para colocar la etiqueta y dos RadioButtons
     */
    private int agregarFilaRadio(String etiqueta, JRadioButton rb1, JRadioButton rb2, int filaActual, GridBagConstraints gbc) {
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelRadio.add(rb1);
        panelRadio.add(rb2);

        // Etiqueta
        gbc.gridx = 1; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(etiqueta), gbc);

        // Componentes (Panel de Radio Buttons)
        gbc.gridx = 2; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.WEST;
        add(panelRadio, gbc);

        return filaActual + 1;
    }

    // --- Manejo de Acciones ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {

            // 1. Obtener los datos del formulario (NOTA: Aquí se concatenan todos los campos)
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String genero = obtenerSeleccion(bgGenero);
            String fechaNac = ftfFechaNacimiento.getText();
            String paisOrigen = txtPaisOrigen.getText();
            String idiomaPrincipal = txtIdiomaPrincipal.getText();
            String nivelEducativo = (String) cmbNivelEducativo.getSelectedItem();
            String telefono = txtTelefono.getText();
            String estatusLegal = (String) cmbEstatusLegal.getSelectedItem();
            String tipoDoc = (String) cmbTipoDocumento.getSelectedItem();
            String numDoc = txtNumDocumento.getText();

            String discapacidadSN = obtenerSeleccion(bgDiscapacidad);
            String discapacidadTipo = txtDiscapacidadTipo.getText();
            String enfermedadSN = obtenerSeleccion(bgEnfermedad);
            String enfermedadTipo = txtEnfermedadTipo.getText();
            String embarazadaSN = obtenerSeleccion(bgEmbarazada);
            String estadoEmpleo = (String) cmbEstadoEmpleo.getSelectedItem();

            // 2. Validación básica (Asegurarse de que campos clave no estén vacíos)
            if (nombres.isEmpty() || apellidos.isEmpty() || genero.isEmpty() || numDoc.isEmpty() || fechaNac.contains("_") || tipoDoc.equals("Seleccionar...")) {
                JOptionPane.showMessageDialog(this, "Debe completar Nombres, Apellidos, Género, Número y Tipo de Documento, y Fecha de Nacimiento.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Crear la lista de datos para guardar (¡NUEVO ORDEN Y CANTIDAD!)
            // Se guardan 17 campos.
            List<String> registro = List.of(
                    nombres, apellidos, genero, fechaNac, paisOrigen, idiomaPrincipal,
                    nivelEducativo, telefono, estatusLegal, tipoDoc, numDoc,
                    discapacidadSN, discapacidadTipo, enfermedadSN, enfermedadTipo, embarazadaSN, estadoEmpleo
            );

            // 4. Guardar los datos usando la clase DataManager
            DataManager.guardarRegistro(registro);

            JOptionPane.showMessageDialog(this, "¡Registro guardado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } else if (e.getSource() == btnVolver) {
            this.dispose();
        }
    }

    // Método auxiliar para obtener el texto del RadioButton seleccionado
    private String obtenerSeleccion(ButtonGroup group) {
        for (java.util.Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }
}