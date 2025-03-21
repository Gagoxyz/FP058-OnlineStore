package org.javaenjoyers.modelos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Modelo {

    private final GestorDatos<Cliente> gestorClientes;
    private final GestorDatos<Articulo> gestorArticulos;
    private final GestorDatos<Pedido> gestorPedidos;
    private final Map<String, Cliente> clientesPorEmail = new HashMap<>();
    private final Map<String, Articulo> articulosPorCodigo = new HashMap<>();

    public Modelo(){
        gestorClientes = new GestorDatos<>();
        gestorArticulos = new GestorDatos<>();
        gestorPedidos = new GestorDatos<>();
    }

    public Map<String, Cliente> getClientesPorEmail(){
        return clientesPorEmail;
    }

    public GestorDatos<Cliente> getClientes(){
        return gestorClientes;
    }

    public void agregarCliente(Cliente cliente){
        for (Cliente cli : getClientes().getLista()){
            if (Objects.equals(cli.getEmail(), cliente.getEmail())){
                return;
            }
        }
        gestorClientes.agregar(cliente);
        clientesPorEmail.put(cliente.getEmail(), cliente);
    }

    public void mostrarClientes(){
        gestorClientes.getLista().forEach(System.out::println);
    }

    public void mostrarClientesEstandar(){
        gestorClientes.getLista().stream().filter(cliente -> cliente instanceof Estandar).forEach(System.out::println);
    }

    public void mostrarClientesPremium(){
        gestorClientes.getLista().stream().filter(cliente -> cliente instanceof Premium).forEach(System.out::println);
    }

    public Cliente buscarClientePorEmail(String email){
        return clientesPorEmail.get(email);
    }

    public GestorDatos<Articulo> getArticulos(){
        return gestorArticulos;
    }

    public Map<String , Articulo> getArticuloPorCodigo(){
        return articulosPorCodigo;
    }

    public void agregarArticulo(Articulo articulo){
        gestorArticulos.agregar(articulo);
        articulosPorCodigo.put(articulo.getCodigoProducto(), articulo);
    }

    public void mostrarArticulos(){
        gestorArticulos.getLista().forEach(System.out::println);
    }

    public Articulo buscarArticuloPorCodigo(String codigo){
        return articulosPorCodigo.get(codigo);
    }

    public void agregarPedido(Pedido pedido){
        int numPedido = gestorPedidos.listSize() + 1;
        pedido.setNumPedido(numPedido);
        gestorPedidos.agregar(pedido);
    }

    public void mostrarPedidos(){
        gestorPedidos.getLista().forEach(System.out::println);
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

    public void eliminarPedido(int numPedido){
        gestorPedidos.getLista().stream().filter(pedido -> pedido.getNumPedido() == numPedido).findFirst().ifPresent(gestorPedidos::eliminarObjeto);
    }
}