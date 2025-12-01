import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DataManager {

    private static final String NOMBRE_ARCHIVO = "registros_individuos.csv";

    // ¡ENCABEZADOS ACTUALIZADOS (17 campos)!
    private static final String ENCABEZADOS = "nombres,apellidos,genero,fechaNac,paisOrigen,idiomaPrincipal,nivelEducativo,telefono,estatusLegal,tipoDoc,numDoc,discapacidadSN,discapacidadTipo,enfermedadSN,enfermedadTipo,embarazadaSN,estadoEmpleo";
    private static final int NUMERO_CAMPOS = 17;

    /**
     * Escribe una nueva línea de datos en el archivo CSV.
     * @param datos Lista de strings con los datos de un individuo.
     */
    public static void guardarRegistro(List<String> datos) {
        // Convierte la lista de datos a una línea separada por comas
        String linea = String.join(",", datos);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {

            // Si el archivo está vacío (o no existe), escribe primero los encabezados
            File archivo = new File(NOMBRE_ARCHIVO);
            if (archivo.length() == 0) {
                writer.write(ENCABEZADOS);
                writer.newLine();
            }

            // Escribe la nueva línea de datos
            writer.write(linea);
            writer.newLine();
            System.out.println("Registro guardado en el archivo: " + NOMBRE_ARCHIVO);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el registro en el archivo.", "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lee todos los registros del archivo CSV.
     * @return Una lista de listas de strings.
     */
    public static List<List<String>> cargarTodosLosRegistros() {
        List<List<String>> registros = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            boolean primerLinea = true;

            while ((linea = reader.readLine()) != null) {
                // Saltar la línea de encabezado
                if (primerLinea) {
                    primerLinea = false;
                    continue;
                }

                // Divide la línea por comas
                String[] campos = linea.split(",", -1);

                // Asegurar que el registro tiene 17 campos antes de agregarlo
                if (campos.length == NUMERO_CAMPOS) {
                    registros.add(List.of(campos));
                } else {
                    // Ignorar líneas que no coinciden con el formato de 17 campos
                    System.out.println("ADVERTENCIA: Saltando línea con formato incorrecto. Campos encontrados: " + campos.length);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de registros no encontrado. Se creará al guardar el primer registro.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer los registros del archivo.", "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
        return registros;
    }
}