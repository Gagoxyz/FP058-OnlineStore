package org.javaenjoyers.modelo;

public class OnlineStore {
    private String nif;
    private GestorDatos<Cliente> clientes = new GestorDatos<>();
    private GestorDatos<Articulo> articulos = new GestorDatos<>();
    private GestorDatos<Pedido> pedidos = new GestorDatos<>();

    public OnlineStore(GestorDatos<Articulo> art, GestorDatos<Cliente> cli, String nif, GestorDatos<Pedido> ped) {
        this.articulos = art;
        this.clientes = cli;
        this.nif = nif;
        this.pedidos = ped;
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

    @Override
    public String toString() {
        return "OnlineStore{" +
                "articulos=" + articulos +
                ", nif='" + nif + '\'' +
                ", clientes=" + clientes +
                ", pedidos=" + pedidos +
                '}';
    }

    public void addCliente(Cliente cliente){
        clientes.agregar(cliente);
    }

    public void addArticulo(Articulo articulo){
        articulos.agregar(articulo);
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