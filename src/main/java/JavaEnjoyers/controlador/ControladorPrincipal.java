package JavaEnjoyers.controlador;

public class ControladorPrincipal {
}
package JavaEnjoyers.controlador;

import JavaEnjoyers.vista.ArticuloVista;
import JavaEnjoyers.vista.ClienteVista;
import JavaEnjoyers.vista.PedidoVista;

import java.util.Scanner;

public class ControladorPrincipal {
    private ClienteVista clienteVista;
    private PedidoVista pedidoVista;
    private ArticuloVista articuloVista;
    private Scanner scanner;

    public ControladorPrincipal(ClienteVista clienteVista, PedidoVista pedidoVista, ArticuloVista articuloVista) {
        this.clienteVista = clienteVista;
        this.pedidoVista = pedidoVista;
        this.articuloVista = articuloVista;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\nMenú Principal");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Pedidos");
            System.out.println("3. Gestión de Artículos");
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
                    clienteVista.mostrarMenu();
                    break;
                case 2:
                    pedidoVista.mostrarMenu();
                    break;
                case 3:
                    articuloVista.mostrarMenu();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}