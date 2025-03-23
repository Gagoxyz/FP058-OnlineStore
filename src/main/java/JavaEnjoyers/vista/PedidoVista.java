package JavaEnjoyers.vista;

import JavaEnjoyers.controlador.ClienteControlador;
import JavaEnjoyers.controlador.PedidoControlador;
import JavaEnjoyers.controlador.ArticuloControlador;
import JavaEnjoyers.modelo.Cliente;
import JavaEnjoyers.modelo.Articulo;
import java.util.Scanner;

public class PedidoVista {
    private PedidoControlador pedidoControlador;
    private ClienteControlador clienteControlador;
    private ArticuloControlador articuloControlador;
    private Scanner scanner;

    public PedidoVista(PedidoControlador pedidoControlador, ClienteControlador clienteControlador, ArticuloControlador articuloControlador) {
        this.pedidoControlador = pedidoControlador;
        this.clienteControlador = clienteControlador;
        this.articuloControlador = articuloControlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\nMenú de Gestión de Pedidos");
            System.out.println("1. Agregar Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Listar Pedidos");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarPedido();
                    break;
                case 2:
                    eliminarPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private void agregarPedido() {
        System.out.print("Ingrese el email del cliente: ");
        String emailCliente = scanner.nextLine();


        Cliente cliente = clienteControlador.obtenerClientePorEmail(emailCliente);

        if (cliente != null) {
            System.out.print("Código del Artículo: ");
            String codigoArticulo = scanner.nextLine();

            System.out.print("Cantidad: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese una cantidad válida.");
                scanner.next();
            }
            int cantidad = scanner.nextInt();
            scanner.nextLine();


            if (pedidoControlador.agregarPedido(1234, emailCliente, codigoArticulo, cantidad)) {
                System.out.println("Pedido agregado correctamente.");
            } else {
                System.out.println("Hubo un error al agregar el pedido.");
            }
        } else {
            System.out.println("No se encontró un cliente con ese email.");
        }
    }

    private void eliminarPedido() {
        System.out.print("Ingrese el número de pedido a eliminar: ");
        int numeroPedido = scanner.nextInt();
        scanner.nextLine();

        if (pedidoControlador.eliminarPedido(numeroPedido)) {
            System.out.println("Pedido eliminado correctamente.");
        } else {
            System.out.println("No se encontró un pedido con ese número.");
        }
    }

    private void listarPedidos() {
        // Muestra los pedidos actuales
        for (String pedido : pedidoControlador.obtenerPedidos()) {
            System.out.println(pedido);
        }
    }
}