import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class VentanaBusqueda extends JPanel implements ActionListener {

    private VentanaPrincipal principal;
    private JTextField txtNumeroDocumento; // Solo queda este campo
    private JButton btnBuscar;
    private JButton btnVolver;

    public VentanaBusqueda(VentanaPrincipal principal) {
        this.principal = principal;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // --- Inicialización de Componentes ---

        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(this);

        txtNumeroDocumento = new JTextField(15);
        aplicarFiltroSoloNumeros(txtNumeroDocumento); // **NUEVO FILTRO**

        btnBuscar = new JButton("   Buscar   ");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 18));
        btnBuscar.addActionListener(this);

        // --- Posicionamiento (Layout) de Componentes ---

        // Botón Volver
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);

        // Etiqueta Número de Documento
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Número de Documento:"), gbc);

        // Campo Número de Documento
        gbc.gridx = 2; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(txtNumeroDocumento, gbc);

        // Botón Buscar
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnBuscar, gbc);
    }

    // --- FILTRO DE ENTRADA (Solo dígitos) ---
    private void aplicarFiltroSoloNumeros(JTextField field) {
        field.addKeyListener(new KeyAdapterSoloNumeros());
    }

    class KeyAdapterSoloNumeros extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            // Permite dígitos (0-9) y teclas de control (como Backspace)
            if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                e.consume(); // Ignorar la tecla
            }
        }
    }

    // --- Lógica de Búsqueda ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            String numero = txtNumeroDocumento.getText();

            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el Número de Documento.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aquí se asume que se buscará el documento sin importar el tipo,
            // solo por el número.
            List<List<String>> registros = DataManager.cargarTodosLosRegistros();
            List<String> resultado = null;

            // Índice para el formato de 17 campos: Número Doc (10)
            final int INDICE_NUM_DOC = 10;
            final int MINIMO_CAMPOS_REQUERIDO = 11;

            for (List<String> registro : registros) {
                if (registro.size() >= MINIMO_CAMPOS_REQUERIDO &&
                        registro.get(INDICE_NUM_DOC).equals(numero)) {
                    resultado = registro;
                    break;
                }
            }

            // Mostrar el resultado
            if (resultado != null) {
                JOptionPane.showMessageDialog(this,
                        "Individuo encontrado:\n" +
                                "Nombres: " + resultado.get(0) + "\n" +
                                "Apellidos: " + resultado.get(1) + "\n" +
                                "País Origen: " + resultado.get(4),
                        "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún individuo con el documento proporcionado.", "Búsqueda Fallida", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnVolver) {
            principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
        }
    }
}