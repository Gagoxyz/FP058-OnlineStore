package org.javaenjoyers.controlador;

import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.JavaFX.VistaCliJavaFX;
import org.javaenjoyers.vista.JavaFX.VistaJavaFX;
import org.javaenjoyers.vista.VistaClientes;

import java.util.List;

public class ControladorCliente {
    private VistaClientes vistaClientes;
    private Herramientas herramientas;
    private ClienteDAO cliDAO;

    private VistaCliJavaFX vistaClientesJFX;
    private VistaJavaFX vistaJFX;
    private ControladorPedido contrPed;

    public ControladorCliente(ClienteDAO cliDAO, Herramientas herramientas, VistaClientes vistaClientes) {
        this.cliDAO = cliDAO;
        this.herramientas = herramientas;
        this.vistaClientes = vistaClientes;
    }

    public void setVistaClientesJFX(VistaCliJavaFX vistaClientesJFX) {
        this.vistaClientesJFX = vistaClientesJFX;
    }

    public void setVistaJFX(VistaJavaFX vistaJFX) {
        this.vistaJFX = vistaJFX;
    }

    public void setContrPed(ControladorPedido contrPed) {
        this.contrPed = contrPed;
    }

    public void menuClientes(){
        boolean seguir = true;
        int opcion;
        while(seguir){
            opcion = vistaClientes.menuClientes();
            opcion = herramientas.comprobarOpcion(opcion, 0, 4);
            switch (opcion){
                case 1:
                    addCliente();
                    break;
                case 2:
                    showTodos();
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
    }

    public Cliente addCliente(){
        String email = vistaClientes.emailCliente();
        boolean repetido = true;
        Cliente cliente;
        while(repetido){
            cliente = cliDAO.buscarCliente(email);
            if(cliente == null){
                break;
            }
            email = herramientas.repetirString(1);
        }
        Cliente clienteModelo = vistaClientes.infoCliente(email);
        vistaClientes.tipoCliente();
        int tipo = herramientas.errorIntEntrada();
        tipo = herramientas.comprobarOpcion(tipo, 1, 2);
        switch (tipo){
            case 1:
                Cliente clienteEstandar = new Estandar(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                cliDAO.insertarCliente(clienteEstandar);
                herramientas.enviarMensaje(1, null);
                return clienteEstandar;
            case 2:
                Cliente clientePremium = new Premium(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                cliDAO.insertarCliente(clientePremium);
                herramientas.enviarMensaje(1, null);
                return clientePremium;
        }
        herramientas.enviarMensaje(1, null);
        return clienteModelo;
    }

    public void showTodos(){
        List<Cliente> listaCli = cliDAO.mostrarClientes(1);
        vistaClientes.mostrarClientes(listaCli);
    }

    public void showEstandar(){
        List<Cliente> listaCli = cliDAO.mostrarClientes(2);
        vistaClientes.mostrarClientes(listaCli);
    }

    public void showPremium(){
        List<Cliente> listaCli = cliDAO.mostrarClientes(3);
        vistaClientes.mostrarClientes(listaCli);
    }

    //MÉTODOS PARA JavaFX
    public void menuClientesJFX(){
        vistaJFX.cambiarVista(vistaClientesJFX.menuClientes());
    }

    public void pedirEmail(boolean paraPedido){
        vistaJFX.cambiarVista(vistaClientesJFX.emailCliente(paraPedido));
    }

    public void comprobarEmail(String email, boolean paraPedido){
        Cliente cliente;
        cliente = cliDAO.buscarCliente(email);
        if(cliente != null){
            vistaJFX.cambiarVista(vistaClientesJFX.emailIncorrecto(paraPedido));
        }else{
            vistaJFX.cambiarVista(vistaClientesJFX.infoCliente(email, paraPedido));
        }
    }

    public void crearCliente(String email, String nombre, String nif, String domicilio, boolean estandar, boolean paraPedido){
        Cliente clienteModelo = new Cliente(email, nombre, domicilio, nif);
        Cliente clienteEstandar = new Estandar();
        Cliente clientePremium = new Premium();
        if(estandar){
            clienteEstandar = new Estandar(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
            cliDAO.insertarCliente(clienteEstandar);
        }else{
            clientePremium = new Premium(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
            cliDAO.insertarCliente(clientePremium);
        }
        if(paraPedido == false){
            vistaJFX.cambiarVista(vistaClientesJFX.clienteInsertado());
        }else{
            if(estandar){
                contrPed.clienteInsertado(clienteEstandar);
            }else{
                contrPed.clienteInsertado(clientePremium);
            }
        }
    }

    public void mostrarClientes(int opcion){
        List<Cliente> clientes = cliDAO.mostrarClientes(opcion);
        vistaJFX.cambiarVista(vistaClientesJFX.mostrarListaClientes(clientes, opcion));

    }
}