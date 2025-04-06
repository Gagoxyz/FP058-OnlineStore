package org.javaenjoyers;

import org.javaenjoyers.controlador.*;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.Vista;
import org.javaenjoyers.vista.VistaArticulos;
import org.javaenjoyers.vista.VistaClientes;
import org.javaenjoyers.vista.VistaPedidos;

public class Main {
    public static void main(String[] args) {
        OnlineStore tienda = new OnlineStore("63014520P");
        Vista vista = new Vista();
        Herramientas herramientas = new Herramientas(vista);
        VistaClientes vistaCli = new VistaClientes(herramientas);
        VistaArticulos vistaArt = new VistaArticulos(herramientas);
        VistaPedidos vistaPed = new VistaPedidos(herramientas);
        ControladorCliente contrCli = new ControladorCliente(herramientas, tienda, vistaCli);
        ControladorArticulo contrArt = new ControladorArticulo(herramientas, tienda, vistaArt);
        ControladorPedido contrPed = new ControladorPedido(contrCli, herramientas, tienda, vistaPed);
        Controlador controlador = new Controlador(contrArt, contrCli, contrPed, herramientas, vista);

        controlador.datosIniciales(tienda);
        controlador.inicio();
    }
}
