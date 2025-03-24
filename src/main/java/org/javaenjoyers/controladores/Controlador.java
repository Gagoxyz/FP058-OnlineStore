package org.javaenjoyers.controladores;

import org.javaenjoyers.modelos.*;
import org.javaenjoyers.vistas.Vista;

import java.util.Objects;

/**
 * Clase Controlador, se encargará de realizar la lógica e interactuar entre el Modelo y la Vista
 */

public class Controlador {

    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    /**
     * Mostrará en la vista los prints de bienvenida
     */
    public void bienvenida(){
        vista.bienvenida();
    }

    /**
     * Mostrará en la Vista el menú principal
     */
    public void inicio(){
        vista.menuPrincipal();
    }

    /**
     * Menú para la gestión de clientes, recibirá la opción indicada por el usuario dede la vista
     * @param opcionVista Opción indicada por el usuario
     */
    public void opcionGestionClientes(String opcionVista){
        int opcion;
        try {
            opcion = Integer.parseInt(opcionVista);
        } catch (Exception e){
            vista.mostrarMensaje("\nOpción incorrecta.");
            return;
        }
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

    /**
     * Añadirá un nuevo cliente con los datos registrados por la entrada de usuario
     */
    public void nuevoCliente(){
        String tipoCliente = vista.solicitarDato("\nIndica tiepo cliente (Estandar/Premium):");
        tipoCliente = tipoCliente.toUpperCase();
        if(!Objects.equals(tipoCliente, "ESTANDAR") && !Objects.equals(tipoCliente, "PREMIUM")){
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
            vista.mostrarMensaje("\nNo se registró nuevo cliente, ya existe en el sistema.");
        }
    }

    /**
     * Mostrará el listado de clientes (se llamará desde el Modelo)
     */
    public void mostrarClientes(){
        modelo.mostrarClientes();
    }

    /**
     * Mostrará el listado de clientes "Estandar" (se llamará desde el Modelo)
     */
    public void mostrarClientesEstandar(){
        modelo.mostrarClientesEstandar();
    }

    /**
     * Mostrará el listado de clientes "Premium" (se llamará desde el Modelo)
     */
    public void mostrarClientesPremium(){
        modelo.mostrarClientesPremium();
    }

    /**
     * Menú para la gestión de artículos, recibirá la opción indicada por el usuario dede la vista
     * @param opcionVista Opción indicada por el usuario
     */
    public void opcionGestionArticulos(String opcionVista){
        int opcion;
        try {
            opcion = Integer.parseInt(opcionVista);
        } catch (Exception e){
            vista.mostrarMensaje("\nOpción incorrecta.");
            return;
        }
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

    /**
     * Añadirá un nuevo artículo con los datos registrados por la entrada de usuario
     */
    public void nuevoArticulo(){
        String codigoProducto = vista.solicitarDato("\nCódigo del artículo:");
        codigoProducto = codigoProducto.toUpperCase();
        for (Articulo articulo : modelo.getArticulos().getLista()){
            if(Objects.equals(articulo.getCodigoProducto(), codigoProducto)){
                vista.mostrarMensaje("\nCódigo de producto ya registrado");
                return;
            }
        }
        String descripcion = vista.solicitarDato("Descripción del artículo:");
        String precioVenta = vista.solicitarDato("Precio de venta:");
        precioVenta = precioVenta.replace(',', '.');
        float floatPV;
        try {
            floatPV = Float.parseFloat(precioVenta);
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido. Ejemplo correcto '4.99', '4,99' o '4'");
            return;
        }
        String gastosEnvio = vista.solicitarDato("Gastos de envío:");
        gastosEnvio = gastosEnvio.replace(',', '.');
        float floatGE;
        try {
            floatGE = Float.parseFloat(gastosEnvio);
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido. Ejemplo correcto '4.99', '4,99' o '4'");
            return;
        }
        String tiempoPrepEnvio = vista.solicitarDato("Tiempo preparación envío:");
        int intTPE;
        try {
            intTPE = Integer.parseInt(tiempoPrepEnvio);
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido.\nDebe ser el tiempo en minutos (número entero, ej: '5'");
            return;
        }
        Articulo nuevoArticulo = new Articulo(codigoProducto, descripcion, floatPV, floatGE, intTPE);
        modelo.agregarArticulo(nuevoArticulo);

        vista.mostrarMensaje("\nArtículo registado correctamente.\n");
    }

    /**
     * Mostrará el listado de artículos (se llamará desde el Modelo)
     */
    public void mostrarArticulos(){
        modelo.mostrarArticulos();
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
            nuevoCliente();
            cliente = buscarClientePorEmail(emailCliente);
        }
        modelo.mostrarArticulos();
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
        modelo.mostrarPedidos();
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
        modelo.mostrarPedidos();
    }
}