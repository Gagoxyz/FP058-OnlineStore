package org.javaenjoyers.controlador;


import org.javaenjoyers.RepositorioGenerico;
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
        return pedidos.obtener(String.valueOf(numPedido));
    }

    public void eliminarPedido(int numPedido) {
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
