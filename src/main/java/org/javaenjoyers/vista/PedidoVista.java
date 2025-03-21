package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.ArticuloControlador;
import org.javaenjoyers.controlador.ClienteControlador;
import org.javaenjoyers.controlador.PedidoControlador;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.LocalDateTime;
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
            System.out.println("2. Mostrar Pedidos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarPedido();
                case 2 -> mostrarPedidosPendientes();
                case 3 -> System.out.println(" Volviendo al menú principal...");
                default -> System.out.println(" Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void agregarPedido() {
        System.out.print("Email del cliente: ");
        Cliente cliente = clienteControlador.obtenerCliente(scanner.nextLine());
        if (cliente == null) {
            System.out.println("Error: Cliente no encontrado.");
            return;
        }

        System.out.print("Código del artículo: ");
        Articulo articulo = articuloControlador.obtenerArticulo(scanner.nextLine());
        if (articulo == null) {
            System.out.println(" Error: Artículo no encontrado.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        int nuevoNumeroPedido = pedidoControlador.contarPedidos() + 1;
        pedidoControlador.agregarPedido(nuevoNumeroPedido, new Pedido(nuevoNumeroPedido, cliente, articulo, cantidad, LocalDateTime.now()));

        System.out.println("Pedido agregado con éxito.");
    }

    private void mostrarPedidosPendientes() {
        System.out.println("\n Pedidos Pendientes:");
        pedidoControlador.mostrarPedidos();
    }

}
