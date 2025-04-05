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
}