package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.ClienteControlador;

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
            System.out.println("\n Gestión de Clientes");
            System.out.println("1. Añadir Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Mostrar Clientes Estándar");
            System.out.println("4. Mostrar Clientes Premium");
            System.out.println("5. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> clienteControlador.mostrarClientes();
                case 3 -> clienteControlador.mostrarClientesPorTipo(false);
                case 4 -> clienteControlador.mostrarClientesPorTipo(true);
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void agregarCliente() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("¿Es Premium? (true/false): ");
        boolean esPremium = scanner.nextBoolean();
        scanner.nextLine(); // Limpiar buffer

        clienteControlador.agregarCliente(email, nombre, domicilio, nif, esPremium);
    }

}
