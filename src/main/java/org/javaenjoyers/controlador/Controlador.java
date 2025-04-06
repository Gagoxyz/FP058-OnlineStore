package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.*;

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

    public void datosIniciales(OnlineStore tienda){
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

        tienda.getClientes().agregar(cliente01);
        tienda.getClientes().agregar(cliente02);
        tienda.getClientes().agregar(cliente03);
        tienda.getClientes().agregar(cliente04);
        tienda.getArticulos().agregar(articulo01);
        tienda.getArticulos().agregar(articulo02);
        tienda.getPedidos().agregar(pedido01);
        tienda.getPedidos().agregar(pedido02);
        tienda.getPedidos().agregar(pedido03);
        tienda.getPedidos().agregar(pedido04);
        tienda.getPedidos().agregar(pedido05);
        cliente01.getPedidos().agregar(pedido01);
        cliente01.getPedidos().agregar(pedido02);
        cliente02.getPedidos().agregar(pedido03);
        cliente02.getPedidos().agregar(pedido04);
        cliente02.getPedidos().agregar(pedido05);
    }
    public void inicio(){
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