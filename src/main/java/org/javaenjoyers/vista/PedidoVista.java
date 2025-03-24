package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.ArticuloControlador;
import org.javaenjoyers.controlador.ClienteControlador;
import org.javaenjoyers.controlador.PedidoControlador;
import org.javaenjoyers.excepciones.ElementoNoEncontradoException;
import org.javaenjoyers.excepciones.PedidoNoEliminableException;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
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
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nGestión de Pedidos");
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar TODOS los Pedidos");
            System.out.println("4. Mostrar Pedidos Pendientes por Cliente");
            System.out.println("5. Mostrar Pedidos Enviados por Cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> agregarPedido();
                    case 2 -> eliminarPedido();
                    case 3 -> mostrarTodosLosPedidos();
                    case 4 -> mostrarPedidosPendientesPorCliente();
                    case 5 -> mostrarPedidosEnviadosPorCliente();
                    case 6 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción no válida, intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un número válido.");
                scanner.nextLine(); // Limpiar buffer de entrada para evitar bucle infinito
                opcion = -1; // Para que el bucle no termine prematuramente
            }

        } while (opcion != 6);
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

//    private void mostrarPedidosPendientes() {
//        System.out.println("\n Pedidos Pendientes:");
//        pedidoControlador.mostrarPedidos();
//    }

    private void mostrarTodosLosPedidos() {
        System.out.println("\n📋 Listado de TODOS los pedidos:");
        try {
            pedidoControlador.mostrarPedidos();
        } catch (Exception e) {
            System.out.println("Error al mostrar los pedidos: " + e.getMessage());
        }
    }

    private void mostrarPedidosPendientesPorCliente() {
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();
        List<Pedido> pedidosPendientes = pedidoControlador.obtenerPedidosPendientesPorCliente(email);

        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes para este cliente.");
        } else {
            pedidosPendientes.forEach(System.out::println);
        }
    }

    private void mostrarPedidosEnviadosPorCliente() {
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();
        List<Pedido> pedidosEnviados = pedidoControlador.obtenerPedidosEnviadosPorCliente(email);

        if (pedidosEnviados.isEmpty()) {
            System.out.println("No hay pedidos enviados para este cliente.");
        } else {
            pedidosEnviados.forEach(System.out::println);
        }
    }
    private void eliminarPedido() {
        System.out.print("Ingrese el número del pedido a eliminar: ");
        int numPedido = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            pedidoControlador.eliminarPedido(numPedido);
            System.out.println("✅ Pedido eliminado con éxito.");
        } catch (ElementoNoEncontradoException | PedidoNoEliminableException e) {
            System.out.println(e.getMessage());
        }
    }


}
