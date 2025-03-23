package JavaEnjoyers.controlador;

import JavaEnjoyers.modelo.Pedido;
import JavaEnjoyers.modelo.Cliente;
import JavaEnjoyers.modelo.Articulo;
import java.util.ArrayList;
import java.util.List;

public class PedidoControlador {
    private List<Pedido> pedidos;
    private ClienteControlador clienteControlador;
    private ArticuloControlador articuloControlador;

    public PedidoControlador(ClienteControlador clienteControlador, ArticuloControlador articuloControlador) {
        this.pedidos = new ArrayList<>();
        this.clienteControlador = clienteControlador;
        this.articuloControlador = articuloControlador;
    }

    public boolean agregarPedido(int numeroPedido, String emailCliente, String codigoArticulo, int cantidad) {

        Cliente cliente = clienteControlador.obtenerClientePorEmail(emailCliente); // Cambio aquí
        Articulo articulo = articuloControlador.obtenerArticuloPorCodigo(codigoArticulo);

        if (cliente != null && articulo != null) {
            Pedido nuevoPedido = new Pedido(numeroPedido, cliente, articulo, cantidad);
            pedidos.add(nuevoPedido);
            return true;
        }
        return false;
    }

    public boolean eliminarPedido(int numeroPedido) {
        return pedidos.removeIf(pedido -> pedido.getNumeroPedido() == numeroPedido);
    }

    public List<String> obtenerPedidos() {
        List<String> listaPedidos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            listaPedidos.add(pedido.toString());
        }
        return listaPedidos;
    }
}