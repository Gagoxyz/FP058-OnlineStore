package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.VistaClientes;

import java.util.ArrayList;

public class ControladorCliente {
    private VistaClientes vistaClientes;
    private Herramientas herramientas;
    private OnlineStore tienda;

    public ControladorCliente(Herramientas herramientas, OnlineStore tienda, VistaClientes vistaClientes) {
        this.herramientas = herramientas;
        this.tienda = tienda;
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

    public void addCliente(){
        String email = vistaClientes.emailCliente();
        boolean repetido = true;
        while(repetido){
            for(Cliente i : tienda.getClientes()){
                if(i.getEmail().equals(email)){
                    email = herramientas.repetirString(1);
                    repetido = true;
                    break;
                }
                repetido = false;
            }
        }
        Cliente clienteModelo = vistaClientes.infoCliente(email);
        vistaClientes.tipoCliente();
        int tipo = herramientas.errorIntEntrada();
        int tipoBueno = herramientas.comprobarOpcion(tipo, 1, 2);
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
        herramientas.enviarMensaje(1, null);
    }

    public void showTodos(){
        ArrayList<Cliente> clientes = tienda.getClientes();
        vistaClientes.showClientes(clientes);
    }

    public void showEstandar(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(Cliente i : tienda.getClientes()){
            if(i instanceof Estandar){
                clientes.add(i);
            }
        }
        vistaClientes.showClientes(clientes);
    }

    public void showPremium(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(Cliente i : tienda.getClientes()){
            if(i instanceof Premium){
                clientes.add(i);
            }
        }
        vistaClientes.showClientes(clientes);
    }
}
