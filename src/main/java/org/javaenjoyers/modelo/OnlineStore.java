package org.javaenjoyers.modelo;

public class OnlineStore {

    private String nif;
    private Modelo modelo;

    public OnlineStore(String nif) {
        this.nif = nif;
        this.modelo = new Modelo();
    }

//    public void agregarCliente(Cliente cliente){
//        modelo.agregarCliente(cliente);
//    }
//
//    public void mostrarClientes(){
//        modelo.mostrarClientes().forEach(System.out::println);
//    }
//
//    public void eliminarCliente(Cliente cliente){
//        modelo.eliminarCliente(cliente);
//    }
//
//    public void agregarArticulo(Articulo articulo){
//        modelo.agregarArticulo(articulo);
//    }
//
//    public void mostrarArticulos(){
//        modelo.mostrarArticulos().forEach(System.out::println);
//    }
//
//    public void eliminarArticulo(Articulo articulo){
//        modelo.eliminarArticulo(articulo);
//    }
//
//    public void agregarPedido(Pedido pedido){
//        modelo.agregarPedido(pedido);
//    }
//
//    public void mostrarPedidos(){
//        modelo.mostrarPedidos().forEach(System.out::println);
//    }
//
//    public void eliminarPedido(Pedido pedido){
//        modelo.eliminarPedido(pedido);
//    }
}