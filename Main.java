import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Repository encargado de manejar alumnos.txt
        AlumnoRepository repository =
                new AlumnoRepository("alumnos.txt");

        int opcion;

        do {

            mostrarMenu();

            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                opcion = 0;
                System.out.println("Error: debe ingresar un numero.");
            }

            switch (opcion) {

                case 1:
                    registrarAlumno(scanner, repository);
                    break;

                case 2:
                    mostrarTodos(repository);
                    break;

                case 3:
                    buscarAlumno(scanner, repository);
                    break;

                case 4:
                    actualizarAlumno(scanner, repository);
                    break;

                case 5:
                    eliminarAlumno(scanner, repository);
                    break;

                case 6:
                    System.out.println("\nPrograma finalizado.");
                    break;

                default:
                    System.out.println("\nOpcion no valida.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    /**
     * Muestra el menú principal.
     */
    private static void mostrarMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       MENU GESTION DE ALUMNOS");
        System.out.println("             JAVA NIO");
        System.out.println("==============================================");
        System.out.println("1. Registrar nuevo alumno");
        System.out.println("2. Ver todos los alumnos");
        System.out.println("3. Buscar alumno por ID");
        System.out.println("4. Actualizar nombre de alumno");
        System.out.println("5. Eliminar alumno");
        System.out.println("6. Salir");
        System.out.println("==============================================");
    }

    /**
     * Registra un nuevo alumno.
     */
    private static void registrarAlumno(
            Scanner scanner,
            AlumnoRepository repository) {

        System.out.println("\n--- Registrar Alumno ---");

        int id;

        try {

            System.out.print("Ingrese ID: ");
            id = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Error: el ID debe ser un numero.");
            return;
        }

        // Validar que el ID sea positivo
        if (id <= 0) {
            System.out.println("Error: el ID debe ser mayor que cero.");
            return;
        }

        // Verificar duplicado
        if (repository.buscarPorId(id) != null) {
            System.out.println(
                    "Error: ya existe un alumno con el ID " + id + ".");
            return;
        }

        System.out.print("Ingrese Nombre Completo: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacio.");
            return;
        }

        Alumno alumno = new Alumno(id, nombre);

        if (repository.agregarAlumno(alumno)) {
            System.out.println("¡Alumno registrado con exito!");
        } else {
            System.out.println("No fue posible registrar el alumno.");
        }
    }

    /**
     * Muestra todos los alumnos.
     */
    private static void mostrarTodos(AlumnoRepository repository) {

        System.out.println("\n--- Lista de Alumnos ---");

        List<Alumno> alumnos = repository.obtenerTodos();

        if (alumnos.isEmpty()) {

            System.out.println(
                    "No hay alumnos registrados o el archivo está vacio.");

            return;
        }

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    /**
     * Busca un alumno por ID.
     */
    private static void buscarAlumno(
            Scanner scanner,
            AlumnoRepository repository) {

        System.out.println("\n--- Buscar Alumno ---");

        int id;

        try {

            System.out.print("Ingrese ID: ");
            id = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Error: el ID debe ser un numero.");
            return;
        }

        Alumno alumno = repository.buscarPorId(id);

        if (alumno != null) {

            System.out.println("\nAlumno encontrado:");
            System.out.println("ID: " + alumno.getId());
            System.out.println("Nombre: " + alumno.getNombre());

        } else {

            System.out.println(
                    "No existe un alumno con el ID " + id + ".");
        }
    }

    /**
     * Actualiza el nombre de un alumno.
     */
    private static void actualizarAlumno(
            Scanner scanner,
            AlumnoRepository repository) {

        System.out.println("\n--- Actualizar Alumno ---");

        int id;

        try {

            System.out.print("Ingrese ID del alumno a modificar: ");
            id = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Error: el ID debe ser un número.");
            return;
        }

        Alumno alumno = repository.buscarPorId(id);

        if (alumno == null) {

            System.out.println(
                    "Error: no existe un alumno con el ID " + id + ".");

            return;
        }

        System.out.println("Alumno actual: " + alumno.getNombre());

        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = scanner.nextLine().trim();

        if (nuevoNombre.isEmpty()) {

            System.out.println(
                    "Error: el nombre no puede estar vacío.");

            return;
        }

        if (repository.actualizarAlumno(id, nuevoNombre)) {

            System.out.println(
                    "¡Alumno actualizado correctamente!");

        } else {

            System.out.println(
                    "No fue posible actualizar el alumno.");
        }
    }

    /**
     * Elimina un alumno.
     */
    private static void eliminarAlumno(
            Scanner scanner,
            AlumnoRepository repository) {

        System.out.println("\n--- Eliminar Alumno ---");

        int id;

        try {

            System.out.print("Ingrese ID del alumno a eliminar: ");
            id = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Error: el ID debe ser un número.");
            return;
        }

        Alumno alumno = repository.buscarPorId(id);

        if (alumno == null) {

            System.out.println(
                    "Error: no existe un alumno con el ID " + id + ".");

            return;
        }

        System.out.println(
                "Alumno encontrado: " + alumno.getNombre());

        System.out.print(
                "¿Está seguro de eliminarlo? (S/N): ");

        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {

            if (repository.eliminarAlumno(id)) {

                System.out.println(
                        "¡Alumno eliminado correctamente!");

            } else {

                System.out.println(
                        "No fue posible eliminar el alumno.");
            }

        } else {

            System.out.println("Operación cancelada.");
        }
    }
}
