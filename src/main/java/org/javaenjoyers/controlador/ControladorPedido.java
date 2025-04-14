package org.javaenjoyers.controlador;

import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.VistaPedidos;

public class ControladorPedido {
    private VistaPedidos vistaPedidos;
    private Herramientas herramientas;
    private ControladorCliente contrCliente;
    private PedidoDAO pedDAO;

    public ControladorPedido(ControladorCliente contrCliente, Herramientas herramientas, PedidoDAO pedDAO, VistaPedidos vistaPedidos) {
        this.contrCliente = contrCliente;
        this.herramientas = herramientas;
        this.pedDAO = pedDAO;
        this.vistaPedidos = vistaPedidos;
    }

    public void menuPedidos(){
        boolean seguir = true;
        int opcion;
        while(seguir){
            opcion = vistaPedidos.menuPedidos();
            opcion = herramientas.comprobarOpcion(opcion, 0, 4);
            switch (opcion){
                case 1:
                    addPedido();
                    break;
                case 2:
                    removePedido();
                    break;
                case 3:
                    showPedidos(true);
                    break;
                case 4:
                    showPedidos(false);
                    break;
                case 0:
                    seguir = false;
                    break;
            }
        }
    }

    public void addPedido(){
        int opcionCliente = vistaPedidos.clientePedido();
        int registroCliente = herramientas.comprobarOpcion(opcionCliente, 1, 2);
        Pedido pedido;
        Cliente cliente = null;
        switch (registroCliente){
            case 1:
                String email = herramientas.pedirString("Indica el email del cliente: ");
                cliente = pedDAO.buscarCliente(email);
                break;
            case 2:
                cliente = contrCliente.addCliente();
                break;
        }
        if(cliente != null){
            String codigoArticulo = herramientas.pedirString("Indica el código del artículo: ");
            Articulo articulo = pedDAO.buscarArticulo(codigoArticulo);
            pedido = vistaPedidos.infoPedido(cliente, articulo);
            pedDAO.insertarPedido(pedido);
            herramientas.enviarMensaje(1, null);
        }
    }

    public void removePedido(){
        int numero = vistaPedidos.pedirNum();
        Pedido pedido = pedDAO.buscarPedido(numero);
        if(pedido != null){
            boolean estado = pedDAO.estadoPedido(numero);
            if(estado){
                pedDAO.eliminarPedido(pedido);
            }else{
                herramientas.enviarMensaje(0, "\nEl pedido ya ha sido enviado, no se puede eliminar.");
            }
        }

    }

    public void showPedidos(boolean pendiente){
        int eleccion = vistaPedidos.mostrarPedidos();
        eleccion = herramientas.comprobarOpcion(eleccion, 1, 2);
        switch (eleccion){
            case 1:
                pedDAO.mostrarPedidos(pendiente, null);
                break;
            case 2:
                String email = herramientas.pedirString("\nIndica el email del cliente: ");
                Cliente cliente = pedDAO.buscarCliente(email);
                pedDAO.mostrarPedidos(pendiente, cliente);
        }
    }
}
