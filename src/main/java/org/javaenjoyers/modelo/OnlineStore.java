package org.javaenjoyers.modelo;

public class OnlineStore {
    private String nif;
    private GestorDatos<Cliente> cli = new GestorDatos<>();
    private GestorDatos<Articulo> art = new GestorDatos<>();
    private GestorDatos<Pedido> ped = new GestorDatos<>();

    public OnlineStore(GestorDatos<Articulo> art, GestorDatos<Cliente> cli, String nif, GestorDatos<Pedido> ped) {
        this.art = art;
        this.cli = cli;
        this.nif = nif;
        this.ped = ped;
    }

    public OnlineStore(String nif) {
        this.nif = nif;
    }

    public GestorDatos<Articulo> getArt() {
        return art;
    }

    public void setArt(GestorDatos<Articulo> art) {
        this.art = art;
    }

    public GestorDatos<Cliente> getCli() {
        return cli;
    }

    public void setCli(GestorDatos<Cliente> cli) {
        this.cli = cli;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public GestorDatos<Pedido> getPed() {
        return ped;
    }

    public void setPed(GestorDatos<Pedido> ped) {
        this.ped = ped;
    }

    @Override
    public String toString() {
        return "OnlineStore{" +
                "art=" + art +
                ", nif='" + nif + '\'' +
                ", cli=" + cli +
                ", ped=" + ped +
                '}';
    }

    public void addCliente(Cliente cliente){
        cli.agregar(cliente);
    }

    public void addArticulo(Articulo articulo){
        art.agregar(articulo);
    }

    public void addPedido(Pedido pedido){
        ped.agregar(pedido);
    }

    public int buscarNumeroPedido(){
        return getPed().getLista().getLast().getNumPedido() + 1;
    }

    public void removePedido(Pedido pedido){
        ped.eliminar(pedido);
    }
}