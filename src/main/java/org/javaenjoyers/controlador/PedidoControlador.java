package org.javaenjoyers.controlador;


import org.javaenjoyers.RepositorioGenerico;
import org.javaenjoyers.excepciones.ElementoNoEncontradoException;
import org.javaenjoyers.excepciones.PedidoNoEliminableException;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoControlador {
    private RepositorioGenerico<Pedido> pedidos;

    public PedidoControlador() {
        this.pedidos = new RepositorioGenerico<>();
    }

    public void agregarPedido(int numPedido, Pedido pedido) {
        pedidos.agregar(String.valueOf(numPedido), pedido);
    }

    public Pedido obtenerPedido(int numPedido) {
        Pedido pedido = pedidos.obtener(String.valueOf(numPedido));
        if (pedido == null) {
            throw new ElementoNoEncontradoException("Pedido con número " + numPedido + " no encontrado.");
        }
        return pedido;
    }

    public void eliminarPedido(int numPedido) {
        Pedido pedido = pedidos.obtener(String.valueOf(numPedido));
        if (pedido == null) {
            throw new ElementoNoEncontradoException("No se puede eliminar. Pedido con número " + numPedido + " no encontrado.");
        }

        LocalDateTime tiempoLimite = pedido.getFechaHoraPedido().plusMinutes(pedido.getArticulo().getTiempoPrepEnvio());
        if (LocalDateTime.now().isAfter(tiempoLimite)) {
            throw new PedidoNoEliminableException("No se puede eliminar. El pedido ya fue enviado.");
        }

        pedidos.eliminar(String.valueOf(numPedido));
    }

    public void mostrarPedidos() {
        if (pedidos.contarElementos() == 0) {
            System.out.println("No hay pedidos registrados.");
        } else {
            pedidos.obtenerTodos().values().forEach(System.out::println);
        }
    }

    // Nuevo método para obtener el total de pedidos
    public int contarPedidos() {
        return pedidos.contarElementos();
    }


    public List<Pedido> obtenerPedidosPendientesPorCliente(String emailCliente) {
        return pedidos.obtenerTodos().values().stream()
                .filter(pedido -> pedido.getCliente().getEmail().equals(emailCliente))
                .filter(pedido -> LocalDateTime.now().isBefore(
                        pedido.getFechaHoraPedido().plusMinutes(pedido.getArticulo().getTiempoPrepEnvio())))
                .collect(Collectors.toList());
    }

    public List<Pedido> obtenerPedidosEnviadosPorCliente(String emailCliente) {
        return pedidos.obtenerTodos().values().stream()
                .filter(pedido -> pedido.getCliente().getEmail().equals(emailCliente))
                .filter(pedido -> LocalDateTime.now().isAfter(
                        pedido.getFechaHoraPedido().plusMinutes(pedido.getArticulo().getTiempoPrepEnvio())))
                .collect(Collectors.toList());
    }

}
