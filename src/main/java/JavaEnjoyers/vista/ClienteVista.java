package JavaEnjoyers.vista;

import JavaEnjoyers.controlador.ClienteControlador;
import JavaEnjoyers.modelo.ClienteEstandar;
import JavaEnjoyers.modelo.ClientePremium;

import java.util.Scanner;

public class ClienteVista {
    private ClienteControlador clienteControlador;
    private Scanner scanner;

    public ClienteVista(ClienteControlador clienteControlador) {
        this.clienteControlador = clienteControlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\nMenú de Gestión de Clientes");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    eliminarCliente();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private void agregarCliente() {
        System.out.print("Nombre del Cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Email del Cliente: ");
        String email = scanner.nextLine();

        System.out.print("DNI del Cliente: ");
        String dni = scanner.nextLine();

        System.out.print("Dirección del Cliente: ");
        String direccion = scanner.nextLine();

        int tipoCliente;
        do {
            System.out.print("Tipo de Cliente (1 - Estandar, 2 - Premium): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese un valor numérico (1 o 2).");
                scanner.next();
            }
            tipoCliente = scanner.nextInt();
            scanner.nextLine();
        } while (tipoCliente != 1 && tipoCliente != 2);

        if (tipoCliente == 1) {
            clienteControlador.agregarCliente(new ClienteEstandar(nombre, email, dni, direccion));
            System.out.println("Cliente estándar agregado correctamente.");
        } else {
            clienteControlador.agregarCliente(new ClientePremium(nombre, email, dni, direccion));
            System.out.println("Cliente premium agregado correctamente.");
        }
    }

    private void eliminarCliente() {
        System.out.print("Ingrese el email del cliente a eliminar: ");
        String email = scanner.nextLine();

        if (clienteControlador.eliminarCliente(email)) {
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("No se encontró un cliente con ese email.");
        }
    }

    private void listarClientes() {
        clienteControlador.listarClientes();
    }
}