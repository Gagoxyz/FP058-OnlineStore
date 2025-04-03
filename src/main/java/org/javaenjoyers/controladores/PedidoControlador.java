package org.javaenjoyers.controladores;

import org.javaenjoyers.modelos.Articulo;
import org.javaenjoyers.modelos.Cliente;
import org.javaenjoyers.modelos.Modelo;
import org.javaenjoyers.modelos.Pedido;
import org.javaenjoyers.vistas.Vista;

import java.util.Objects;

public class PedidoControlador {


    private Modelo modelo;
    private Vista vista;
    private ClienteControlador clienteControlador;


    public PedidoControlador(Modelo modelo, Vista vista, ClienteControlador clienteControlador) {
        this.modelo = modelo;
        this.vista = vista;
        this.clienteControlador = clienteControlador;
    }

    /**
     * Menú para la gestión de pedidos, recibirá la opción indicada por el usuario dede la vista
     * @param opcionVista Opción indicada por el usuario
     */
    public void opcionGestionPedidos(String opcionVista){
        int opcion;
        try {
            opcion = Integer.parseInt(opcionVista);
        } catch (Exception e){
            vista.mostrarMensaje("\nOpción incorrecta.");
            return;
        }
        switch (opcion){
            case 1:
                nuevoPedido();
                break;
            case 2:
                eliminarPedido();
                break;
            case 3:
                mostrarPedidosPdteEnvio();
                break;
            case 4:
                mostrarPedidosEnviados();
                break;
            case 5:
                resumenPedidos();
                break;
            case 0:
                return;
            default:
                vista.mostrarMensaje("\nOpción incorrecta.\n");
                break;
        }
    }

    /**
     * Añadirá un nuevo pedido con los datos registrados por la entrada de usuario
     */
    public void nuevoPedido(){
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        Cliente cliente = modelo.buscarClientePorEmail(emailCliente);

        while (cliente == null){
            vista.mostrarMensaje("\nCliente no existe, se procede a registradrlo.");
            clienteControlador.nuevoCliente();
            cliente = buscarClientePorEmail(emailCliente);
        }
        modelo.obtenerArticulos();
        String codigoProducto = vista.solicitarDato("\nIndica el código del producto:");
        codigoProducto = codigoProducto.toUpperCase();
        Articulo articulo = buscarArticuloPorCodigo(codigoProducto);
        String cantidad = vista.solicitarDato("\nIndica la cantidad:");
        int intCantidad;
        try {
            intCantidad = Integer.parseInt(cantidad);
        } catch (Exception e){
            vista.mostrarMensaje("\nCantidad introducida no válida, debe ser un número entero.");
            return;
        }

        Pedido nuevoPedido = new Pedido(cliente, articulo, intCantidad);
        modelo.agregarPedido(nuevoPedido);
        vista.mostrarMensaje("\nSe ha registrado el pedido correctamente.\n");
    }

    /**
     * Obtendrá un cliente buscándolo por el email
     * @param email Email del cliente por el que realizará la búsqueda
     * @return Devuelve un objeto de tipo Cliente
     */
    public Cliente buscarClientePorEmail(String email){
        return modelo.getClientesPorEmail().get(email);
    }

    /**
     * Obtendrá un artículo buscándolo por el código de producto
     * @param codigo Código de producto por el que realizará la búsqueda
     * @return Devuelve un objeto de tipo Articulo
     */
    public Articulo buscarArticuloPorCodigo(String codigo){
        return modelo.getArticulosPorCodigo().get(codigo);
    }

    /**
     * Eliminará un pedido indicado por el usuario
     */
    public void eliminarPedido(){
        modelo.obtenerPedidos();
        String numeroPedido = vista.solicitarDato("\nIndica el número del pedido (0 para salir):");
        if(Objects.equals(numeroPedido, "0")){
            return;
        }
        int nPedido;
        try {
            nPedido = (Integer.parseInt(numeroPedido));
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido, debe ser un número entero.");
            return;
        }
        Pedido eliminarPedido = null;
        for (Pedido p : modelo.getPedidos().getLista()){
            if(p.getNumPedido() == nPedido){
                eliminarPedido = p;
                break;
            }
        }

        if (eliminarPedido != null){
            if(!modelo.verificarTiempoPedido(eliminarPedido)){
                modelo.eliminarPedido(nPedido);
                vista.mostrarMensaje("\nPedido eliminado correctamente.\n");
            } else {
                vista.mostrarMensaje("\nPedido enviado, no se puede eliminar.\n");
            }
        } else {
            vista.mostrarMensaje("\nNo hay ningún pedido con ese número.\n");
        }
    }

    /**
     * Mostrará los pedidos que estén pendientes de enviar, se filtrará por el email del cliente
     */
    public void mostrarPedidosPdteEnvio(){
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        Cliente cliente = modelo.buscarClientePorEmail(emailCliente);
        if(cliente == null){
            vista.mostrarMensaje("\nNo hay ningún cliente registrado con el correo " + emailCliente + "\n");
            return;
        }
        boolean pedidosPdteEnvio = false;
        for (Pedido pedido : cliente.getPedidos()){
            if(!modelo.verificarTiempoPedido(pedido)){
                vista.mostrarMensaje(pedido.toString());
                pedidosPdteEnvio = true;
            }
        }
        if(!pedidosPdteEnvio){
            vista.mostrarMensaje("\nEl cliente no tiene pedidos pendientes de envío.\n");
        }
    }

    /**
     * Mostrará los pedidos que estén enviados, se filtrará por el email del cliente
     */
    public void mostrarPedidosEnviados() {
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        Cliente cliente = modelo.buscarClientePorEmail(emailCliente);
        if (cliente == null) {
            vista.mostrarMensaje("\nNo hay ningún cliente registrado con el correo " + emailCliente + "\n");
            return;
        }
        boolean pedidosPdteEnvio = false;
        for (Pedido pedido : cliente.getPedidos()) {
            if (modelo.verificarTiempoPedido(pedido)) {
                vista.mostrarMensaje(pedido.toString());
                pedidosPdteEnvio = true;
            }
        }
        if (!pedidosPdteEnvio) {
            vista.mostrarMensaje("\nEl cliente no tiene pedidos enviados.\n");
        }
    }

    /**
     * Mostrará un resumen con todos los pedidos
     */
    public void resumenPedidos(){
        vista.mostrarMensaje(modelo.obtenerArticulos().toString());
    }
}
