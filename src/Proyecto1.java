import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Proyecto1 {

    // Lista para almacenar los consecutivos eliminados
    static ArrayList<String> consecutivosEliminados = new ArrayList<>();

    public static void printMenu(String[] options) {// método para imprimir el menú ciclicamente
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static String obtenerPalabraValida(Scanner scanner) { //método de Validación de nombre y apellido
        String palabra = "";
        boolean esValida = false;

        while (!esValida) {
            try {
                System.out.print("Ingrese un nombre (más de 3 letras y solo letras): ");
                palabra = scanner.nextLine();

                if (palabra.length() < 3) {
                    throw new IllegalArgumentException("El nombre debe tener al menos 3 letras.");
                }

                // Expresión regular que acepta letras mayúsculas y minúsculas, así como letras con acentos y otros caracteres especiales
                if (!palabra.matches("[\\p{L}]+")) {
                    throw new IllegalArgumentException("El nombre debe contener solo letras.");
                }

                esValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return palabra;
    }

    public static String obtenerOpcionValida(Scanner scanner) { //validación de opciones en el menú
        String opcion = "";
        boolean esValida = false;

        while (!esValida) {
            try {
                System.out.print("Elige una opción : ");
                opcion = scanner.nextLine();

                if (!opcion.matches("[1-5]")) {
                    throw new IllegalArgumentException("Por favor, ingrese una opción válida (1-5).");
                }

                esValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return opcion;
    }

    // Método para generar un nuevo consecutivo único
    public static String generarNuevoConsecutivo() {
        String codigo = "T" + String.format("%03d", consecutivosEliminados.size() + 1);
        return codigo;
    }

    public static ArrayList<Tarea> filtrarTareas(ArrayList<Tarea> tareas, String estado, LocalDate fechaVencimiento) {
        ArrayList<Tarea> tareasFiltradas = new ArrayList<>(); // método para filtrar tareas por Estado o Fecha de vencimiento

        for (Tarea tarea : tareas) {
            // Verificar si la tarea coincide con los criterios de filtrado
            if ((estado == null || tarea.getEstado().equalsIgnoreCase(estado)) &&
                    (fechaVencimiento == null || tarea.getFechaExpiracion().equals(fechaVencimiento))) {
                tareasFiltradas.add(tarea);
            }
        }

        return tareasFiltradas;
    }

    public static LocalDate validarFechaExpiracion(Scanner scanner) {//validación de fechas
        LocalDate fechaExpiracion = null;
        boolean formatoValido = false;

        while (!formatoValido) {
            try {
                System.out.print("Ingrese la fecha de expiración (AAAA-MM-DD): ");
                String fechaExpiracionStr = scanner.nextLine();
                fechaExpiracion = LocalDate.parse(fechaExpiracionStr);
                formatoValido = true; // Si la conversión se realiza correctamente, el formato es válido
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, ingrese una fecha en el formato AAAA-MM-DD.");
            }
        }

        return fechaExpiracion;
    }

    public static void main(String[] args) {// se inicia main
        ArrayList<Tarea> tareas = new ArrayList<>();
        String[] options = {"1-Agregar Tareas", // Mi manera de presentar las opciones es mediante un menú
                "2- Ver Tareas",
                "3- Actualizar Tareas",
                "4- Borrar Tareas",
                "5- Exit"
        };
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 5) {// bucle para retorno y validación 
            System.out.print("\033[H\033[2J");  // Limpiar la pantalla
            printMenu(options);
            option = Integer.parseInt(obtenerOpcionValida(scanner));
            switch (option) {
                case 1:System.out.print("\033[H\033[2J");
                    // Variable para controlar si el usuario desea agregar otra tarea
                    boolean agregarOtraTarea = true;

                    while (agregarOtraTarea) { // ciclo de iteración para construir el ArrayList tarea con los objetos de la clase Tarea
                        System.out.println("Ingrese los detalles para la nueva tarea:"); //Solicitud de los datos de atributos para llenar los objetos

                        System.out.print("Nombre de la tarea: ");
                        String nombreTarea = scanner.nextLine();

                        // Validar la fecha de expiración
                        LocalDate fechaExpiracion = validarFechaExpiracion(scanner);

                        System.out.println("Ingrese el nombre del encargado:");
                        String nombreEncargado = obtenerPalabraValida(scanner);

                        System.out.println("Ingrese el apellido del encargado:");
                        String apellidoEncargado = obtenerPalabraValida(scanner);

                        Encargado encargado = new Encargado(nombreEncargado, apellidoEncargado);

                        // Asignar un nuevo consecutivo a la tarea en caso de que no tenga
                        String nuevoConsecutivo;
                        if (consecutivosEliminados.isEmpty()) {
                            // Si no hay consecutivos eliminados disponibles, genera uno nuevo
                            nuevoConsecutivo = generarNuevoConsecutivo();
                        } else {
                            // Si hay consecutivos eliminados disponibles, toma el primero de la lista
                            nuevoConsecutivo = consecutivosEliminados.remove(0);
                        }// Esto anterior busca que no dejen de existir los consecutivos de los objetos eliminados

                        Tarea tarea = new Tarea(nombreTarea, fechaExpiracion, encargado);
                        tareas.add(tarea);

                        // Preguntar al usuario si desea agregar otra tarea
                        System.out.print("¿Desea agregar otra tarea? (S/N): ");
                        String respuesta = scanner.nextLine().trim().toUpperCase();
                        agregarOtraTarea = respuesta.equals("S");
                    }
                    break;

                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("\nTareas ingresadas:");

                    // Imprimir las tareas ingresadas
                    for (Tarea tarea : tareas) {
                        System.out.println("Consecutivo: " + tarea.getConsecutivo());
                        System.out.println("Nombre: " + tarea.getNombre());
                        System.out.println("Estado: " + tarea.getEstado());
                        System.out.println("Fecha de expiración: " + tarea.getFechaExpiracion());
                        System.out.println();
                    }

                    // Pausa para permitir al usuario ver las tareas ingresadas
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    System.out.println("¿Desea filtrar las tareas? (S/N): ");
                    String respuestaFiltrar = scanner.nextLine().trim().toUpperCase();

                    if (respuestaFiltrar.equals("S")) {
                    System.out.println("Seleccione cómo desea filtrar las tareas:");
                    System.out.println("1. Filtrar por estado");
                    System.out.println("2. Filtrar por fecha de vencimiento");
                    System.out.print("Ingrese su elección: ");
                    int opcionFiltrar = Integer.parseInt(obtenerOpcionValida(scanner));

                     if (opcionFiltrar == 1) {
                      // Filtrar por estado
                    System.out.print("Ingrese el estado por el cual desea filtrar las tareas: ");
                    String estadoFiltrar = scanner.nextLine();
                     ArrayList<Tarea> tareasFiltradas = filtrarTareas(tareas, estadoFiltrar, null);
            // Imprimir las tareas filtradas
            for (Tarea tarea : tareasFiltradas) {
                System.out.println("Consecutivo: " + tarea.getConsecutivo());
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Estado: " + tarea.getEstado());
                System.out.println("Fecha de expiración: " + tarea.getFechaExpiracion());
                System.out.println();
            }
        } else if (opcionFiltrar == 2) {
            // Filtrar por fecha de vencimiento
            System.out.print("Ingrese la fecha de vencimiento por la cual desea filtrar las tareas (AAAA-MM-DD): ");
            LocalDate fechaVencimientoFiltrar = LocalDate.parse(scanner.nextLine());
            ArrayList<Tarea> tareasFiltradas = filtrarTareas(tareas, null, fechaVencimientoFiltrar);
            // Imprimir las tareas filtradas
            for (Tarea tarea : tareasFiltradas) {
                System.out.println("Consecutivo: " + tarea.getConsecutivo());
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Estado: " + tarea.getEstado());
                System.out.println("Fecha de expiración: " + tarea.getFechaExpiracion());
                System.out.println();
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }
    
                    break;

                case 3:// Se busca por consecutivo, y se actualiza 
                    System.out.print("\033[H\033[2J");// borrar pantalla

                    System.out.println("Ingrese el consecutivo de la tarea que desea actualizar:");
                    String consecutivoActualizar = scanner.nextLine();

                    // Buscar la tarea correspondiente
                    Tarea tareaActualizar = null;
                    for (Tarea tarea : tareas) {
                        if (tarea.getConsecutivo().equals(consecutivoActualizar)) {
                            tareaActualizar = tarea;
                            break;
                        }
                    }

                    // Verificar si se encontró la tarea
                    if (tareaActualizar != null) {
                        // Mostrar el estado actual de la tarea
                        System.out.println("Estado actual de la tarea:");
                        System.out.println("Consecutivo: " + tareaActualizar.getConsecutivo());
                        System.out.println("Nombre: " + tareaActualizar.getNombre());
                        System.out.println("Estado: " + tareaActualizar.getEstado());

                        // Solicitar al usuario que confirme si desea marcar la tarea como "Resuelta"
                        System.out.print("¿Desea marcar esta tarea como Resuelta? (S/N): ");
                        String respuesta = scanner.nextLine().trim().toUpperCase();

                        if (respuesta.equals("S")) {
                            // Actualizar el estado de la tarea a "Resuelta"
                            tareaActualizar.setEstado("Resuelta");
                            System.out.println("La tarea ha sido actualizada correctamente.");
                        } else {
                            System.out.println("La tarea no ha sido actualizada.");
                        }
                    } else {
                        System.out.println("No se encontró ninguna tarea con el consecutivo especificado.");
                    }
                    break;

                case 4: //Se busca por consecutivo, y se elimina                    System.out.println("Ingrese el consecutivo de la tarea que desea eliminar:");
                    // Solicitar al usuario el consecutivo de la tarea que desea eliminar
                    System.out.print("\033[H\033[2J");// borrar pantalla
                    System.out.println("Ingrese el consecutivo de la tarea que desea eliminar:");
                    String consecutivoEliminar = scanner.nextLine();

                    // Variable para verificar si se encontró la tarea y se eliminó con éxito
                    boolean tareaEliminada = false;

                    // Iterar sobre la lista de tareas
                    for (int i = 0; i < tareas.size(); i++) {
                        Tarea tarea = tareas.get(i);

                        // Verificar si el consecutivo de la tarea actual coincide con el ingresado por el usuario
                        if (tarea.getConsecutivo().equals(consecutivoEliminar)) {
                            // Se encontró la tarea, agregar su consecutivo a la lista de eliminados
                            consecutivosEliminados.add(tarea.getConsecutivo());
                            // Eliminar la tarea de la lista
                            tareas.remove(i);
                            tareaEliminada = true;
                            break; // Salir del bucle una vez que se ha eliminado la tarea
                        }
                    }
                    if (tareaEliminada) {
                        System.out.println("La tarea ha sido eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna tarea con el consecutivo especificado.");
                    }
                    break;

                case 5:
                    // Caso 5: Exit
                    System.out.println("Saliendo del programa...");
                    System.exit(0); // Salir del programa con estado de salida 0 (éxito)
                    break;
            }
        }
    }
}
