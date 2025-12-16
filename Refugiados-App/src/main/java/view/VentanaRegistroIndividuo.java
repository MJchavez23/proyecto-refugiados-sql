package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.Setter;
import model.Hogar;
import model.enums.*;

@Getter
@Setter
public class VentanaRegistroIndividuo extends JPanel{ // CAMBIO: extends JPanel

    private VentanaPrincipal principal; // NUEVO: Referencia al contenedor principal
    private Map<String, Integer> familias;

    // --- Componentes del Formulario ---
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtPaisOrigen;
    private JTextField txtIdiomaPrincipal;
    private JTextField txtTelefono;
    private JTextField txtNumDocumento;
    private JTextField txtEnfermedad;
    private JTextField txtDiscapacidad;

    private JDateChooser dateChooserFechaNacimiento;

    private JComboBox<String> cmbGenero;
    private JComboBox<String> cmbNivelEducativo;
    private JComboBox<String> cmbEstatusLegal;
    private JComboBox<String> cmbTipoDocumento;
    private JComboBox<String> cmbEstadoEmpleo;
    private JComboBox<String> cmbnombresFamilias;

    private JRadioButton rbEmbarazadaSi, rbEmbarazadaNo;
    private ButtonGroup bgEmbarazada;

    private JRadioButton rbRepresentanteSi, rbRepresentanteNo;
    private ButtonGroup bgRepresentante;

    private JButton btnGuardar;
    private JButton btnVolver;

    // CAMBIO: El constructor recibe la VentanaPrincipal
    public VentanaRegistroIndividuo(VentanaPrincipal principal) {

        this.principal = principal;

        // --- Configuración del Panel ---
        // Se elimina setTitle, setDefaultCloseOperation, setSize, setLocationRelativeTo, setVisible

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);

        // --- 1. Inicialización de Componentes y Aplicación de Filtros ---

        btnVolver = new JButton("Volver al Menú");
        btnGuardar = new JButton("Guardar Registro");
        btnGuardar.setPreferredSize(new Dimension(250, 40));

        // Filtros (Se mantiene la lógica de filtro)
        txtNombres = new JTextField(20); aplicarFiltroSoloLetras(txtNombres);
        txtApellidos = new JTextField(20); aplicarFiltroSoloLetras(txtApellidos);
        txtPaisOrigen = new JTextField(20); aplicarFiltroSoloLetras(txtPaisOrigen);
        txtIdiomaPrincipal = new JTextField(20); aplicarFiltroSoloLetras(txtIdiomaPrincipal);
        txtTelefono = new JTextField(20); aplicarFiltroNumSimbolos(txtTelefono);
        txtNumDocumento = new JTextField(20); aplicarFiltroNumSimbolos(txtNumDocumento);
        txtDiscapacidad = new JTextField(20);
        txtEnfermedad = new JTextField(20);

        dateChooserFechaNacimiento = new JDateChooser();
        dateChooserFechaNacimiento.setPreferredSize(new Dimension(200, 25));

        String[] generos = Arrays.stream(Genero.values()).map(Genero::name).toArray(String[]::new);
        cmbGenero = new JComboBox<>(generos);
        cmbGenero.setSelectedIndex(-1);

        String[] nivelesEducativo = Arrays.stream(NivelEducacion.values()).map(NivelEducacion::name).toArray(String[]::new);
        cmbNivelEducativo = new JComboBox<>(nivelesEducativo);
        cmbNivelEducativo.setSelectedIndex(-1);

        String[] estatusLegal = Arrays.stream(EstatusLegal.values()).map(EstatusLegal::name).toArray(String[]::new);
        cmbEstatusLegal = new JComboBox<>(estatusLegal);
        cmbEstatusLegal.setSelectedIndex(-1);

        String[] tiposDocumento = Arrays.stream(TipoDocumento.values()).map(TipoDocumento::name).toArray(String[]::new);
        cmbTipoDocumento = new JComboBox<>(tiposDocumento);
        cmbTipoDocumento.setSelectedIndex(-1);

        String[] estadosEmpleo = Arrays.stream(EstadoEmpleo.values()).map(EstadoEmpleo::name).toArray(String[]::new);
        cmbEstadoEmpleo = new JComboBox<>(estadosEmpleo);
        cmbEstadoEmpleo.setSelectedIndex(-1);

        cmbnombresFamilias = new JComboBox<>();


        bgEmbarazada = new ButtonGroup();
        rbEmbarazadaSi = new JRadioButton("Sí"); rbEmbarazadaNo = new JRadioButton("No"); rbEmbarazadaNo.setSelected(true);
        bgEmbarazada.add(rbEmbarazadaSi); bgEmbarazada.add(rbEmbarazadaNo);

