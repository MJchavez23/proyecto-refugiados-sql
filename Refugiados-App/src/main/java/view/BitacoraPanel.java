package view;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

@Getter
public class BitacoraPanel extends JPanel {

    private VentanaPrincipal principal;
    private JTable tablaBitacora;
    private DefaultTableModel tableModel;
    private JButton btnSubirArchivo;
    private JButton btnVolver;

    public BitacoraPanel(VentanaPrincipal principal) {
        this.principal = principal;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inicializarControlesSuperiores();
        inicializarTablaCentral();
    }

    private void inicializarControlesSuperiores() {
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelControles.setBorder(BorderFactory.createTitledBorder("Acciones de Bitácora"));

        btnVolver = new JButton("Volver al Menú");
        btnSubirArchivo = new JButton("Subir Archivo (.xlsx)");

        panelControles.add(btnVolver);
        panelControles.add(btnSubirArchivo);

        add(panelControles, BorderLayout.NORTH);
    }


    private void inicializarTablaCentral() {
        String[] columnNames = {"Fecha", "Accion", "Usuario", "Tabla"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaBitacora = new JTable(tableModel);
        tablaBitacora.setFillsViewportHeight(true);
        tablaBitacora.setRowHeight(25);
        tablaBitacora.getTableHeader().setReorderingAllowed(false);


        JScrollPane scrollPane = new JScrollPane(tablaBitacora);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registros del Sistema"));

        add(scrollPane, BorderLayout.CENTER);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void volverMenuPrincipal() {
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
    }

    public void cambiarPanel(){
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_BITACORA);
    }

    public void agregarListener(ActionListener evento) {
        btnVolver.addActionListener(evento);
        btnSubirArchivo.addActionListener(evento);
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

    public void agregarFila(Object[] datos) {
        tableModel.addRow(datos);
    }

    public void limpiarTabla() {
        tableModel.setRowCount(0);
    }
}