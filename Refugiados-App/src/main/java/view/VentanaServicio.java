package view;

import lombok.Getter;
import lombok.Setter;
import model.enums.TipoServicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
@Setter
@Getter
public class VentanaServicio extends JPanel {

    private VentanaPrincipal principal;

    // Nuevos componentes
    private JComboBox<String> cmbFamilias;
    private JComboBox<TipoServicio> cmbTipoServicio;

    private JTextArea txtDescripcion;
    private JButton btnGuardar;
    private JButton btnVolver;

    // Map para manejar la relación Nombre -> ID de familia
    private Map<String, Integer> familiasMap;

    public VentanaServicio(VentanaPrincipal principal) {
        this.principal = principal;
        this.familiasMap = new HashMap<>(); // Inicializamos vacío

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // --- Inicialización de Componentes ---

        // 1. Combo Familias
        cmbFamilias = new JComboBox<>();
        cmbFamilias.setPreferredSize(new Dimension(200, 25));

        // 2. Combo Tipo Servicio
        cmbTipoServicio = new JComboBox<>(TipoServicio.values());
        cmbTipoServicio.setPreferredSize(new Dimension(200, 25));
        cmbTipoServicio.setSelectedIndex(-1);

        // 4. Descripción
        txtDescripcion = new JTextArea(5, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);

        // Botones
        btnVolver = new JButton("Volver al Menú");
        btnGuardar = new JButton("Guardar Servicio");
        btnGuardar.setPreferredSize(new Dimension(200, 35));

        // --- Posicionamiento (GridBagLayout) ---
        int fila = 0;

        // Fila 0: Botón Volver
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);
        fila++;

        // Fila 1: Título
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("<html><h2>Registro de Servicios</h2></html>"), gbc);
        fila++;

        // Fila 2: Familia (Reemplaza al documento)
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Familia Beneficiaria:"), gbc);
        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(cmbFamilias, gbc);
        fila++;

        // Fila 3: Tipo de Servicio
        gbc.gridx = 1; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo de Servicio:"), gbc);
        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        add(cmbTipoServicio, gbc);
        fila++;


        // Fila 5: Descripción
        gbc.gridx = 1; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Detalle del Servicio:"), gbc);
        gbc.gridx = 2; gbc.gridy = fila; gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollDescripcion, gbc);
        gbc.fill = GridBagConstraints.NONE;
        fila++;

        // Fila 6: Botón Guardar
        gbc.gridx = 1; gbc.gridy = fila; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);
    }

    public void cargarFamilias() {
        cmbFamilias.removeAllItems();

        if (familiasMap != null) {
            for (String nombreFamilia : familiasMap.keySet()) {
                cmbFamilias.addItem(nombreFamilia);
            }
        }
        cmbFamilias.setSelectedIndex(-1);
    }


    public List<String> obtenerDatosDelFormulario() {
        if (cmbFamilias.getSelectedItem() == null || cmbFamilias.getSelectedIndex() == -1) {
            mostrarError("Debe seleccionar una Familia beneficiaria.");
            return List.of();
        }

        if (cmbTipoServicio.getSelectedItem() == null) {
            mostrarError("Debe seleccionar un Tipo de Servicio.");
            return List.of();

        }

        if (txtDescripcion.getText().trim().isEmpty()) {
            mostrarError("La descripción del servicio es obligatoria.");
            return List.of();

        }

        List<String> datos = new ArrayList<>();

        String nombreFamilia = (String) cmbFamilias.getSelectedItem();
        Integer idFamilia = familiasMap.get(nombreFamilia);

        datos.add(String.valueOf(idFamilia));

        String tipo = cmbTipoServicio.getSelectedItem().toString();
        datos.add(tipo);

        datos.add(txtDescripcion.getText().trim());

        return datos;
    }


    public void aplicarListener(ActionListener evento) {
        btnGuardar.addActionListener(evento);
        btnVolver.addActionListener(evento);
    }


    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void volverMenuPrincipal() {
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
    }

    public void cambiarPanel(){
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_SERVICIO);
    }

    public void mostrarMensajeSucess() {
        JOptionPane.showMessageDialog(this, "Se guardo correctamente", "Exito!", JOptionPane.INFORMATION_MESSAGE);
    }
}