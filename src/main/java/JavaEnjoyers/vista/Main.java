package JavaEnjoyers.vista;

import JavaEnjoyers.controlador.ArticuloControlador;
import JavaEnjoyers.controlador.ClienteControlador;
import JavaEnjoyers.controlador.PedidoControlador;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creación de los controladores
        ClienteControlador clienteControlador = new ClienteControlador();
        ArticuloControlador articuloControlador = new ArticuloControlador();

        // Aquí pasas los controladores necesarios al crear el PedidoControlador
        PedidoControlador pedidoControlador = new PedidoControlador(clienteControlador, articuloControlador);

        // Creación de las vistas, pasando los controladores necesarios
        ClienteVista clienteVista = new ClienteVista(clienteControlador);
        PedidoVista pedidoVista = new PedidoVista(pedidoControlador, clienteControlador, articuloControlador);
        ArticuloVista articuloVista = new ArticuloVista(articuloControlador); // Ahora solo pasa el controlador

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú Principal");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Artículos");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.next();
            }
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    clienteVista.mostrarMenu();
                    break;
                case 2:
                    articuloVista.mostrarMenu();
                    break;
                case 3:
                    pedidoVista.mostrarMenu();
                    break;
                case 4:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
