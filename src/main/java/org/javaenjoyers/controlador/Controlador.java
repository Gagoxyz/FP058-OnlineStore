package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.*;

public class Controlador {
    private OnlineStore tienda;
    private Vista vista;
    private Herramientas herramientas;
    private ControladorCliente contrCliente;
    private ControladorArticulo contrArticulo;
    private ControladorPedido contrPedido;

    public Controlador(ControladorArticulo contrArticulo, ControladorCliente contrCliente, ControladorPedido contrPedido, Herramientas herramientas, OnlineStore tienda, Vista vista) {
        this.contrArticulo = contrArticulo;
        this.contrCliente = contrCliente;
        this.contrPedido = contrPedido;
        this.herramientas = herramientas;
        this.tienda = tienda;
        this.vista = vista;
    }

    public void inicio(){
        vista.bienvenida();
        int opcion;
        int opcionMenu;
        //int opcionSegunda;
        boolean continuar = true;
        //boolean seguir;
        while(continuar){
            opcion = vista.menuPrincipal();
            opcionMenu = herramientas.comprobarOpcion(opcion, 0, 3);
            switch(opcionMenu){
                case 1:
                    /*seguir = true;
                    while(seguir){
                        opcion = vista.menuClientes();
                        opcionSegunda = comprobarOpcion(opcion, 0, 4);
                        switch (opcionSegunda){
                            case 1:
                                addCliente();
                                break;
                            case 2:
                                tienda.showClientes();
                                break;
                            case 3:
                                tienda.showEstandar();
                                break;
                            case 4:
                                tienda.showPremium();
                                break;
                            case 0:
                                seguir = false;
                                break;
                        }
                    }*/
                    contrCliente.menuClientes();
                    break;
                case 2:
                    /*seguir = true;
                    while(seguir){
                        opcion = vista.menuArticulos();
                        opcionSegunda = comprobarOpcion(opcion, 0, 2);
                        switch (opcionSegunda){
                            case 1:
                                addArticulo();
                                break;
                            case 2:
                                tienda.showArticulos();                                break;
                            case 0:
                                seguir = false;
                                break;
                        }
                    }*/
                    contrArticulo.menuArticulos();
                    break;
                case 3:
                    /*seguir = true;
                    while(seguir){
                        opcion = vista.menuPedidos();
                        opcionSegunda = comprobarOpcion(opcion, 0, 4);
                        switch (opcionSegunda){
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
                    }*/
                    contrPedido.menuPedidos();
                    break;
                case 0:
                    continuar = false;
                    break;
            }
        }
    }

    /*public void addCliente(){
        String email = vista.emailCliente();
        boolean repetido = true;
        while(repetido){
            for(Cliente i : tienda.getClientes()){
                if(i.getEmail().equals(email)){
                    email = vista.repeticionString();
                    repetido = true;
                    break;
                }
                repetido = false;
            }
        }
        Cliente clienteModelo = vista.infoCliente(email);
        int tipo = vista.tipoCliente();
        int tipoBueno = comprobarOpcion(tipo, 1, 2);
        switch (tipoBueno){
            case 1:
                Cliente clienteEstandar = new Estandar(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                tienda.addCliente(clienteEstandar);
                break;
            case 2:
                Cliente clientePremium = new Premium(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                tienda.addCliente(clientePremium);
                break;
        }
        vista.ejecucionExitosa();
    }*/

    /*public void addArticulo(){
        String codigo = vista.codigoArticulo();
        boolean repetido = true;
        while(repetido){
            for(Articulo i : tienda.getArticulos()){
                if(i.getCodigoProducto().equals(codigo)){
                    codigo = vista.repeticionString();
                    repetido = true;
                    break;
                }
                repetido = false;
            }
        }
        Articulo articuloNuevo = vista.infoArticulo(codigo);
        tienda.addArticulo(articuloNuevo);
        vista.ejecucionExitosa();
    }*/

    /*public void addPedido(){
        int opcionCliente = vista.clientePedido();
        int registroCliente = comprobarOpcion(opcionCliente, 1, 2);
        Pedido pedidoNuevo;
        Cliente cliente = null;
        switch (registroCliente){
            case 1:
                String email = vista.indicarCliente();
                cliente = buscarCliente(email);
                break;
            case 2:
                addCliente();
                break;
        }
        if(registroCliente == 2){
            cliente = tienda.getClientes().getLast();
        }
        if(cliente != null){
            String codigoArticulo = vista.indicarArticulo();
            Articulo articulo = buscarArticulo(codigoArticulo);
            if(articulo != null){
                int numero = tienda.buscarNumeroPedido();
                pedidoNuevo = vista.infoPedido(cliente, articulo, numero);
                tienda.addPedido(pedidoNuevo);
                vista.ejecucionExitosa();
            }
        }
    }*/

    /*public Cliente buscarCliente(String email){
        Cliente clienteVacio = null;
        boolean vuelta = true;
        while(vuelta){
            for(Cliente i : tienda.getClientes()){
                if(i.getEmail().equals(email)){
                    return i;
                }
            }
            email = vista.corregirString();
            if(email.equals("0")){
                vuelta = false;
            }
        }
        return clienteVacio;
    }*/

    /*public Articulo buscarArticulo(String codigo){
        Articulo articuloVacio = null;
        boolean vuelta = true;
        while(vuelta){
            for(Articulo i : tienda.getArticulos()){
                if(i.getCodigoProducto().equals(codigo)){
                    return i;
                }
            }
            codigo = vista.corregirString();
            if(codigo.equals("0")){
                vuelta = false;
            }
        }
        return articuloVacio;
    }*/

    /*public void removePedido(){
        int numero = vista.indicarNumero();
        Pedido pedidoBuscado = buscarPedido(numero);
        if(pedidoBuscado != null){
            boolean estadoPedido = pedidoBuscado.envioPendiente();
            if(estadoPedido){
                tienda.removePedido(pedidoBuscado);
                vista.ejecucionExitosa();
            }else{
                vista.noEliminar();
            }
        }
    }*/

    /*public Pedido buscarPedido(int numero){
        Pedido pedidoVacio = null;
        boolean vuelta = true;
        while(vuelta){
            for(Pedido i : tienda.getPedidos()){
                if(i.getNumPedido() == numero){
                    return i;
                }
            }
            numero = vista.corregirInt();
            if(numero == 0){
                vuelta = false;
            }
        }
        return pedidoVacio;
    }*/

    /*public void showPedidos(boolean pendiente){
        int eleccion = vista.mostrarPedidos();
        int opcion = comprobarOpcion(eleccion, 1, 2);
        boolean contador;
        if(opcion == 1){
            contador = tienda.showPedidos(pendiente, null);
        }else{
            String email = vista.indicarCliente();
            Cliente cliente = buscarCliente(email);
            contador = tienda.showPedidos(pendiente, cliente);
        }
        if(!contador){
            vista.ceroPedidos();
        }
    }*/

    /*public int comprobarOpcion(int eleccion, int inicio, int fin){
        while(eleccion < inicio || eleccion > fin){
            eleccion = vista.intInvalido();
        }
        return eleccion;
    }*/
}