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
            throw new ElementoNoEncontradoException("⚠️ Pedido con número " + numPedido + " no encontrado.");
        }
        return pedido;
    }

    public void eliminarPedido(int numPedido) {
        Pedido pedido = pedidos.obtener(String.valueOf(numPedido));
        if (pedido == null) {
            throw new ElementoNoEncontradoException("⚠️ No se puede eliminar. Pedido con número " + numPedido + " no encontrado.");
        }

        LocalDateTime tiempoLimite = pedido.getFechaHoraPedido().plusMinutes(pedido.getArticulo().getTiempoPrepEnvio());
        if (LocalDateTime.now().isAfter(tiempoLimite)) {
            throw new PedidoNoEliminableException("⚠️ No se puede eliminar. El pedido ya fue enviado.");
        }

        pedidos.eliminar(String.valueOf(numPedido));
    }

    public void mostrarPedidos() {
        pedidos.mostrarTodos();
    }

    // Nuevo método para obtener el total de pedidos
    public int contarPedidos() {
        return pedidos.contarElementos();
    }


}
