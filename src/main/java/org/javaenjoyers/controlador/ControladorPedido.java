package org.javaenjoyers.controlador;

import org.hibernate.query.criteria.JpaEntityJoin;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.JavaFX.VistaJavaFX;
import org.javaenjoyers.vista.JavaFX.VistaPedJavaFX;
import org.javaenjoyers.vista.VistaClientes;
import org.javaenjoyers.vista.VistaPedidos;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ControladorPedido {
    private VistaPedidos vistaPedidos;
    private Herramientas herramientas;
    private PedidoDAO pedDAO;
    private VistaClientes vistaClientes;
    private ClienteDAO cliDAO;

    private VistaPedJavaFX vistaPedidosJFX;
    private VistaJavaFX vistaJFX;
    private ControladorCliente contrCli;

    public ControladorPedido(ClienteDAO cliDAO, Herramientas herramientas, PedidoDAO pedDAO, VistaClientes vistaClientes, VistaPedidos vistaPedidos) {
        this.cliDAO = cliDAO;
        this.herramientas = herramientas;
        this.pedDAO = pedDAO;
        this.vistaClientes = vistaClientes;
        this.vistaPedidos = vistaPedidos;
    }

    public void setVistaJFX(VistaJavaFX vistaJFX) {
        this.vistaJFX = vistaJFX;
    }

    public void setVistaPedidosJFX(VistaPedJavaFX vistaPedidosJFX) {
        this.vistaPedidosJFX = vistaPedidosJFX;
    }

    public void setContrCli(ControladorCliente contrCli) {
        this.contrCli = contrCli;
    }

    //MIS MÉTODOS
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
        boolean vuelta = true;
        switch (registroCliente){
            case 1:
                String email = herramientas.pedirString("Indica el email del cliente: ");
                while(vuelta){
                    cliente = pedDAO.buscarCliente(email);
                    if(cliente == null){
                        email = herramientas.repetirString(2);
                        if(email.equals("0")){
                            vuelta = false;
                        }
                    }else{
                        vuelta = false;
                    }
                }
                clienteNuevo = false;
                break;
            case 2:
                cliente = crearCliente();
                break;
        }
        if(cliente != null){
            String codigoArticulo = herramientas.pedirString("Indica el código del artículo: ");
            boolean repetir = true;
            Articulo articulo = new Articulo();
            while(repetir){
                articulo = pedDAO.buscarArticulo(codigoArticulo);
                if(articulo == null){
                    codigoArticulo = herramientas.repetirString(2);
                    if(codigoArticulo.equals("0")){
                        repetir = false;
                        articulo = null;
                    }
                }else{
                    repetir = false;
                }
            }
            if(articulo != null){
                pedido = vistaPedidos.infoPedido(cliente, articulo);
                pedDAO.insertarPedido(pedido, clienteNuevo);
                herramientas.enviarMensaje(1, null);
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
        Pedido pedido = new Pedido();
        boolean vuelta = true;
        while(vuelta){
            pedido = pedDAO.buscarPedido(numero);
            if(pedido != null){
                vuelta = false;
            }else{
                numero = herramientas.repetirInt();
                if(numero == 0){
                    vuelta = false;
                }
            }
        }
        if(pedido != null){
            boolean pendiente = pedDAO.estadoPedido(numero);
            if(pendiente){
                boolean exito = pedDAO.eliminarPedido(pedido);
                if(exito){
                    herramientas.enviarMensaje(1, null);
                }
            }else{
                herramientas.enviarMensaje(0, "\nEl pedido ya ha sido enviado, no se puede eliminar.");
            }
        }

    }

    public void showPedidos(boolean pendiente){
        List<Pedido> listaPed;
        int eleccion = vistaPedidos.mostrarPedidos();
        eleccion = herramientas.comprobarOpcion(eleccion, 1, 2);
        switch (eleccion){
            case 1:
                listaPed = pedDAO.mostrarPedidos(pendiente, null);
                vistaPedidos.showPedidos(listaPed);
                break;
            case 2:
                String email = herramientas.pedirString("\nIndica el email del cliente: ");
                boolean vuelta = true;
                Cliente cliente = new Cliente();
                while(vuelta){
                    cliente = pedDAO.buscarCliente(email);
                    if(cliente == null){
                        email = herramientas.repetirString(2);
                        if(email.equals("0")){
                            cliente = null;
                            vuelta = false;
                        }
                    }else{
                        vuelta = false;
                    }
                }
                if(cliente != null){
                    listaPed = pedDAO.mostrarPedidos(pendiente, cliente);
                    vistaPedidos.showPedidos(listaPed);
                }
        }
    }

    //MÉTODOS PARA JavaFX
    public void menuPedidosFX(){
        vistaJFX.cambiarVista(vistaPedidosJFX.menuPedidos());
    }

    public void comprobarEmail(String email, int caso, boolean pendiente){
        Cliente cliente;
        cliente = pedDAO.buscarCliente(email);
        if(cliente == null){
            vistaJFX.cambiarVista(vistaPedidosJFX.inexistente(null, 1));
        }else{
            if(caso == 1){
                vistaJFX.cambiarVista(vistaPedidosJFX.pedirCodigo(cliente));
            }else{
                mostrarPedidos(pendiente, cliente);
            }

        }
    }

    public void buscarArticulo(String codigo, Cliente cliente){
        Articulo articulo = pedDAO.buscarArticulo(codigo);
        if(articulo == null){
            vistaJFX.cambiarVista(vistaPedidosJFX.inexistente(cliente, 2));
        }else{
            vistaJFX.cambiarVista(vistaPedidosJFX.infoPedido(cliente, articulo));
        }
    }

    public void crearPedido(Cliente cliente, Articulo articulo, String cantString){
        try{
            int cantidad = Integer.parseInt(cantString);
            Pedido pedido = new Pedido(articulo, cantidad, cliente);
            pedDAO.insertarPedido(pedido, false);
            vistaJFX.cambiarVista(vistaPedidosJFX.pedidoInsertado());
        }catch(NumberFormatException e){
            vistaJFX.cambiarVista(vistaPedidosJFX.formatoIncorrecto(cliente, articulo, 1));
        }
    }

    public void crearClienteFX(){
        contrCli.pedirEmail(true);
    }

    public void clienteInsertado(Cliente cliente){
        vistaJFX.cambiarVista(vistaPedidosJFX.clienteInsertado(cliente));
    }

    public void buscarPedido(String numPedido){
        try{
            int numero = Integer.parseInt(numPedido);
            Pedido pedido = pedDAO.buscarPedido(numero);
            if(pedido == null){
                vistaJFX.cambiarVista(vistaPedidosJFX.inexistente(null, 3));
            }else{
                boolean pendiente = pedDAO.estadoPedido(numero);
                if(pendiente){
                    pedDAO.eliminarPedido(pedido);
                    vistaJFX.cambiarVista(vistaPedidosJFX.eliminacionPedido(2));
                }else{
                    vistaJFX.cambiarVista(vistaPedidosJFX.eliminacionPedido(1));
                }
            }
        }catch(NumberFormatException e){
            vistaJFX.cambiarVista(vistaPedidosJFX.formatoIncorrecto(null, null, 2));
        }
    }

    public void mostrarPedidos(boolean pendiente, Cliente cliente){
        List<Pedido> listaPed = pedDAO.mostrarPedidos(pendiente, cliente);
        String email = null;
        if (cliente != null){
            email = cliente.getEmail();
        }
        vistaJFX.cambiarVista(vistaPedidosJFX.mostrarPedidos(listaPed, email));
    }
}
