package org.javaenjoyers.controlador;

import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.VistaClientes;
import org.javaenjoyers.vista.VistaPedidos;

public class ControladorPedido {
    private VistaPedidos vistaPedidos;
    private Herramientas herramientas;
    private PedidoDAO pedDAO;
    private VistaClientes vistaClientes;
    private ClienteDAO cliDAO;

    public ControladorPedido(ClienteDAO cliDAO, Herramientas herramientas, PedidoDAO pedDAO, VistaClientes vistaClientes, VistaPedidos vistaPedidos) {
        this.cliDAO = cliDAO;
        this.herramientas = herramientas;
        this.pedDAO = pedDAO;
        this.vistaClientes = vistaClientes;
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
        boolean clienteNuevo = true;
        switch (registroCliente){
            case 1:
                String email = herramientas.pedirString("Indica el email del cliente: ");
                cliente = pedDAO.buscarCliente(email);
                clienteNuevo = false;
                break;
            case 2:
                cliente = crearCliente();
                clienteNuevo = true;
                break;
        }
        if(cliente != null){
            String codigoArticulo = herramientas.pedirString("Indica el código del artículo: ");
            Articulo articulo = pedDAO.buscarArticulo(codigoArticulo);
            if(articulo != null){
                pedido = vistaPedidos.infoPedido(cliente, articulo);
                pedDAO.insertarPedido(pedido, clienteNuevo);
            }
        }
    }

    public Cliente crearCliente(){
        String email = vistaClientes.emailCliente();
        Cliente cliente = null;
        boolean vuelta = true;
        while(vuelta){
            Cliente cliExistente = cliDAO.buscarCliente(email);
            if(cliExistente == null){
                vuelta = false;
                cliente = vistaClientes.infoCliente(email);
                vistaClientes.tipoCliente();
                int tipo = herramientas.errorIntEntrada();
                tipo = herramientas.comprobarOpcion(tipo, 1, 2);
                switch (tipo){
                    case 1:
                        cliente = new Estandar(cliente.getEmail(), cliente.getNombre(), cliente.getDomicilio(), cliente.getNif());
                        break;
                    case 2:
                        cliente = new Premium(cliente.getEmail(), cliente.getNombre(), cliente.getDomicilio(), cliente.getNif());
                }
            }else{
                email = herramientas.repetirString(1);
            }
        }
        return cliente;
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
