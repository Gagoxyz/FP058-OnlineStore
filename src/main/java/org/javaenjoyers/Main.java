package org.javaenjoyers;

import org.javaenjoyers.controlador.*;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.Vista;
import org.javaenjoyers.vista.VistaArticulos;
import org.javaenjoyers.vista.VistaClientes;
import org.javaenjoyers.vista.VistaPedidos;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // nueva tienda para JavaEnjoyers
        OnlineStore tienda = new OnlineStore("63014520P");
        Vista vista = new Vista();
        Herramientas herramientas = new Herramientas(vista);
        VistaClientes vistaCli = new VistaClientes(herramientas);
        VistaArticulos vistaArt = new VistaArticulos(herramientas);
        VistaPedidos vistaPed = new VistaPedidos(herramientas);
        ControladorCliente contrCli = new ControladorCliente(herramientas, tienda, vistaCli);
        ControladorArticulo contrArt = new ControladorArticulo(herramientas, tienda, vistaArt);
        ControladorPedido contrPed = new ControladorPedido(contrCli, herramientas, tienda, vistaPed);
        Controlador controlador = new Controlador(contrArt, contrCli, contrPed, herramientas, tienda, vista);

        // clases de prueba
        Cliente cliente01 = new Estandar("manuela@estandar.com", "Manuela", "c/cliente estandar S/N", "123456789");
        Cliente cliente02 = new Premium("david@premium.com", "David", "c/cliente estandar S/N", "87654321X");
        Cliente cliente03 = new Premium("irene@premium.com", "Irene", "c/cliente de al lado S/N", "78420365F");
        Cliente cliente04 = new Estandar("sofia@estandar.com", "Sofía", "c/que maja soy S/N", "89624542S");
        Articulo articulo01 = new Articulo("A001", "Estante de madera de pino", 20.99f, 4.99f, 0);
        Articulo articulo02 = new Articulo("A002", "Silla de despacho", 86.35f, 8.36f, 5);
        Pedido pedido01 = new Pedido(1, cliente01, articulo01, 1);
        Pedido pedido02 = new Pedido(2, cliente01, articulo02, 2);
        Pedido pedido03 = new Pedido(3, cliente02, articulo01, 3);
        Pedido pedido04 = new Pedido(4, cliente02, articulo02, 2);
        Pedido pedido05 = new Pedido(5, cliente02, articulo02, 3);

        tienda.getClientes().add(cliente01);
        tienda.getClientes().add(cliente02);
        tienda.getClientes().add(cliente03);
        tienda.getClientes().add(cliente04);
        tienda.getArticulos().add(articulo01);
        tienda.getArticulos().add(articulo02);
        tienda.getPedidos().add(pedido01);
        tienda.getPedidos().add(pedido02);
        tienda.getPedidos().add(pedido03);
        tienda.getPedidos().add(pedido04);
        tienda.getPedidos().add(pedido05);

        controlador.inicio();
    }
}
