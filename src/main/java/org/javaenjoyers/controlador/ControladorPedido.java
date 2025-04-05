package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.OnlineStore;
import org.javaenjoyers.modelo.Pedido;
import org.javaenjoyers.vista.VistaPedidos;

import java.util.ArrayList;

public class ControladorPedido {
    private VistaPedidos vistaPedidos;
    private Herramientas herramientas;
    private OnlineStore tienda;
    private ControladorCliente contrCliente;

    public ControladorPedido(ControladorCliente contrCliente, Herramientas herramientas, OnlineStore tienda, VistaPedidos vistaPedidos) {
        this.contrCliente = contrCliente;
        this.herramientas = herramientas;
        this.tienda = tienda;
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
        Pedido pedidoNuevo;
        Cliente cliente = null;
        switch (registroCliente){
            case 1:
                String email = herramientas.pedirString("Indica el email del cliente: ");
                cliente = buscarCliente(email);
                break;
            case 2:
                contrCliente.addCliente();
                break;
        }
        if(registroCliente == 2){
            cliente = tienda.getCli().getLista().getLast();
        }
        if(cliente != null){
            String codigoArticulo = herramientas.pedirString("Indica el código del artículo: ");
            Articulo articulo = buscarArticulo(codigoArticulo);
            if(articulo != null){
                int numero = tienda.buscarNumeroPedido();
                pedidoNuevo = vistaPedidos.infoPedido(cliente, articulo, numero);
                tienda.addPedido(pedidoNuevo);
                herramientas.enviarMensaje(1, null);
            }
        }
    }

    public Cliente buscarCliente(String email){
        Cliente clienteVacio = null;
        boolean vuelta = true;
        while(vuelta){
            for(Cliente i : tienda.getCli().getLista()){
                if(i.getEmail().equals(email)){
                    return i;
                }
            }
            email = herramientas.repetirString(2);
            if(email.equals("0")){
                vuelta = false;
            }
        }
        return clienteVacio;
    }

    public Articulo buscarArticulo(String codigo){
        Articulo articuloVacio = null;
        boolean vuelta = true;
        while(vuelta){
            for(Articulo i : tienda.getArt().getLista()){
                if(i.getCodigoProducto().equals(codigo)){
                    return i;
                }
            }
            codigo = herramientas.repetirString(2);
            if(codigo.equals("0")){
                vuelta = false;
            }
        }
        return articuloVacio;
    }

    public void removePedido(){
        int numero = vistaPedidos.pedirNum();
        Pedido pedidoBuscado = buscarPedido(numero);
        if(pedidoBuscado != null){
            boolean estadoPedido = pedidoBuscado.envioPendiente();
            if(estadoPedido){
                tienda.removePedido(pedidoBuscado);
                herramientas.enviarMensaje(1, null);
            }else{
                herramientas.enviarMensaje(0, "El pedido indicado ya ha sido enviado, no puede eliminarse.");
            }
        }
    }

    public Pedido buscarPedido(int numero){
        Pedido pedidoVacio = null;
        boolean vuelta = true;
        while(vuelta){
            for(Pedido i : tienda.getPed().getLista()){
                if(i.getNumPedido() == numero){
                    return i;
                }
            }
            numero = herramientas.repetirInt();
            if(numero == 0){
                vuelta = false;
            }
        }
        return pedidoVacio;
    }

    public void showPedidos(boolean pendiente){
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        boolean estadoPedido;
        int eleccion = vistaPedidos.mostrarPedidos();
        eleccion = herramientas.comprobarOpcion(eleccion, 1, 2);
        boolean listaVacia = true;
        if(eleccion == 1){
            for(Pedido i : tienda.getPed().getLista()){
                estadoPedido = i.envioPendiente();
                if(estadoPedido == pendiente){
                    listaPedidos.add(i);
                    listaVacia = false;
                }
            }
        }else{
            String email = herramientas.pedirString("\nIndica el email del cliente: ");
            Cliente cliente = buscarCliente(email);
            for(Pedido i : tienda.getPed().getLista()){
                if(i.getCliente().getEmail().equals(cliente.getEmail())){
                    estadoPedido = i.envioPendiente();
                    if(estadoPedido == pendiente){
                        listaPedidos.add(i);
                        listaVacia = false;
                    }
                }
            }
        }
        if(listaVacia){
            herramientas.enviarMensaje(0, "\nNo hay pedidos que mostrar.");
        }else{
            vistaPedidos.showPedidos(listaPedidos);
        }
    }
}
