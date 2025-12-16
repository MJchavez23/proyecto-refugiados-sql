package view;

import lombok.Getter;
import model.Individuo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter
public class VentanaBusqueda extends JPanel {

    private VentanaPrincipal principal;
    private JTextField txtNumeroDocumento;
    private JButton btnBuscar;
    private JButton btnVolver;

    public VentanaBusqueda(VentanaPrincipal principal) {
        this.principal = principal;

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(150, 35));

        txtNumeroDocumento = new JTextField(15);
        txtNumeroDocumento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNumeroDocumento.setMargin(new Insets(4, 4, 4, 4));
        txtNumeroDocumento.addKeyListener(new KeyAdapterSoloNumeros());

        btnBuscar = new JButton("Buscar Individuo");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setPreferredSize(new Dimension(200, 40));
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblDocumento = new JLabel("Número de Documento:");
        lblDocumento.setFont(new Font("Segoe UI", Font.BOLD, 14));

        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(btnVolver, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblDocumento, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtNumeroDocumento, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnBuscar, gbc);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarIndividuo(Individuo ind) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 13));
        JOptionPane.showMessageDialog(this,
                "Individuo encontrado:\n\n" +
                        "Id: " + ind.getId() + "\n" +
                        "Nombre: " + ind.getNombre() + "\n" +
                        "Apellido: " + ind.getApellido() + "\n" +
                        "Genero: " + ind.getGenero().name() + "\n" +
                        "Fecha Nacimiento: " + ind.getFechaNacimiento() + "\n" +
                        "Pais Origen: " + ind.getPaisOrigen() + "\n" +
                        "Idioma Principal: " + ind.getIdiomaPrincipal() + "\n" +
                        "Nivel Educativo: " + ind.getNivelEducacion() + "\n" +
                        "Telefono: " + ind.getTelefono() + "\n" +
                        "Estatus Legal: " + ind.getEstatusLegal() + "\n" +
                        "Numero Documento: " + ind.getNumeroDocumento() + "\n" +
                        "Estado Empleo: " + ind.getEstadoEmpleo() + "\n" +
                        "Discapacidad: " + ind.getDiscapacidad() + "\n" +
                        "Enfermedad Cronica: " + ind.getEnfermedadCronica() + "\n" +
                        "Hogar: " + (ind.getHogar() != null ? ind.getHogar().getNombreHogar() : "N/A") + "\n" +
                        "Refugio: " + (ind.getHogar() != null && ind.getHogar().getRefugio() != null ? ind.getHogar().getRefugio().getNombre() : "N/A") + "\n",
                "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    class KeyAdapterSoloNumeros extends KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                e.consume();
            }
        }
    }

    public void agregarListener(ActionListener evento) {
        btnVolver.addActionListener(evento);
        btnBuscar.addActionListener(evento);
    }

    public void cambiarPanel(){
        txtNumeroDocumento.setText("");
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_BUSQUEDA);
    }

    public String extraerNumeroDocumento(){
        String numero = txtNumeroDocumento.getText();
        if (numero.isBlank()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el Número de Documento.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
        return numero;
    }

    public void volverMenuPrincipal() {
        principal.mostrarPanel(VentanaPrincipal.NOMBRE_MENU);
    }
}