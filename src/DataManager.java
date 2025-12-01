import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane; // ¡Importación necesaria para mostrar mensajes!

public class DataManager {

    // Nombre del archivo donde se guardarán los datos
    private static final String NOMBRE_ARCHIVO = "registros_individuos.csv";

    // Encabezados del archivo CSV (Orden exacto que se usa para guardar y buscar)
    private static final String ENCABEZADOS = "nombre,fechaNacimiento,nacionalidad,genero,tipoDocumento,numeroDocumento,notas";

    /**
     * Escribe una nueva línea de datos en el archivo CSV.
     * @param datos Lista de strings con los datos de un individuo.
     */
    public static void guardarRegistro(List<String> datos) {
        // Convierte la lista de datos a una línea separada por comas
        String linea = String.join(",", datos);

        // Usamos 'true' en FileWriter para APPEND (añadir al final)
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
            // Muestra un mensaje de error si falla la escritura
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
                String[] campos = linea.split(",", -1); // El -1 asegura que se incluyan campos vacíos

                // Si el número de campos es correcto (debería ser 7), lo agregamos
                if (campos.length == 7) {
                    registros.add(List.of(campos));
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