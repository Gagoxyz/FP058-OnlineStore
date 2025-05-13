package org.javaenjoyers.DAO;

import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Pedido;
import org.javaenjoyers.modelo.Cliente;

import java.util.List;

public interface PedidoDAO {
    void insertarPedido(Pedido pedido, boolean clienteNuevo);
    Cliente buscarCliente(String email);
    Articulo buscarArticulo(String codigo);
    Pedido buscarPedido(int numPedido);
    boolean estadoPedido (int numero);
    boolean eliminarPedido(Pedido pedido);
    List<Pedido> mostrarPedidos(boolean pendiente, Cliente cliente);
}