        bgRepresentante = new ButtonGroup();
        rbRepresentanteSi = new JRadioButton("Si"); rbRepresentanteNo = new JRadioButton("No"); rbRepresentanteNo.setSelected(true);
        bgRepresentante.add(rbRepresentanteSi); bgRepresentante.add(rbRepresentanteNo);


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
        fila = agregarFilaFormulario("Discapacidad:", txtDiscapacidad, fila, gbc);
        // Fila 8: Enfermedad Crónica (Si/No) y Tipo
        fila = agregarFilaFormulario("Enfermedad Crónica:", txtEnfermedad, fila, gbc);
        // Fila 9: Embarazada (Si/No)
        fila = agregarFilaRadio("Embarazada:", rbEmbarazadaSi, rbEmbarazadaNo, fila, gbc);
        fila = agregarFilaRadio("Representante", rbRepresentanteSi, rbRepresentanteNo, fila, gbc);
        fila = agregarFilaFormulario("Familias:", cmbnombresFamilias, fila, gbc);

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
    public void llenarFamilias(){
        cmbnombresFamilias.removeAllItems();
        for (String h : familias.keySet()){
                cmbnombresFamilias.addItem(h);
        }
        cmbnombresFamilias.setSelectedIndex(-1);
    }

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

    public List<String> extraerInformacion() {

            // 1. Obtener los datos del formulario (la misma lógica de 17 campos)
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            if (cmbGenero.getSelectedItem() == null) {
                mostrarError("Seleccione un Genero");
                return  List.of();
            }
            String genero = cmbGenero.getSelectedItem().toString();
            if (dateChooserFechaNacimiento == null || dateChooserFechaNacimiento.getDate() == null) {
                mostrarError("Seleccione un Fecha");
                return  List.of();
            }
            LocalDate fechaNac = dateChooserFechaNacimiento.getDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            String paisOrigen = txtPaisOrigen.getText();
            String idiomaPrincipal = txtIdiomaPrincipal.getText();
            if (cmbNivelEducativo.getSelectedItem() == null) {
                mostrarError("Seleccione un Nivel Educativo");
                return  List.of();
            }
            String nivelEducativo = cmbNivelEducativo.getSelectedItem().toString();
            String telefono = txtTelefono.getText();
            if(cmbEstatusLegal.getSelectedItem() == null) {
                mostrarError("Seleccione un Estatus");
                return  List.of();
            }
            String estatusLegal = cmbEstatusLegal.getSelectedItem().toString();
            if(cmbTipoDocumento.getSelectedItem() == null) {
                mostrarError("Seleccione un Tipo de Documento");
                return  List.of();
            }
            String tipoDoc = cmbTipoDocumento.getSelectedItem().toString();
            String numDoc = txtNumDocumento.getText();

            String discapacidad = txtDiscapacidad.getText();
            String enfermedadSN = txtEnfermedad.getText();
            String embarazadaSN = obtenerSeleccion(bgEmbarazada);
            String representanteSN = obtenerSeleccion(bgRepresentante);

            if (cmbEstadoEmpleo.getSelectedItem() == null) {
                mostrarError("Seleccione un Estado Empleo");
                return List.of();
            }
            String estadoEmpleo = cmbEstadoEmpleo.getSelectedItem().toString();
            if (cmbnombresFamilias.getSelectedItem() == null) {
                mostrarError("Seleccione un Nombres Familia");
                return  List.of();
            }
            String idFamilia = familias.get(cmbnombresFamilias.getSelectedItem().toString()).toString();

            // 2. Validación básica
            if (nombres.isBlank()|| apellidos.isBlank() || genero.isEmpty() || numDoc.isBlank() || fechaNac == null || tipoDoc.isBlank() || paisOrigen.isEmpty() || idiomaPrincipal.isBlank() || telefono.isBlank() || discapacidad.isBlank() ||enfermedadSN.isBlank() || embarazadaSN.isBlank() || representanteSN.isBlank()) {
                mostrarError("Debe completar todos los campos clave.");
                return List.of();
            }

            // 3. Crear la lista de datos para guardar
            return List.of(
                    nombres, apellidos, genero, fechaNac.toString(), paisOrigen, idiomaPrincipal,
                    nivelEducativo, telefono, estatusLegal, tipoDoc, numDoc,
                    discapacidad, enfermedadSN, embarazadaSN, estadoEmpleo, representanteSN, idFamilia
            );
    }

    public void agregarListener(ActionListener evento) {
        btnVolver.addActionListener(evento);
        btnGuardar.addActionListener(evento);
    }

    public void mostrarMensajeIndividuoGuardado() {
        JOptionPane.showMessageDialog(this, "¡Registro guardado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void volverMenuPrincipal() {
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
    }

    public void cambiarPanel(){
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_REGISTRO);
    }

}