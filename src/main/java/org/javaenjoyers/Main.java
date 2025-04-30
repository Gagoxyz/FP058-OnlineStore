package org.javaenjoyers;

import org.javaenjoyers.controladores.ArticuloControlador;
import org.javaenjoyers.controladores.ClienteControlador;
import org.javaenjoyers.controladores.Controlador;
import org.javaenjoyers.controladores.PedidoControlador;
import org.javaenjoyers.modelos.*;
import org.javaenjoyers.vistas.ArticuloVista;
import org.javaenjoyers.vistas.ClienteVista;
import org.javaenjoyers.vistas.PedidoVista;
import org.javaenjoyers.vistas.Vista;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Aplicación para la gestión de pedidos
 * @author JavaEnjoyers
 */
public class Main {
    public static void main(String[] args) {

//        LogManager.getLogManager().reset();
//        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
//        globalLogger.setLevel(java.util.logging.Level.OFF);

        // Creamos MVC (Modelo Vista Controlador)
        ClienteModelo clienteModelo = new ClienteModelo();
        ArticuloModelo articuloModelo = new ArticuloModelo();
        PedidoModelo pedidoModelo = new PedidoModelo();
        Vista vista = new Vista(null, null, null, null);
        ClienteVista clienteVista = new ClienteVista(null);
        ArticuloVista articuloVista = new ArticuloVista(null);
        PedidoVista pedidoVista = new PedidoVista(null);
        Controlador controlador = new Controlador(vista);
        ClienteControlador clienteControlador = new ClienteControlador(clienteModelo, vista);
        ArticuloControlador articuloControlador = new ArticuloControlador(articuloModelo, vista);
        PedidoControlador pedidoControlador = new PedidoControlador(pedidoModelo, articuloModelo, vista, clienteControlador);
        vista.setControlador(controlador);
        vista.setClienteVista(clienteVista);
        vista.setArticuloVista(articuloVista);
        vista.setPedidoVista(pedidoVista);
        clienteVista.setClienteControlador(clienteControlador);
        articuloVista.setArticuloControlador(articuloControlador);
        pedidoVista.setPedidoControlador(pedidoControlador);

        // Realizamos la carga de datos (varios objetos de prueba)
        //CargaDatos.cargarDatos(clienteModelo, articuloModelo, pedidoModelo);

        // Iniciamos el controlador
        controlador.bienvenida();
        controlador.inicio();
    }
}