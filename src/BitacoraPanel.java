import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BitacoraPanel extends JPanel implements ActionListener {

    private VentanaPrincipal principal;
    private JTable tablaBitacora;
    private DefaultTableModel tableModel;
    private JButton btnSubirArchivo;
    private JButton btnVolver;

    public BitacoraPanel(VentanaPrincipal principal) {
        this.principal = principal;

        // Usamos BorderLayout para el panel (Norte: botones, Centro: tabla)
        setLayout(new BorderLayout(10, 10));

        // --- 1. Configuración de la Tabla (Estilo Excel no editable) ---
        String[] columnNames = {"ID", "Fecha", "Detalle", "Usuario"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacemos la tabla no editable
            }
        };
        tablaBitacora = new JTable(tableModel);
        tablaBitacora.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(tablaBitacora);

        // --- 2. Panel de Controles (Norte) ---
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);

        btnSubirArchivo = new JButton("Subir Bitácora (.xlsx)");
        btnSubirArchivo.addActionListener(this);

        panelControles.add(btnVolver);
        panelControles.add(btnSubirArchivo);

        // --- 3. Ensamblaje ---
        add(new JLabel("Registro de Bitácora (Solo Lectura)", SwingConstants.CENTER), BorderLayout.NORTH); // Título
        add(scrollPane, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH); // Botones abajo
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);

        } else if (e.getSource() == btnSubirArchivo) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();

                // Validación simple de extensión
                if (fileName.toLowerCase().endsWith(".xlsx")) {
                    // Aquí iría la lógica real para leer el archivo XLSX y poblar la tabla.
                    // Por ahora, solo mostramos el mensaje de éxito.
                    JOptionPane.showMessageDialog(this,
                            "Archivo seleccionado: " + fileName + "\n" +
                                    "Se necesitaría una librería (e.g., Apache POI) para leer el contenido real de XLSX.",
                            "Subida de Archivo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Formato de archivo no soportado. Por favor, suba un archivo .xlsx.",
                            "Error de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}