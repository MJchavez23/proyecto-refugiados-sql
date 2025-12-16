package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.Setter;
import model.enums.*;

@Getter
@Setter
public class VentanaRegistroIndividuo extends JPanel {

    private VentanaPrincipal principal;
    private Map<String, Integer> familias;

    // Campos
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

    private JButton btnSubirArchivo;
    private JButton btnGuardar;
    private JButton btnVolver;

    public VentanaRegistroIndividuo(VentanaPrincipal principal) {
        this.principal = principal;


        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnVolver = new JButton("Volver al Menú");
        panelSuperior.add(btnVolver);
        add(panelSuperior, BorderLayout.NORTH);


        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Individuo"));

        JPanel panelCentralWrapper = new JPanel(new GridBagLayout());
        panelCentralWrapper.add(panelFormulario);

        JScrollPane scrollPane = new JScrollPane(panelCentralWrapper);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;


        btnGuardar = new JButton("Guardar Registro");
        btnGuardar.setPreferredSize(new Dimension(180, 30));

        btnSubirArchivo = new JButton("Carga Masiva (Excel)");
        btnSubirArchivo.setPreferredSize(new Dimension(180, 30));


        txtNombres = new JTextField(20); aplicarFiltroSoloLetras(txtNombres);
        txtApellidos = new JTextField(20); aplicarFiltroSoloLetras(txtApellidos);
        txtPaisOrigen = new JTextField(20); aplicarFiltroSoloLetras(txtPaisOrigen);
        txtIdiomaPrincipal = new JTextField(20); aplicarFiltroSoloLetras(txtIdiomaPrincipal);
        txtTelefono = new JTextField(20); aplicarFiltroNumSimbolos(txtTelefono);
        txtNumDocumento = new JTextField(20);
        txtDiscapacidad = new JTextField(20);
        txtEnfermedad = new JTextField(20);

        dateChooserFechaNacimiento = new JDateChooser();
        dateChooserFechaNacimiento.setPreferredSize(new Dimension(223, 24));


        String[] generos = Arrays.stream(Genero.values()).map(Genero::name).toArray(String[]::new);
        cmbGenero = new JComboBox<>(generos); cmbGenero.setSelectedIndex(-1);
        cmbGenero.setPreferredSize(new Dimension(223, 24));

        String[] nivelesEducativo = Arrays.stream(NivelEducacion.values()).map(NivelEducacion::name).toArray(String[]::new);
        cmbNivelEducativo = new JComboBox<>(nivelesEducativo); cmbNivelEducativo.setSelectedIndex(-1);
        cmbNivelEducativo.setPreferredSize(new Dimension(223, 24));

        String[] estatusLegal = Arrays.stream(EstatusLegal.values()).map(EstatusLegal::name).toArray(String[]::new);
        cmbEstatusLegal = new JComboBox<>(estatusLegal); cmbEstatusLegal.setSelectedIndex(-1);
        cmbEstatusLegal.setPreferredSize(new Dimension(223, 24));

        String[] tiposDocumento = Arrays.stream(TipoDocumento.values()).map(TipoDocumento::name).toArray(String[]::new);
        cmbTipoDocumento = new JComboBox<>(tiposDocumento); cmbTipoDocumento.setSelectedIndex(-1);
        cmbTipoDocumento.setPreferredSize(new Dimension(223, 24));

        String[] estadosEmpleo = Arrays.stream(EstadoEmpleo.values()).map(EstadoEmpleo::name).toArray(String[]::new);
        cmbEstadoEmpleo = new JComboBox<>(estadosEmpleo); cmbEstadoEmpleo.setSelectedIndex(-1);
        cmbEstadoEmpleo.setPreferredSize(new Dimension(223, 24));

        cmbnombresFamilias = new JComboBox<>();
        cmbnombresFamilias.setPreferredSize(new Dimension(223, 24));


        bgEmbarazada = new ButtonGroup();
        rbEmbarazadaSi = new JRadioButton("Sí"); rbEmbarazadaNo = new JRadioButton("No"); rbEmbarazadaNo.setSelected(true);
        bgEmbarazada.add(rbEmbarazadaSi); bgEmbarazada.add(rbEmbarazadaNo);

        bgRepresentante = new ButtonGroup();
        rbRepresentanteSi = new JRadioButton("Si"); rbRepresentanteNo = new JRadioButton("No"); rbRepresentanteNo.setSelected(true);
        bgRepresentante.add(rbRepresentanteSi); bgRepresentante.add(rbRepresentanteNo);


        int fila = 0;
        fila = agregarFilaFormulario(panelFormulario, "Nombres:", txtNombres, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Apellidos:", txtApellidos, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Género:", cmbGenero, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Fecha Nacimiento:", dateChooserFechaNacimiento, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "País Origen:", txtPaisOrigen, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Idioma Principal:", txtIdiomaPrincipal, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Nivel Educativo:", cmbNivelEducativo, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Teléfono:", txtTelefono, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Estatus Legal:", cmbEstatusLegal, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Tipo Documento:", cmbTipoDocumento, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Número Documento:", txtNumDocumento, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Estado Empleo:", cmbEstadoEmpleo, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Discapacidad:", txtDiscapacidad, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Enfermedad Crónica:", txtEnfermedad, fila, gbc);
        fila = agregarFilaRadio(panelFormulario, "Embarazada:", rbEmbarazadaSi, rbEmbarazadaNo, fila, gbc);
        fila = agregarFilaRadio(panelFormulario, "Representante:", rbRepresentanteSi, rbRepresentanteNo, fila, gbc);
        fila = agregarFilaFormulario(panelFormulario, "Familias:", cmbnombresFamilias, fila, gbc);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelAcciones.setBorder(BorderFactory.createEtchedBorder());
        panelAcciones.add(btnGuardar);
        panelAcciones.add(btnSubirArchivo);

        add(panelAcciones, BorderLayout.SOUTH);
    }


