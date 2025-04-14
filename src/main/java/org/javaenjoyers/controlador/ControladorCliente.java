package org.javaenjoyers.controlador;

import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.VistaClientes;

public class ControladorCliente {
    private VistaClientes vistaClientes;
    private Herramientas herramientas;
    private ClienteDAO cliDAO;

    public ControladorCliente(ClienteDAO cliDAO, Herramientas herramientas, VistaClientes vistaClientes) {
        this.cliDAO = cliDAO;
        this.herramientas = herramientas;
        this.vistaClientes = vistaClientes;
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
        int tipoBueno = herramientas.comprobarOpcion(tipo, 1, 2);
        switch (tipoBueno){
            case 1:
                Cliente clienteEstandar = new Estandar(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                cliDAO.insertarCliente(clienteEstandar);
                return clienteEstandar;
            case 2:
                Cliente clientePremium = new Premium(clienteModelo.getEmail(), clienteModelo.getNombre(), clienteModelo.getDomicilio(), clienteModelo.getNif());
                cliDAO.insertarCliente(clientePremium);
                return clientePremium;
        }
        herramientas.enviarMensaje(1, null);
        clienteModelo = null;
        return clienteModelo;
    }

    public void showTodos(){
        cliDAO.mostrarClientes(1);
    }

    public void showEstandar(){
        cliDAO.mostrarClientes(2);
    }

    public void showPremium(){
        cliDAO.mostrarClientes(3);
    }
}
