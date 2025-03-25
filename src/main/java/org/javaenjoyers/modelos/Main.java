package org.javaenjoyers.modelos;

import org.javaenjoyers.controladores.ArticuloControlador;
import org.javaenjoyers.controladores.ClienteControlador;
import org.javaenjoyers.controladores.PedidoControlador;
import org.javaenjoyers.vistas.ArticuloVista;
import org.javaenjoyers.vistas.ClienteVista;
import org.javaenjoyers.vistas.PedidoVista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("\n");

        // prueba de 2 objetos tipo cliente
//        Cliente cli01 = new Estandar("manuela@estandar.com", "Manuela", "c/cliente estandar S/N", "123456789");
//        Cliente cli02 = new Premium("david@premium.com", "David", "c/cliente estandar S/N", "87654321X");

        // pruebas print para llamar a los objetos creados
//        System.out.println(cli01.toString());
//        System.out.println("\n\n");
//        System.out.println(cli02.toString());
//        System.out.println("\n\n");

        // prueba llamar constante CUOTA
//        System.out.println(Premium.CUOTA);


        Scanner scanner = new Scanner(System.in);

        // Instanciar Controladores (Gestión de la lógica)
        ArticuloControlador articuloControlador = new ArticuloControlador();
        ClienteControlador clienteControlador = new ClienteControlador();
        PedidoControlador pedidoControlador = new PedidoControlador();

        // Instanciar Vistas (Interacción con el usuario)
        ArticuloVista articuloVista = new ArticuloVista(articuloControlador);
        ClienteVista clienteVista = new ClienteVista(clienteControlador);
        PedidoVista pedidoVista = new PedidoVista(pedidoControlador, clienteControlador, articuloControlador);

        int opcion;
        do {
            System.out.println("\nMENÚ PRINCIPAL - ONLINE STORE");
            System.out.println("1. Gestión de Artículos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Ingresa un número.");
                scanner.next(); // Descarta la entrada inválida
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> articuloVista.mostrarMenu();
                case 2 -> clienteVista.mostrarMenu();
                case 3 -> pedidoVista.mostrarMenu();
                case 4 -> System.out.println("Cerrando aplicación. ¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);

        scanner.close(); // Cerrar scanner
    }

}