package org.javaenjoyers.modelos;

import java.time.LocalDateTime;

public class CargaDatos {

  public static void cargarDatos(Modelo modelo){
      // objetos de prueba
      Cliente cliente01 = new Premium("david@premium.com", "David", "Calle A 1", "12345678A");
      Cliente cliente02 = new Premium("maria@premium.com", "Maria", "Calle B 2", "12345678B");
      Cliente cliente03 = new Estandar("milena@estandar.com", "Milena", "Calle C 3", "12345678C");
      Cliente cliente04 = new Estandar("xavi@estandar.com", "Xavi", "Calle D 4", "12345678D");
      Articulo articulo01 = new Articulo("A001", "Teclado y ratón oficina", 20.99f, 4.99f, 120);
      Articulo articulo02 = new Articulo("A002", "Televisor LG 55 pulgadas", 899.99f, 10.99f, 360);
      Articulo articulo03 = new Articulo("A003", "ASUS TUF Gaming A15", 699f, 4.99f, 30);
      Pedido pedido01 = new Pedido(cliente02, articulo01, 2, LocalDateTime.of(2025,3,19,10,30,0));
      Pedido pedido02 = new Pedido(cliente03, articulo02,2,LocalDateTime.of(2025, 3, 19, 11, 5, 15));
      Pedido pedido03 = new Pedido(cliente04, articulo02, 1);
      Pedido pedido04 = new Pedido(cliente01, articulo01, 1);
      Pedido pedido05 = new Pedido(cliente01, articulo03, 1);

      // añadimos los elementos a sus respectivas listas
      modelo.agregarCliente(cliente01);
      modelo.agregarCliente(cliente02);
      modelo.agregarCliente(cliente03);
      modelo.agregarCliente(cliente04);
      modelo.agregarArticulo(articulo01);
      modelo.agregarArticulo(articulo02);
      modelo.agregarArticulo(articulo03);
      modelo.agregarPedido(pedido01);
      modelo.agregarPedido(pedido02);
      modelo.agregarPedido(pedido03);
      modelo.agregarPedido(pedido04);
      modelo.agregarPedido(pedido05);
  }
}
