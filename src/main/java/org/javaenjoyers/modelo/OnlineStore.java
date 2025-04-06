package org.javaenjoyers.modelo;

import java.util.HashMap;
import java.util.Map;

public class OnlineStore {
    private String nif;
    private GestorDatos<Cliente> clientes = new GestorDatos<>();
    private GestorDatos<Articulo> articulos = new GestorDatos<>();
    private GestorDatos<Pedido> pedidos = new GestorDatos<>();
    private Map<String, Cliente> clientePorEmail = new HashMap<>();
    private Map<String, Articulo> articuloPorCodigo = new HashMap<>();

    public OnlineStore(Map<String, Articulo> articuloPorCodigo, GestorDatos<Articulo> articulos, Map<String, Cliente> clientePorEmail, GestorDatos<Cliente> clientes, String nif, GestorDatos<Pedido> pedidos) {
        this.articuloPorCodigo = articuloPorCodigo;
        this.articulos = articulos;
        this.clientePorEmail = clientePorEmail;
        this.clientes = clientes;
        this.nif = nif;
        this.pedidos = pedidos;
    }

    public OnlineStore(String nif) {
        this.nif = nif;
    }

    public GestorDatos<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(GestorDatos<Articulo> articulos) {
        this.articulos = articulos;
    }

    public GestorDatos<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(GestorDatos<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public GestorDatos<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(GestorDatos<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Map<String, Articulo> getArticuloPorCodigo() {
        return articuloPorCodigo;
    }

    public void setArticuloPorCodigo(Map<String, Articulo> articuloPorCodigo) {
        this.articuloPorCodigo = articuloPorCodigo;
    }

    public Map<String, Cliente> getClientePorEmail() {
        return clientePorEmail;
    }

    public void setClientePorEmail(Map<String, Cliente> clientePorEmail) {
        this.clientePorEmail = clientePorEmail;
    }

    @Override
    public String toString() {
        return "OnlineStore{" +
                "articuloPorCodigo=" + articuloPorCodigo +
                ", nif='" + nif + '\'' +
                ", clientes=" + clientes +
                ", articulos=" + articulos +
                ", pedidos=" + pedidos +
                ", clientePorEmail=" + clientePorEmail +
                '}';
    }

    public void addCliente(Cliente cliente){
        clientes.agregar(cliente);
        clientePorEmail.put(cliente.getEmail(), cliente);
    }

    public void addArticulo(Articulo articulo){
        articulos.agregar(articulo);
        articuloPorCodigo.put(articulo.getCodigoProducto(), articulo);
    }

    public void addPedido(Pedido pedido){
        pedidos.agregar(pedido);
    }

    public int buscarNumeroPedido(){
        return getPedidos().getLista().getLast().getNumPedido() + 1;
    }

    public void removePedido(Pedido pedido){
        pedidos.eliminar(pedido);
    }
}