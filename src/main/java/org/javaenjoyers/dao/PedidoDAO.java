package org.javaenjoyers.dao;

import org.javaenjoyers.modelos.Cliente;
import org.javaenjoyers.modelos.Pedido;
import java.util.List;

public interface PedidoDAO extends DAO<Pedido, Integer> {

    Pedido obtenerPedidoPorNumPedido(int numPedido);
    List<Pedido> obtenerPedidosPorCliente(String email);
    List<Pedido> obtenerPedidosPendientesEnvio(Cliente cliente);
    List<Pedido> obtenerPedidosEnviados(Cliente cliente);
}
