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

/**
 * Aplicación para la gestión de pedidos
 * @author JavaEnjoyers
 */
public class Main {
    public static void main(String[] args) {

        // Creamos MVC (Modelo Vista Controlador)
        Modelo modelo = new Modelo ();
        Vista vista = new Vista(null, null, null, null);
        ClienteVista clienteVista = new ClienteVista(null);
        ArticuloVista articuloVista = new ArticuloVista(null);
        PedidoVista pedidoVista = new PedidoVista(null);
        Controlador controlador = new Controlador(vista);
        ClienteControlador clienteControlador = new ClienteControlador(modelo, vista);
        ArticuloControlador articuloControlador = new ArticuloControlador(modelo, vista);
        PedidoControlador pedidoControlador = new PedidoControlador(modelo, vista, clienteControlador);
        vista.setControlador(controlador);
        vista.setClienteVista(clienteVista);
        vista.setArticuloVista(articuloVista);
        vista.setPedidoVista(pedidoVista);
        clienteVista.setClienteControlador(clienteControlador);
        articuloVista.setArticuloControlador(articuloControlador);
        pedidoVista.setPedidoControlador(pedidoControlador);

        // Realizamos la carga de datos (varios objetos de prueba)
        CargaDatos.cargarDatos(modelo);

        // Iniciamos el controlador
        controlador.bienvenida();
        controlador.inicio();
    }
}