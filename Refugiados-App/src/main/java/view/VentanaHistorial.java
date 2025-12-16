package view;

import lombok.Getter;
import model.HistorialServicio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class VentanaHistorial extends JPanel {

    private VentanaPrincipal principal;


    private JTextField cmbServicio;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;
    private JButton btnVolver;
    private JButton btnVerHistorial;


    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;

    public VentanaHistorial(VentanaPrincipal principal) {
        this.principal = principal;


        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inicializarFormularioSuperior();
        inicializarTablaInferior();
    }

    private void inicializarFormularioSuperior() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());

        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Evento"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Numero Documento:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        cmbServicio = new JTextField(15);
        panelFormulario.add(cmbServicio, gbc);


        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panelFormulario.add(scrollDescripcion, gbc);


        gbc.gridx = 0; gbc.gridy = 3;
        btnVolver = new JButton("Volver");
        panelFormulario.add(btnVolver, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        btnGuardar = new JButton("Crear Historial");
        panelFormulario.add(btnGuardar, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        btnVerHistorial = new JButton("Buscar y Ver Historial en Tabla");
        panelFormulario.add(btnVerHistorial, gbc);

        add(panelFormulario, BorderLayout.NORTH);
    }


    private void inicializarTablaInferior() {

        String[] columnas = {"Nombre Servicio", "Estado Serivicio", "Familia", "Descripción Historial", };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHistorial = new JTable(modeloTabla);
        tablaHistorial.setFillsViewportHeight(true);
        tablaHistorial.setRowHeight(25);


        JScrollPane scrollTabla = new JScrollPane(tablaHistorial);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial Detallado"));

        add(scrollTabla, BorderLayout.CENTER);
    }


    public void llenarTabla(List<HistorialServicio> lista) {

        modeloTabla.setRowCount(0);

        if (lista == null || lista.isEmpty()) {
            return;
        }

        for (HistorialServicio h : lista) {
            Object[] fila = {
                    h.getServicio().getNombreServicio(),
                    h.getServicio().getEstadoServicio(),
                    h.getServicio().getHogar().getNombreHogar(),
                    h.getDescripcion()
            };
            modeloTabla.addRow(fila);
        }
    }



    public String obtenerNumeroDocumento() {
        if (cmbServicio.getText() == null) {
            return "";
        }
        return cmbServicio.getText().trim();
    }

    public String obtenerHistorialDescripcion() {
        if (txtDescripcion.getText() == null) {
            return "";
        }
        return txtDescripcion.getText().trim();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String s) {
        JOptionPane.showMessageDialog(this, s, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void volverMenuPrincipal() {
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
    }

    public void cambiarPanel(){
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_HISTORIAL);
    }

    public void agregarListener(ActionListener evento) {
        btnGuardar.addActionListener(evento);
        btnVolver.addActionListener(evento);
        btnVerHistorial.addActionListener(evento);
    }
}