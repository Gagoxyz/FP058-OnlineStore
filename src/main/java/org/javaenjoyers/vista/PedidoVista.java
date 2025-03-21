package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.ArticuloControlador;
import org.javaenjoyers.controlador.ClienteControlador;
import org.javaenjoyers.controlador.PedidoControlador;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;

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
            System.out.println("\n Gestión de Pedidos");
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar Pedidos Pendientes");
            System.out.println("4. Mostrar Pedidos Enviados");
            System.out.println("5. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarPedido();
                case 2 -> eliminarPedido();
                case 3 -> pedidoControlador.mostrarPedidosPendientes();
                case 4 -> pedidoControlador.mostrarPedidosEnviados();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void agregarPedido() {
        System.out.print("Email del cliente: ");
        Cliente cliente = clienteControlador.buscarCliente(scanner.nextLine());
        System.out.print("Código del artículo: ");
        Articulo articulo = articuloControlador.buscarArticulo(scanner.nextLine());
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        pedidoControlador.agregarPedido(cliente, articulo, cantidad);
    }
    public void eliminarPedido() {
        System.out.print("Ingrese el número de pedido a eliminar: ");
        int numPedido = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        pedidoControlador.eliminarPedido(numPedido);
    }

}
