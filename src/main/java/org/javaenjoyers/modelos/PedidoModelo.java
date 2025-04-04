package org.javaenjoyers.modelos;

import org.javaenjoyers.dao.ClienteDAO;
import org.javaenjoyers.dao.FactoryDAO;
import org.javaenjoyers.dao.PedidoDAO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PedidoModelo {

    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;

    public PedidoModelo(){
        pedidoDAO = FactoryDAO.getPedidoDAO();
        clienteDAO = FactoryDAO.getClienteDAO();
    }

    public List<Pedido> obtenerPedidos(){
        return pedidoDAO.obtenerDatos();
    }

    public void agregarPedido(Pedido pedido){
        Cliente cliente = pedido.getCliente();

        if(cliente != null){
            pedidoDAO.insertar(pedido);
        }
    }

    public Cliente obtenerClientePorEmail(String email){
        return clienteDAO.obtener(email);
    }

    public Pedido obtenerPedidoPorNumPedido(int numPedido){
        return pedidoDAO.obtenerPedidoPorNumPedido(numPedido);
    }

    public void eliminarPedido(Pedido pedido){
        pedidoDAO.eliminar(pedido);
    }

    public List<Pedido> obtenerPedidosPorCliente(Cliente cliente){
        return pedidoDAO.obtenerPedidosPorCliente(cliente.getEmail());
    }

    public List<Pedido> obtenerPedidosPendientesEnvio(Cliente cliente){
        return pedidoDAO.obtenerPedidosPendientesEnvio(cliente);
    }

    public List<Pedido> obtenerPedidosEnviados(Cliente cliente){
        return pedidoDAO.obtenerPedidosEnviados(cliente);
    }

    public boolean verificarTiempoPedido(Pedido pedido){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        long tiempoTranscurrido = pedido.getFechaHoraPedido().until(fechaHoraActual, ChronoUnit.MINUTES);
        return tiempoTranscurrido > pedido.getArticulo().getTiempoPrepEnvio();
    }
}
