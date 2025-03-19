package org.javaenjoyers.modelos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    public void agregarArticulo(Articulo articulo){
        gestorArticulos.agregar(articulo);
    }

    public void mostrarArticulos(){
        gestorArticulos.listar().forEach(System.out::println);
    }

    public void agregarPedido(Pedido pedido){
        int numPedido = gestorPedidos.listSize() + 1;
        pedido.setNumPedido(numPedido);
        gestorPedidos.agregar(pedido);
    }

    public void mostrarPedidos(){
        gestorPedidos.listar().forEach(System.out::println);
    }

    public GestorDatos<Pedido> getPedidos(){
        return gestorPedidos;
    }

    public boolean verificarTiempoPedido(Pedido pedidoCliente){
        boolean excesoTiempo = false;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        long tiempoPedido = pedidoCliente.getFechaHoraPedido().until(fechaHoraActual, ChronoUnit.SECONDS);

        if(tiempoPedido > pedidoCliente.getArticulo().getTiempoPrepEnvio()){
            excesoTiempo = true;
        }

        return excesoTiempo;
    }

    public void eliminarPedido(int index){
        gestorPedidos.eliminarElemento(index);
    }
}