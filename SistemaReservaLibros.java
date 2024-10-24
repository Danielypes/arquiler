
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jyepes
 */
class NodoReserva {
    int idReserva;
    String nombreCliente;
    String tituloLibro;
    String fecha;
    NodoReserva siguiente;

    public NodoReserva(int idReserva, String nombreCliente, String tituloLibro, String fecha) {
        this.idReserva = idReserva;
        this.nombreCliente = nombreCliente;
        this.tituloLibro = tituloLibro;
        this.fecha = fecha;
        this.siguiente = null;
    }
}

class ListaReservas {
    private NodoReserva cabeza;

    public ListaReservas() {
        this.cabeza = null;
    }

    // agregar una nueva reserva
    public void agregarReserva(int idReserva, String nombreCliente, String tituloLibro, String fecha) {
        NodoReserva nuevaReserva = new NodoReserva(idReserva, nombreCliente, tituloLibro, fecha);

        if (cabeza == null) {
            cabeza = nuevaReserva;
        } else {
            NodoReserva actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaReserva;
        }
        System.out.println("Reserva " + idReserva + " creada exitosamente.");
    }

    // listar todas las reservas
    public void listarReservas() {
        if (cabeza == null) {
            System.out.println("no hay reservas registradas.");
            return;
        }

        NodoReserva actual = cabeza;
        System.out.println("listado de Reservas:");
        while (actual != null) {
            System.out.println("ID: " + actual.idReserva + ", cliente: " + actual.nombreCliente + 
                               ", Libro: " + actual.tituloLibro + ", Fecha: " + actual.fecha);
            actual = actual.siguiente;
        }
    }

    // cancelar una reserva por ID
    public void cancelarReserva(int idReserva) {
        NodoReserva actual = cabeza;
        NodoReserva anterior = null;

        while (actual != null && actual.idReserva != idReserva) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual == null) {
            System.out.println("no se encontro ninguna reserva con ID " + idReserva);
            return;
        }

        if (anterior == null) {
            cabeza = actual.siguiente; 
        } else {
            anterior.siguiente = actual.siguiente; 
        }

        System.out.println("Reserva " + idReserva + " cancelada exitosamente");
    }
}

public class SistemaReservaLibros {
    private static final Scanner scanner = new Scanner(System.in);

    // comprobar entrada de enteros
    public static int validarEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("entrada invalida. Debe ser un numero entero.");
            }
        }
    }

    public static void main(String[] args) {
        ListaReservas listaReservas = new ListaReservas();

        while (true) {
            System.out.println("\n=== menu del sistema de reserva de libros ===");
            System.out.println("1. Realizar Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    int idReserva = validarEntero("ingrese el ID de la reserva: ");
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Ingrese el título del libro: ");
                    String tituloLibro = scanner.nextLine();
                    System.out.print("Ingrese la fecha (DD/MM/AAAA): ");
                    String fecha = scanner.nextLine();
                    listaReservas.agregarReserva(idReserva, nombreCliente, tituloLibro, fecha);
                }

                case "2" -> listaReservas.listarReservas();

                case "3" -> {
                    int idCancelar = validarEntero("ingrese el ID de la reserva a cancelar: ");
                    listaReservas.cancelarReserva(idCancelar);
                }

                case "4" -> {
                    try (scanner) {
                        System.out.println("saliendo del sistema...");
                    }
                    return;
                }


                default -> System.out.println("opcion invalida... Intente de nuevo");
            }
        }
    }
}