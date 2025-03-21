package org.javaenjoyers.controlador;


import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PedidoControlador {
    private List<Pedido> pedidos;
    private int contadorPedidos;

    public PedidoControlador() {
        this.pedidos = new ArrayList<>();
        this.contadorPedidos = 1; // Autoincrementable
    }

    public void agregarPedido(Cliente cliente, Articulo articulo, int cantidad) {
        if (cliente == null) {
            System.out.println("Error: Cliente no encontrado.");
            return;
        }
        if (articulo == null) {
            System.out.println("Error: Artículo no encontrado.");
            return;
        }

        Pedido pedido = new Pedido(contadorPedidos++, cliente, articulo, cantidad, LocalDateTime.now());
        pedidos.add(pedido);
        System.out.println("Pedido agregado correctamente.");
        System.out.println("Tiempo de preparación estimado: " + articulo.getTiempoPrepEnvio() + " minutos.");
    }

    public void eliminarPedido(int numeroPedido) {
        Pedido pedido = pedidos.stream()
                .filter(p -> p.getNumPedido() == numeroPedido)
                .findFirst()
                .orElse(null);

        if (pedido == null) {
            System.out.println("Error: No se encontró el pedido.");
            return;
        }

        LocalDateTime tiempoEnvio = pedido.getFechaHoraPedido().plusMinutes(pedido.getArticulo().getTiempoPrepEnvio());
        long minutosRestantes = ChronoUnit.MINUTES.between(LocalDateTime.now(), tiempoEnvio);

        if (minutosRestantes <= 0) {
            System.out.println("No se puede eliminar el pedido porque ya ha sido enviado.");
            return;
        }

        pedidos.remove(pedido);
        System.out.println("Pedido eliminado correctamente.");
        System.out.println("Tiempo restante antes del envío: " + minutosRestantes + " minutos.");
    }

    public void mostrarPedidosPendientes() {
        System.out.println("📋 Pedidos Pendientes de Envío:");
        pedidos.stream()
                .filter(p -> ChronoUnit.MINUTES.between(LocalDateTime.now(),
                        p.getFechaHoraPedido().plusMinutes(p.getArticulo().getTiempoPrepEnvio())) > 0)
                .forEach(pedido -> {
                    long minutosRestantes = ChronoUnit.MINUTES.between(LocalDateTime.now(),
                            pedido.getFechaHoraPedido().plusMinutes(pedido.getArticulo().getTiempoPrepEnvio()));
                    System.out.println(pedido);
                    System.out.println("⏳ Tiempo restante para envío: " + minutosRestantes + " minutos.");
                });
    }

    public void mostrarPedidosEnviados() {
        System.out.println("Pedidos Enviados:");
        pedidos.stream()
                .filter(p -> ChronoUnit.MINUTES.between(LocalDateTime.now(),
                        p.getFechaHoraPedido().plusMinutes(p.getArticulo().getTiempoPrepEnvio())) <= 0)
                .forEach(System.out::println);
    }


}
