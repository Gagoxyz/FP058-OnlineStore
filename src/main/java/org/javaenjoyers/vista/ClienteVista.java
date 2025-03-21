package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.ClienteControlador;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;
import org.javaenjoyers.modelo.Premium;

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
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> mostrarClientes();
                case 3 -> System.out.println(" Volviendo al menú principal...");
                default -> System.out.println(" Opción no válida.");
            }
        } while (opcion != 3);
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
        scanner.nextLine();

        Cliente cliente = esPremium ? new Premium(email, nombre, domicilio, nif)
                : new Estandar(email, nombre, domicilio, nif);
        clienteControlador.agregarCliente(email, cliente);
    }

    private void mostrarClientes() {
        System.out.println("\n Lista de Clientes:");
        clienteControlador.mostrarClientes();
    }

}