    private int agregarFilaFormulario(JPanel panel, String etiqueta, JComponent componente, int filaActual, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = filaActual;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        gbc.gridy = filaActual;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(componente, gbc);

        return filaActual + 1;
    }

    private int agregarFilaRadio(JPanel panel, String etiqueta, JRadioButton rb1, JRadioButton rb2, int filaActual, GridBagConstraints gbc) {
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelRadio.add(rb1);
        panelRadio.add(new JLabel("   "));
        panelRadio.add(rb2);

        gbc.gridx = 0;
        gbc.gridy = filaActual;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        gbc.gridy = filaActual;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(panelRadio, gbc);

        return filaActual + 1;
    }

    public File extraerArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();

            if (fileName.toLowerCase().endsWith(".xlsx")) {
                return selectedFile;
            }
        }
        return null;
    }

    public List<String> extraerInformacion() {
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        if (cmbGenero.getSelectedItem() == null) { mostrarError("Seleccione un Genero"); return List.of(); }
        String genero = cmbGenero.getSelectedItem().toString();

        if (dateChooserFechaNacimiento == null || dateChooserFechaNacimiento.getDate() == null) { mostrarError("Seleccione una Fecha"); return List.of(); }
        LocalDate fechaNac = dateChooserFechaNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String paisOrigen = txtPaisOrigen.getText();
        String idiomaPrincipal = txtIdiomaPrincipal.getText();

        if (cmbNivelEducativo.getSelectedItem() == null) { mostrarError("Seleccione un Nivel Educativo"); return List.of(); }
        String nivelEducativo = cmbNivelEducativo.getSelectedItem().toString();

        String telefono = txtTelefono.getText();

        if(cmbEstatusLegal.getSelectedItem() == null) { mostrarError("Seleccione un Estatus"); return List.of(); }
        String estatusLegal = cmbEstatusLegal.getSelectedItem().toString();

        if(cmbTipoDocumento.getSelectedItem() == null) { mostrarError("Seleccione un Tipo de Documento"); return List.of(); }
        String tipoDoc = cmbTipoDocumento.getSelectedItem().toString();
        String numDoc = txtNumDocumento.getText();

        String discapacidad = txtDiscapacidad.getText();
        String enfermedadSN = txtEnfermedad.getText();
        String embarazadaSN = obtenerSeleccion(bgEmbarazada);
        String representanteSN = obtenerSeleccion(bgRepresentante);

        if (cmbEstadoEmpleo.getSelectedItem() == null) { mostrarError("Seleccione un Estado Empleo"); return List.of(); }
        String estadoEmpleo = cmbEstadoEmpleo.getSelectedItem().toString();

        if (cmbnombresFamilias.getSelectedItem() == null) { mostrarError("Seleccione un Nombres Familia"); return List.of(); }
        String idFamilia = familias.get(cmbnombresFamilias.getSelectedItem().toString()).toString();

        if (nombres.isBlank()|| apellidos.isBlank() || genero.isEmpty() || numDoc.isBlank() || fechaNac == null || tipoDoc.isBlank() || paisOrigen.isEmpty() || idiomaPrincipal.isBlank() || telefono.isBlank() || discapacidad.isBlank() ||enfermedadSN.isBlank() || embarazadaSN.isBlank() || representanteSN.isBlank()) {
            mostrarError("Debe completar todos los campos clave.");
            return List.of();
        }

        return List.of(
                nombres, apellidos, genero, fechaNac.toString(), paisOrigen, idiomaPrincipal,
                nivelEducativo, telefono, estatusLegal, tipoDoc, numDoc,
                discapacidad, enfermedadSN, embarazadaSN, estadoEmpleo, representanteSN, idFamilia
        );
    }

    public void agregarListener(ActionListener evento) {
        btnVolver.addActionListener(evento);
        btnGuardar.addActionListener(evento);
        btnSubirArchivo.addActionListener(evento);
    }

    public void llenarFamilias(){
        cmbnombresFamilias.removeAllItems();
        if (familias != null) {
            for (String h : familias.keySet()){
                cmbnombresFamilias.addItem(h);
            }
        }
        cmbnombresFamilias.setSelectedIndex(-1);
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

    class KeyAdapterSoloLetras extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) e.consume();
        }
    }

    class KeyAdapterNumSimbolos extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && "+-()#".indexOf(c) == -1 && !Character.isISOControl(c)) e.consume();
        }
    }

    private void aplicarFiltroSoloLetras(JTextField field) { field.addKeyListener(new KeyAdapterSoloLetras()); }
    private void aplicarFiltroNumSimbolos(JTextField field) { field.addKeyListener(new KeyAdapterNumSimbolos()); }

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

     public void mostrarExito(String individuosGuardadosCorrectamente) {
        JOptionPane.showMessageDialog(this, "¡Registros guardados exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}