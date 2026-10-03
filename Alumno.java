public class Alumno {

    private int id;
    private String nombre;

    public Alumno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Convierte el objeto al formato requerido:
     * ID_Alumno - NombreAlumno
     */
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
