package org.javaenjoyers.modelo;

import java.util.List;

public class Modelo {

    private GestorDatos<Cliente> gestorClientes;
    private GestorDatos<Articulo> gestorArticulos;
    private GestorDatos<Pedido> gestorPedidos;

    public Modelo(){
        gestorClientes = new GestorDatos<>();
        gestorArticulos = new GestorDatos<>();
        gestorPedidos = new GestorDatos<>();
    }

    public void agregarCliente(Cliente cliente){
        gestorClientes.agregar(cliente);
    }

    public void mostrarClientes(){
        gestorClientes.listar().forEach(System.out::println);
    }

    public void mostrarClientesEstandar(){
        gestorClientes.listar().stream().filter(cliente -> cliente instanceof Estandar).forEach(System.out::println);
    }

    public void mostrarClientesPremium(){
        gestorClientes.listar().stream().filter(cliente -> cliente instanceof Premium).forEach(System.out::println);
    }

    public void eliminarCliente(Cliente cliente){
        gestorClientes.eliminar(cliente);
    }

    public void agregarArticulo(Articulo articulo){
        gestorArticulos.agregar(articulo);
    }

    public void mostrarArticulos(){
        gestorArticulos.listar().forEach(System.out::println);
    }

    public void eliminarArticulo(Articulo articulo){
        gestorArticulos.eliminar(articulo);
    }

    public void agregarPedido(Pedido pedido){
        gestorPedidos.agregar(pedido);
    }

    public void mostrarPedidos(){
        gestorPedidos.listar().forEach(System.out::println);
    }

    public void eliminarPedido(Pedido pedido){
        gestorPedidos.eliminar(pedido);
    }
}
