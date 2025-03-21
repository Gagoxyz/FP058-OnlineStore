package org.javaenjoyers.controladores;

import org.javaenjoyers.modelos.*;
import org.javaenjoyers.vistas.Vista;

import java.util.Objects;


public class Controlador {

    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void bienvenida(){
        vista.bienvenida();
    }

    public void inicio(){
        vista.menuPrincipal();
    }

    public void opcionGestionClientes(int opcion){
        switch (opcion){
            case 1:
                nuevoCliente();
                break;
            case 2:
                mostrarClientes();
                break;
            case 3:
                mostrarClientesEstandar();
                break;
            case 4:
                mostrarClientesPremium();
                break;
            case 0:
                return;
            default:
                vista.mostrarMensaje("\nOpción incorrecta.\n");
                break;
        }
    }

    public void nuevoCliente(){
        String tipoCliente = vista.solicitarDato("\nIndica tiepo cliente (Estandar/Premium):");
        if(!Objects.equals(tipoCliente, "Estandar") && !Objects.equals(tipoCliente, "Premium")){
            System.out.println("\nTipo de cliente incorrecto.\n");
            return;
        }
        String nif = vista.solicitarDato("Indica el NIF:");
        String nombre = vista.solicitarDato("Indica el Nombre:");
        String email = vista.solicitarDato("Indica el Email:");
        String domicilio = vista.solicitarDato("Indica el Domicilio:");

        Cliente nuevoCliente;
        if (Objects.equals(tipoCliente, "Estandar")){
            nuevoCliente = new Estandar(email, nombre, domicilio, nif);
        } else {
            nuevoCliente = new Premium(email,nombre, domicilio, nif);
        }
        modelo.agregarCliente(nuevoCliente);

        if(modelo.getClientes().getLista().contains(nuevoCliente)){
            vista.mostrarMensaje("\nCliente registrado correctamente.\n");
        } else {
            vista.mostrarMensaje("\nNo se registro nuevo cliente, ya existe.");
        }
    }

    public void mostrarClientes(){
        modelo.mostrarClientes();
    }

    public void mostrarClientesEstandar(){
        modelo.mostrarClientesEstandar();
    }

    public void mostrarClientesPremium(){
        modelo.mostrarClientesPremium();
    }

    public void opcionGestionArticulos(int opcion){
        switch (opcion){
            case 1:
                nuevoArticulo();
                break;
            case 2:
                mostrarArticulos();
                break;
            case 0:
                return;
            default:
                vista.mostrarMensaje("\nOpción incorrecta.\n");
                break;
        }
    }

    public void nuevoArticulo(){
        String codigoProducto = vista.solicitarDato("\nCódigo del artículo:");
        String descripcion = vista.solicitarDato("Descripción del artículo:");
        String precioVenta = vista.solicitarDato("Precio de venta:");
        float floatPV = Float.parseFloat(precioVenta);
        String gastosEnvio = vista.solicitarDato("Gastos de envío:");
        float floatGE = Float.parseFloat(gastosEnvio);
        String tiempoPrepEnvio = vista.solicitarDato("Tiempo preparación envío:");
        int intTPE = Integer.parseInt(tiempoPrepEnvio);

        Articulo nuevoArticulo = new Articulo(codigoProducto, descripcion, floatPV, floatGE, intTPE);
        modelo.agregarArticulo(nuevoArticulo);

        vista.mostrarMensaje("\nArtículo registado correctamente.\n");
    }

    public void mostrarArticulos(){
        modelo.mostrarArticulos();
    }

    public void opcionGestionPedidos(int opcion){
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
            case 0:
                return;
            default:
                vista.mostrarMensaje("\nOpción incorrecta.\n");
                break;
        }
    }

    public void nuevoPedido(){
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        Cliente cliente = modelo.buscarClientePorEmail(emailCliente);

        while (cliente == null){
            vista.mostrarMensaje("\nCliente no existe, se procede a registradrlo.");
            nuevoCliente();
            cliente = buscarClientePorEmail(emailCliente);
        }
        modelo.mostrarArticulos();
        String codigoProducto = vista.solicitarDato("\nIndica el código del producto:");
        Articulo articulo = buscarArticuloPorCodigo(codigoProducto);
        String cantidad = vista.solicitarDato("\nIndica la cantidad:");
        int intCantidad = Integer.parseInt(cantidad);

        Pedido nuevoPedido = new Pedido(cliente, articulo, intCantidad);
        modelo.agregarPedido(nuevoPedido);
        vista.mostrarMensaje("\nSe ha registrado el pedido correctamente.\n");
    }

    public Cliente buscarClientePorEmail(String email){
        return modelo.getClientesPorEmail().get(email);
    }

    public Articulo buscarArticuloPorCodigo(String codigo){
        return modelo.getArticulosPorCodigo().get(codigo);
    }

    public void eliminarPedido(){
        modelo.mostrarPedidos();
        String numeroPedido = vista.solicitarDato("\nIndica el número del pedido:");
        int nPedido = (Integer.parseInt(numeroPedido));
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

    public void mostrarPedidosPdteEnvio(){
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        boolean clienteEncontrado = false;
        for (Pedido pedido : modelo.getPedidos().getLista()){
            if(pedido.getCliente().getEmail().equals(emailCliente) && !modelo.verificarTiempoPedido(pedido)){
                vista.mostrarMensaje(pedido.toString());
                clienteEncontrado = true;
            }
        }
        if(!clienteEncontrado){
            vista.mostrarMensaje("\nNo hay ningún cliente registrado con el correo " + emailCliente + "\n");
        }
    }

    public void mostrarPedidosEnviados(){
        String emailCliente = vista.solicitarDato("\nIndica el email del cliente:");
        boolean clienteEncontrado = false;
        for (Pedido pedido : modelo.getPedidos().getLista()){
            if(pedido.getCliente().getEmail().equals(emailCliente) && modelo.verificarTiempoPedido(pedido)){
                vista.mostrarMensaje(pedido.toString());
                clienteEncontrado = true;
            }
        }
        if(!clienteEncontrado){
            vista.mostrarMensaje("\nNo hay ningún cliente registrado con el correo " + emailCliente + "\n");
        }
    }
}