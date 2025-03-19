package org.javaenjoyers;

import org.javaenjoyers.controladores.Controlador;
import org.javaenjoyers.modelos.*;
import org.javaenjoyers.vistas.Vista;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // Creamos MVC (Modelo Vista Controlador)
        Modelo modelo = new Modelo ();
        Vista vista = new Vista(null);
        Controlador controlador = new Controlador(modelo, vista);
        vista = new Vista(controlador);

        // objetos de prueba
        Cliente cliente01 = new Estandar("milena@estandar.com", "Milena", "c/cliente estandar S/N", "123456789");
        Cliente cliente02 = new Premium("david@premium.com", "David", "c/cliente estandar S/N", "87654321X");
        Cliente cliente03 = new Premium("maria@premium.com", "Maria", "c/cliente estandar S/N", "87654321X");
        Cliente cliente04 = new Estandar("xavid@premium.com", "Xavi", "c/cliente estandar S/N", "87654321X");
        Articulo articulo01 = new Articulo("A001", "Artículo de prueba", 20.99f, 4.99f, 120);
        Pedido pedido01 = new Pedido(cliente01, articulo01, 1);
        Pedido pedido02 = new Pedido(cliente02, articulo01, 2, LocalDateTime.of(2025,03,19,10,30,00));
        Pedido pedido04 = new Pedido(cliente04, articulo01, 1);
        Pedido pedido03 = new Pedido(cliente03, articulo01,2,LocalDateTime.of(2025, 03, 19, 11, 05, 15));

        // añadimos los clientes, articulos y pedidos a nuestra tienda
        modelo.agregarCliente(cliente01);
        modelo.agregarCliente(cliente02);
        modelo.agregarCliente(cliente03);
        modelo.agregarCliente(cliente04);
        modelo.agregarArticulo(articulo01);
        modelo.agregarPedido(pedido01);
        modelo.agregarPedido(pedido02);
        modelo.agregarPedido(pedido03);
        modelo.agregarPedido(pedido04);

        vista.bienvenida();
        vista.menuPrincipal();
    }
}
