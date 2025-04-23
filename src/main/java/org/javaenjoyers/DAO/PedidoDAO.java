package org.javaenjoyers.DAO;

import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Pedido;
import org.javaenjoyers.modelo.Cliente;

public interface PedidoDAO {
    void insertarPedido(Pedido pedido, boolean clienteNuevo);
    Cliente buscarCliente(String email);
    Articulo buscarArticulo(String codigo);
    Pedido buscarPedido(int numPedido);
    boolean estadoPedido (int numero);
    void eliminarPedido(Pedido pedido);
    void mostrarPedidos(boolean pendiente, Cliente cliente);
}
