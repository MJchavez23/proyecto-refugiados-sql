import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class VentanaRegistro extends JPanel implements ActionListener { // CAMBIO: extends JPanel

    private VentanaPrincipal principal; // NUEVO: Referencia al contenedor principal

    // --- Componentes del Formulario ---
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtPaisOrigen;
    private JTextField txtIdiomaPrincipal;
    private JTextField txtTelefono;
    private JTextField txtNumDocumento;
    private JTextField txtDiscapacidadTipo;
    private JTextField txtEnfermedadTipo;

    private JDateChooser dateChooserFechaNacimiento;

    private JComboBox<String> cmbGenero;
    private JComboBox<String> cmbNivelEducativo;
    private JComboBox<String> cmbEstatusLegal;
    private JComboBox<String> cmbTipoDocumento;
    private JComboBox<String> cmbEstadoEmpleo;

    private JRadioButton rbDiscapacidadSi, rbDiscapacidadNo;
    private ButtonGroup bgDiscapacidad;
    private JRadioButton rbEnfermedadSi, rbEnfermedadNo;
    private ButtonGroup bgEnfermedad;
    private JRadioButton rbEmbarazadaSi, rbEmbarazadaNo;
    private ButtonGroup bgEmbarazada;

    private JButton btnGuardar;
    private JButton btnVolver;

    // CAMBIO: El constructor recibe la VentanaPrincipal
    public VentanaRegistro(VentanaPrincipal principal) {
        this.principal = principal;

        // --- Configuración del Panel ---
        // Se elimina setTitle, setDefaultCloseOperation, setSize, setLocationRelativeTo, setVisible

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);

        // --- 1. Inicialización de Componentes y Aplicación de Filtros ---

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);
        btnGuardar = new JButton("Guardar Registro");
        btnGuardar.addActionListener(this);
        btnGuardar.setPreferredSize(new Dimension(250, 40));

        // Filtros (Se mantiene la lógica de filtro)
        txtNombres = new JTextField(20); aplicarFiltroSoloLetras(txtNombres);
        txtApellidos = new JTextField(20); aplicarFiltroSoloLetras(txtApellidos);
        txtPaisOrigen = new JTextField(20); aplicarFiltroSoloLetras(txtPaisOrigen);
        txtIdiomaPrincipal = new JTextField(20); aplicarFiltroSoloLetras(txtIdiomaPrincipal);
        txtTelefono = new JTextField(20); aplicarFiltroNumSimbolos(txtTelefono);
        txtNumDocumento = new JTextField(20); aplicarFiltroNumSimbolos(txtNumDocumento);
        txtDiscapacidadTipo = new JTextField(20);
        txtEnfermedadTipo = new JTextField(20);

        dateChooserFechaNacimiento = new JDateChooser();
        dateChooserFechaNacimiento.setPreferredSize(new Dimension(200, 25));

        cmbGenero = new JComboBox<>(new String[]{"Seleccionar...", "Masculino", "Femenino"});
        cmbNivelEducativo = new JComboBox<>(new String[]{"Seleccionar...", "Ninguno", "Primaria", "Secundaria", "Técnico", "Universitario"});
        cmbEstatusLegal = new JComboBox<>(new String[]{"Seleccionar...", "Regular", "Irregular", "Solicitante de Asilo", "Refugiado"});
        cmbTipoDocumento = new JComboBox<>(new String[]{"Seleccionar...", "Cédula/DNI", "Pasaporte", "Carnet de Refugiado", "Otro"});
        cmbEstadoEmpleo = new JComboBox<>(new String[]{"Seleccionar...", "Empleado", "Desempleado", "Estudiante", "Ama de casa", "Jubilado"});

        bgDiscapacidad = new ButtonGroup();
        rbDiscapacidadSi = new JRadioButton("Sí"); rbDiscapacidadNo = new JRadioButton("No"); rbDiscapacidadNo.setSelected(true);
        bgDiscapacidad.add(rbDiscapacidadSi); bgDiscapacidad.add(rbDiscapacidadNo);

        bgEnfermedad = new ButtonGroup();
        rbEnfermedadSi = new JRadioButton("Sí"); rbEnfermedadNo = new JRadioButton("No"); rbEnfermedadNo.setSelected(true);
        bgEnfermedad.add(rbEnfermedadSi); bgEnfermedad.add(rbEnfermedadNo);

        bgEmbarazada = new ButtonGroup();
        rbEmbarazadaSi = new JRadioButton("Sí"); rbEmbarazadaNo = new JRadioButton("No"); rbEmbarazadaNo.setSelected(true);
        bgEmbarazada.add(rbEmbarazadaSi); bgEmbarazada.add(rbEmbarazadaNo);


        // --- 2. Posicionamiento (GridBagLayout) ---

        int fila = 0;

        // Botón Volver
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);
        fila++;

        // (Se omiten filas de layout para brevedad, asumiendo que el layout es idéntico a la versión anterior)
        // Fila 1: Nombres y Apellidos
        fila = agregarFilaFormulario("Nombres:", txtNombres, fila, gbc);
        fila = agregarFilaFormulario("Apellidos:", txtApellidos, fila, gbc);
        // ... (el resto de las filas se mantiene igual)

        // Fila 2: Género (ComboBox) y Fecha Nacimiento (JCalendar)
        fila = agregarFilaFormulario("Género:", cmbGenero, fila, gbc);
        fila = agregarFilaFormulario("Fecha Nacimiento:", dateChooserFechaNacimiento, fila, gbc);
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

        // Fila 10: Botón Guardar
        fila++;
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);


        // --- MÉTODOS DE FILTRADO DE ENTRADA (Mantenemos la lógica de filtro) ---
        // (Los cuerpos de estos métodos están al final)
    }

    // Aquí deben ir los cuerpos de los métodos KeyAdapterSoloLetras, KeyAdapterNumSimbolos,
    // agregarFilaFormulario, agregarFilaRadio, obtenerSeleccion, etc. (se asume que los mantienes)

    // Adaptador para permitir solo letras y espacios
    class KeyAdapterSoloLetras extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
                e.consume();
            }
        }
    }

    // Adaptador para permitir números y símbolos
    class KeyAdapterNumSimbolos extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && "+-()#".indexOf(c) == -1 && !Character.isISOControl(c)) {
                e.consume();
            }
        }
    }

    // (Resto de los métodos auxiliares: agregarFilaFormulario, agregarFilaRadio, obtenerSeleccion)

    private void aplicarFiltroSoloLetras(JTextField field) {
        field.addKeyListener(new KeyAdapterSoloLetras());
    }

    private void aplicarFiltroNumSimbolos(JTextField field) {
        field.addKeyListener(new KeyAdapterNumSimbolos());
    }

    private int agregarFilaFormulario(String etiqueta, JComponent componente, int filaActual, GridBagConstraints gbc) {
        gbc.gridx = 1; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(etiqueta), gbc);
        gbc.gridx = 2; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.WEST;
        add(componente, gbc);
        return filaActual + 1;
    }

    private int agregarFilaRadio(String etiqueta, JRadioButton rb1, JRadioButton rb2, int filaActual, GridBagConstraints gbc) {
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelRadio.add(rb1);
        panelRadio.add(rb2);
        gbc.gridx = 1; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(etiqueta), gbc);
        gbc.gridx = 2; gbc.gridy = filaActual; gbc.anchor = GridBagConstraints.WEST;
        add(panelRadio, gbc);
        return filaActual + 1;
    }

    private String obtenerSeleccion(ButtonGroup group) {
        for (java.util.Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }


    // --- Manejo de Acciones ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {

            // 1. Obtener los datos del formulario (la misma lógica de 17 campos)
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String genero = (String) cmbGenero.getSelectedItem();
            String fechaNac = (dateChooserFechaNacimiento.getDate() != null)
                    ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateChooserFechaNacimiento.getDate())
                    : "";
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

            // 2. Validación básica
            if (nombres.isEmpty() || apellidos.isEmpty() || genero.equals("Seleccionar...") || numDoc.isEmpty() || fechaNac.isEmpty() || tipoDoc.equals("Seleccionar...")) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos clave (Nombre, Género, Documento, Fecha).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Crear la lista de datos para guardar
            List<String> registro = List.of(
                    nombres, apellidos, genero, fechaNac, paisOrigen, idiomaPrincipal,
                    nivelEducativo, telefono, estatusLegal, tipoDoc, numDoc,
                    discapacidadSN, discapacidadTipo, enfermedadSN, enfermedadTipo, embarazadaSN, estadoEmpleo
            );

            // 4. Guardar y mostrar éxito
            DataManager.guardarRegistro(registro);

            JOptionPane.showMessageDialog(this, "¡Registro guardado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // CAMBIO: Regresar al menú principal después de guardar
            principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);

        } else if (e.getSource() == btnVolver) {
            // CAMBIO: Regresar al menú principal
            principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
        }
    }
}