package org.javaenjoyers.controlador;

import org.javaenjoyers.vista.*;

import java.sql.SQLException;

public class Controlador {
    private Vista vista;
    private Herramientas herramientas;
    private ControladorCliente contrCliente;
    private ControladorArticulo contrArticulo;
    private ControladorPedido contrPedido;

    public Controlador(ControladorArticulo contrArticulo, ControladorCliente contrCliente, ControladorPedido contrPedido, Herramientas herramientas, Vista vista) {
        this.contrArticulo = contrArticulo;
        this.contrCliente = contrCliente;
        this.contrPedido = contrPedido;
        this.herramientas = herramientas;
        this.vista = vista;
    }

    public void inicio() throws SQLException {
        vista.bienvenida();
        int opcion;
        int opcionMenu;
        boolean continuar = true;
        while(continuar){
            opcion = vista.menuPrincipal();
            opcionMenu = herramientas.comprobarOpcion(opcion, 0, 3);
            switch(opcionMenu){
                case 1:
                    contrCliente.menuClientes();
                    break;
                case 2:
                    contrArticulo.menuArticulos();
                    break;
                case 3:
                    contrPedido.menuPedidos();
                    break;
                case 0:
                    continuar = false;
                    break;
            }
        }
    }
}