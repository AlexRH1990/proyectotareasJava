
import java.time.LocalDate;

public class Tarea {
    private static int contadorTareas = 0;
    private String consecutivo;
    private String nombre;
    private String estado = "Pendiente"; // Estado predeterminado
    private LocalDate fechaExpiracion;
    private Encargado encargado; // Nuevo atributo Encargado

    public Tarea(String nombre, LocalDate fechaExpiracion, Encargado encargado) {
        this.consecutivo = generarConsecutivo();
        this.nombre = nombre;
        this.fechaExpiracion = fechaExpiracion;
        this.encargado = encargado;
    }

    private String generarConsecutivo() {
        String codigo = "T" + String.format("%03d", contadorTareas);
        contadorTareas++;
        return codigo;
    }

    // Getters y setters para acceder y modificar los atributos de la clase

    // Métodos accesores para obtener los atributos del objeto tarea
    public String getConsecutivo() {
        return consecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Encargado getEncargado() {
        return encargado;
    }
}
