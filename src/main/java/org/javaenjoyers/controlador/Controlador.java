package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class Controlador {
    private OnlineStore tienda;
    private Vista vista;

    public Controlador(OnlineStore tienda, Vista vista) {
        this.tienda = tienda;
        this.vista = vista;
    }

    public void inicio(){
        vista.bienvenida();
        int opcion;
        int opcionMenu;
        int opcionSegunda;
        boolean continuar = true;
        boolean seguir;
        while(continuar){
            opcion = vista.menuPrincipal();
            opcionMenu = comprobarOpcion(opcion, 0, 3);
            switch(opcionMenu){
                case 1:
                    seguir = true;
                    while(seguir){
                        opcion = vista.menuClientes();
                        opcionSegunda = comprobarOpcion(opcion, 0, 4);
                        switch (opcionSegunda){
                            case 1:
                                addCliente();
                                break;
                            case 2:
                                showCliente();
                                break;
                            case 3:
                                showEstandar();
                                break;
                            case 4:
                                showPremium();
                                break;
                            case 0:
                                seguir = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    seguir = true;
                    while(seguir){
                        opcion = vista.menuArticulos();
                        opcionSegunda = comprobarOpcion(opcion, 0, 2);
                        switch (opcionSegunda){
                            case 1:
                                addArticulo();
                                break;
                            case 2:
                                showArticulos();
                                break;
                            case 0:
                                seguir = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    seguir = true;
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
                                showPendientes();
                                break;
                            case 4:
                                showEnviados();
                                break;
                            case 0:
                                seguir = false;
                                break;
                        }
                    }
                    break;
                case 0:
                    continuar = false;
                    break;
            }
        }
    }

    public void addCliente(){
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
                tienda.getClientes().add(clienteEstandar);
                break;
            case 2:
                Cliente clientePremium = new Premium(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                tienda.getClientes().add(clientePremium);
                break;
        }
        vista.ejecucionExitosa();
    }

    public void showCliente(){
        System.out.println(tienda.getClientes());
    }

    public void showEstandar(){
        for(Cliente i : tienda.getClientes()){
            if(i instanceof Estandar){
                System.out.println(i);
            }
        }
    }

    public void showPremium(){
        for(Cliente i : tienda.getClientes()){
            if(i instanceof Premium){
                System.out.println(i);
            }
        }
    }

    public void addArticulo(){
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
        tienda.getArticulos().add(articuloNuevo);
        vista.ejecucionExitosa();
    }

    public void showArticulos(){
        System.out.println(tienda.getArticulos());
    }

    public void addPedido(){
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
                int numero = buscarNumeroPedido();
                pedidoNuevo = vista.infoPedido(cliente, articulo, numero);
                tienda.getPedidos().add(pedidoNuevo);
                vista.ejecucionExitosa();
            }
        }
    }

    public Cliente buscarCliente(String email){
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
    }

    public Articulo buscarArticulo(String codigo){
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
    }

    public int buscarNumeroPedido(){
        return tienda.getPedidos().getLast().getNumPedido() + 1;
    }

    public void removePedido(){
        int numero = vista.indicarNumero();
        Pedido pedidoBuscado = buscarPedido(numero);
        if(pedidoBuscado != null){
            boolean estadoPedido = envioPendiente(pedidoBuscado);
            if(estadoPedido){
                tienda.getPedidos().removeIf(p -> p.getNumPedido() == pedidoBuscado.getNumPedido());
                vista.ejecucionExitosa();
            }else{
                vista.noEliminar();
            }
        }
    }

    public Pedido buscarPedido(int numero){
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
    }

    public boolean envioPendiente(Pedido pedido){
        Duration duracion = Duration.between(pedido.getFechaHoraPedido(), LocalDateTime.now());
        int tiempoPasado = (int)duracion.toMinutes();
        int tiempoRequerido = pedido.getArticulo().getTiempoPrepEnvio() * pedido.getCantidad();
        if(tiempoPasado < tiempoRequerido){
            return true; //Está pendiente
        }else{
            return false; //Está enviado
        }
    }

    public void showPendientes(){
        showPedidos(true);
    }

    public void showEnviados(){
        showPedidos(false);
    }

    public void showPedidos(boolean pendiente){
        int eleccion = vista.mostrarPedidos();
        int opcion = comprobarOpcion(eleccion, 1, 2);
        boolean estadoPedido;
        boolean contador = false;
        if(opcion == 1){
            for(Pedido i : tienda.getPedidos()){
                estadoPedido = envioPendiente(i);
                if(estadoPedido == pendiente){
                    System.out.println(i);
                    contador = true;
                }
            }
        }else{
            String email = vista.indicarCliente();
            Cliente cliente = buscarCliente(email);
            for(Pedido i : tienda.getPedidos()){
                if(i.getCliente().equals(cliente)){
                    estadoPedido = envioPendiente(i);
                    if(estadoPedido == pendiente){
                        System.out.println(i);
                        contador = true;
                    }
                }
            }
        }
        if(!contador){
            vista.ceroPedidos();
        }
    }

    public int comprobarOpcion(int eleccion, int inicio, int fin){
        while(eleccion < inicio || eleccion > fin){
            eleccion = vista.intInvalido();
        }
        return eleccion;
    }
}