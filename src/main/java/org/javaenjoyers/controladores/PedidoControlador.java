package org.javaenjoyers.controladores;

import org.javaenjoyers.modelos.*;
import org.javaenjoyers.vistas.Vista;
import java.util.List;
import java.util.Objects;

public class PedidoControlador {

    private final PedidoModelo pedidoModelo;
    private final ArticuloModelo articuloModelo;
    private final Vista vista;
    private final ClienteControlador clienteControlador;

    public PedidoControlador(PedidoModelo pedidoModelo, ArticuloModelo articuloModelo, Vista vista, ClienteControlador clienteControlador) {
        this.pedidoModelo = pedidoModelo;
        this.articuloModelo = articuloModelo;
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
        Cliente cliente = pedidoModelo.obtenerClientePorEmail(emailCliente);

        while (cliente == null){
            vista.mostrarMensaje("\nCliente no existe, se procede a registradrlo.");
            clienteControlador.nuevoCliente();
            cliente = pedidoModelo.obtenerClientePorEmail(emailCliente);
        }
        articuloModelo.obtenerArticulos();
        String codigoProducto = vista.solicitarDato("\nIndica el código del producto:");
        codigoProducto = codigoProducto.toUpperCase();
        Articulo articulo = articuloModelo.obtenerArticuloPorCodigo(codigoProducto);
        if (articulo == null){
            vista.mostrarMensaje("\nEl código de producto no existe.\n");
            return;
        }

        String cantidad = vista.solicitarDato("\nIndica la cantidad:");
        int intCantidad;
        try {
            intCantidad = Integer.parseInt(cantidad);
        } catch (Exception e){
            vista.mostrarMensaje("\nCantidad introducida no válida, debe ser un número entero.");
            return;
        }

        Pedido nuevoPedido = new Pedido(cliente, articulo, intCantidad);
        pedidoModelo.agregarPedido(nuevoPedido);
        vista.mostrarMensaje("\nSe ha registrado el pedido correctamente.\n");
    }

    /**
     * Eliminará un pedido indicado por el usuario
     */
    public void eliminarPedido(){
        vista.mostrarMensaje(pedidoModelo.obtenerPedidos().toString());
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
        Pedido pedido = pedidoModelo.obtenerPedidoPorNumPedido(nPedido);
        if(pedido == null){
            vista.mostrarMensaje("\nNo hay ningún pedido con ese número.\n");
            return;
        }

        if(!pedidoModelo.verificarTiempoPedido(pedido)){
            pedidoModelo.eliminarPedido(pedido);
            vista.mostrarMensaje("\nPedido eliminador correctamente.\n");
        } else {
            vista.mostrarMensaje("\nPedido enviado, no se puede eliminar.\n");
        }
    }

    /**
     * Mostrará los pedidos que estén pendientes de enviar, se filtrará por el email del cliente
     */
    public void mostrarPedidosPdteEnvio(){
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        Cliente cliente = pedidoModelo.obtenerClientePorEmail(emailCliente);
        if(cliente == null){
            vista.mostrarMensaje("\nNo hay ningún cliente registrado con el correo " + emailCliente + "\n");
            return;
        }
        List<Pedido> pedidosPdteEnvio = pedidoModelo.obtenerPedidosPendientesEnvio(cliente);
        if (pedidosPdteEnvio.isEmpty()){
            vista.mostrarMensaje("\nEl cliente no tiene pedidos pendientes de envío.");
        } else {
            for (Pedido pedido : pedidosPdteEnvio){
                vista.mostrarMensaje(pedido.toString());
            }
        }
    }

    /**
     * Mostrará los pedidos que estén enviados, se filtrará por el email del cliente
     */
    public void mostrarPedidosEnviados() {
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        Cliente cliente = pedidoModelo.obtenerClientePorEmail(emailCliente);
        if (cliente == null) {
            vista.mostrarMensaje("\nNo hay ningún cliente registrado con el correo " + emailCliente + "\n");
            return;
        }
        List<Pedido> pedidosEnviados = pedidoModelo.obtenerPedidosEnviados(cliente);
        if(pedidosEnviados.isEmpty()){
            vista.mostrarMensaje("\nEl cliente no tiene pedidos enviados.");
        } else {
            for (Pedido pedido : pedidosEnviados){
                vista.mostrarMensaje(pedido.toString());
            }
        }
    }

    /**
     * Mostrará un resumen con todos los pedidos
     */
    public void resumenPedidos(){
        vista.mostrarMensaje(pedidoModelo.obtenerPedidos().toString());
    }
}
