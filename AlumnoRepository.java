import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {

    private final Path archivo;

    /**
     * Constructor.
     * Recibe el nombre del archivo donde se almacenarán
     * los alumnos.
     */
    public AlumnoRepository(String nombreArchivo) {
        this.archivo = Path.of(nombreArchivo);

        // Crear el archivo si no existe
        try {
            if (!Files.exists(archivo)) {
                Files.createFile(archivo);
                System.out.println("Archivo " + nombreArchivo + " creado correctamente.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    /**
     * CREA un nuevo alumno.
     */
    public boolean agregarAlumno(Alumno alumno) {

        // Verificar que el ID no esté duplicado
        if (buscarPorId(alumno.getId()) != null) {
            return false;
        }

        try {
            /*
             * APPEND permite agregar el registro al final
             * sin borrar los registros existentes.
             */
            Files.writeString(
                    archivo,
                    alumno.toString() + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            return true;

        } catch (IOException e) {
            System.out.println("Error al guardar el alumno: " + e.getMessage());
            return false;
        }
    }

    /**
     * LEE todos los alumnos del archivo.
     */
    public List<Alumno> obtenerTodos() {

        List<Alumno> alumnos = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(archivo);

            for (String linea : lineas) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                Alumno alumno = convertirLineaAAlumno(linea);

                if (alumno != null) {
                    alumnos.add(alumno);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return alumnos;
    }

    /**
     * BUSCA un alumno por ID.
     */
    public Alumno buscarPorId(int id) {

        try {
            List<String> lineas = Files.readAllLines(archivo);

            for (String linea : lineas) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                Alumno alumno = convertirLineaAAlumno(linea);

                if (alumno != null && alumno.getId() == id) {
                    return alumno;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al buscar el alumno: " + e.getMessage());
        }

        return null;
    }

    /**
     * ACTUALIZA el nombre de un alumno.
     */
    public boolean actualizarAlumno(int id, String nuevoNombre) {

        try {
            List<String> lineas = Files.readAllLines(archivo);
            boolean encontrado = false;

            for (int i = 0; i < lineas.size(); i++) {

                String linea = lineas.get(i);

                if (linea.trim().isEmpty()) {
                    continue;
                }

                Alumno alumno = convertirLineaAAlumno(linea);

                if (alumno != null && alumno.getId() == id) {

                    // Reemplazar solamente esa línea
                    lineas.set(i, id + " - " + nuevoNombre);

                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {

                /*
                 * Se escribe nuevamente el contenido actualizado.
                 */
                Files.write(
                        archivo,
                        lineas,
                        StandardOpenOption.TRUNCATE_EXISTING
                );

                return true;
            }

        } catch (IOException e) {
            System.out.println("Error al actualizar el alumno: " + e.getMessage());
        }

        return false;
    }

    /**
     * ELIMINA un alumno por ID.
     */
    public boolean eliminarAlumno(int id) {

        try {
            List<String> lineas = Files.readAllLines(archivo);
            boolean encontrado = false;

            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                Alumno alumno = convertirLineaAAlumno(linea);

                if (alumno != null && alumno.getId() == id) {

                    // No agregamos esta línea.
                    encontrado = true;

                } else {
                    nuevasLineas.add(linea);
                }
            }

            if (encontrado) {

                /*
                 * Sobrescribimos el archivo con las líneas
                 * que no fueron eliminadas.
                 */
                Files.write(
                        archivo,
                        nuevasLineas,
                        StandardOpenOption.TRUNCATE_EXISTING
                );

                return true;
            }

        } catch (IOException e) {
            System.out.println("Error al eliminar el alumno: " + e.getMessage());
        }

        return false;
    }

    /**
     * Convierte una línea del archivo en un objeto Alumno.
     *
     * Formato:
     * 101 - Juan Carlos Pérez
     */
    private Alumno convertirLineaAAlumno(String linea) {

        try {

            String[] partes = linea.split(" - ", 2);

            if (partes.length != 2) {
                return null;
            }

            int id = Integer.parseInt(partes[0].trim());
            String nombre = partes[1].trim();

            return new Alumno(id, nombre);

        } catch (NumberFormatException e) {
            return null;
        }
    }
}
