package org.javaenjoyers.modelos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Clase Modelo, registrará todos los datos de la aplicación
 */

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

    /**
     * Mapa que asociará cada email con su cliente
     * @return Devuelve un Map de Cliente donde el email será la clave
     */
    public Map<String, Cliente> getClientesPorEmail(){
        return clientesPorEmail;
    }

    /**
     * Lista con el tipo de dato que contiene el GestorDatos
     * @return Devuelve la lista de Clientes
     */
    public GestorDatos<Cliente> getClientes(){
        return gestorClientes;
    }

    /**
     * Añade un cliente al gestor de clientes y al Map de clientes por email
     * @param cliente Objeto que se añadirá a la lista de clientes y al mapa
     */
    public void agregarCliente(Cliente cliente){
        for (Cliente cli : getClientes().getLista()){
            if (Objects.equals(cli.getEmail(), cliente.getEmail())){
                return;
            }
        }
        gestorClientes.agregar(cliente);
        clientesPorEmail.put(cliente.getEmail(), cliente);
    }

    /**
     * Muestra los clientes registrados en el gestor de clientes
     */
    public void mostrarClientes(){
        gestorClientes.getLista().forEach(System.out::println);
    }

    /**
     * Muestra los clientes "Estandar" registrados en el gestor de clientes
     */
    public void mostrarClientesEstandar(){
        gestorClientes.getLista().stream().filter(cliente -> cliente instanceof Estandar).forEach(System.out::println);
    }

    /**
     * Muestra los clientes "Premium" registrados en el gestor de clientes
     */
    public void mostrarClientesPremium(){
        gestorClientes.getLista().stream().filter(cliente -> cliente instanceof Premium).forEach(System.out::println);
    }

    /**
     * Buscará un cliente registrado que coincida con el email indicado
     * @param email Email indicado por el usuario para buscar un cliente
     * @return Objeto tipo Cliente asociado al email indicado
     */
    public Cliente buscarClientePorEmail(String email){
        return clientesPorEmail.get(email);
    }

    /**
     * Lista con el tipo de dato que contiene el GestorDatos
     * @return Devuelve la lista de Artículos
     */
    public GestorDatos<Articulo> getArticulos(){
        return gestorArticulos;
    }

    /**
     * Mapa que asociará cada código de producto a su artículo
     * @return Devuelve un Map de Articulo donde el codigoProducto será la clave
     */
    public Map<String , Articulo> getArticulosPorCodigo(){
        return articulosPorCodigo;
    }

    /**
     * Añade un artículo al gestor de artículos y al Map de artículos por código
     * @param articulo Objeto que se añadirá a la lista de artículos y al mapa
     */
    public void agregarArticulo(Articulo articulo){
        gestorArticulos.agregar(articulo);
        articulosPorCodigo.put(articulo.getCodigoProducto(), articulo);
    }

    /**
     * Muestra los artículos registrados en el gestor de artículos
     */
    public void mostrarArticulos(){
        gestorArticulos.getLista().forEach(System.out::println);
    }

    /**
     * Añade un pedido al gestor de pedidos y a los pedidos del Cliente que reliza el pedido
     * @param pedido Objeto que se añadirá a la lista de pedidos de ambas clases
     */
    public void agregarPedido(Pedido pedido){
        int numPedido = gestorPedidos.listSize() + 1;
        pedido.setNumPedido(numPedido);
        gestorPedidos.agregar(pedido);

        Cliente cliente = pedido.getCliente();
        if(cliente != null){
            cliente.getPedidos().add(pedido);
        }
    }

    /**
     * Mostrará los pedidos registrados en el gestor de pedidos
     */
    public void mostrarPedidos(){
        gestorPedidos.getLista().forEach(System.out::println);
    }

    /**
     * Lista con el tipo de dato que contiene el GestorDatos
     * @return Devuelve la lista de Pedidos
     */
    public GestorDatos<Pedido> getPedidos(){
        return gestorPedidos;
    }

    /**
     * Verifica si el tiempo de preparación del pedido supera la fecha y hora actual
     * @param pedidoCliente Pedido donde se realizará la verificación
     * @return Devuelve un boolean verificando si excede el tiempo
     */
    public boolean verificarTiempoPedido(Pedido pedidoCliente){
        boolean excesoTiempo = false;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        long tiempoPedido = pedidoCliente.getFechaHoraPedido().until(fechaHoraActual, ChronoUnit.MINUTES);

        if(tiempoPedido > pedidoCliente.getArticulo().getTiempoPrepEnvio()){
            excesoTiempo = true;
        }

        return excesoTiempo;
    }

    /**
     * Eliminará un pedido indicado por el usuario, también se eliminará del List de pedidos del cliente indicado
     * @param numPedido Número de pedido que se eliminará
     */
    public void eliminarPedido(int numPedido){
        gestorPedidos.getLista().stream().filter(pedido -> pedido.getNumPedido() == numPedido).findFirst().ifPresent(pedido -> {
            gestorPedidos.eliminarObjeto(pedido);
            Cliente cliente = pedido.getCliente();
            if (cliente != null){
                cliente.getPedidos().remove(pedido);
            }
        });
    }
}
