package org.javaenjoyers.modelo;

import java.util.ArrayList;

public class OnlineStore {
    private String nif;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Articulo> articulos = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public OnlineStore(ArrayList<Articulo> articulos, ArrayList<Cliente> clientes, String nif, ArrayList<Pedido> pedidos) {
        this.articulos = articulos;
        this.clientes = clientes;
        this.nif = nif;
        this.pedidos = pedidos;
    }

    public OnlineStore(String nif) {
        this.nif = nif;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
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
        getClientes().add(cliente);
    }

    /*public void showClientes(){
        System.out.println(getClientes());
    }*/

    /*public void showEstandar(){
        for(Cliente i : getClientes()){
            if(i instanceof Estandar){
                System.out.println(i);
            }
        }
    }

    public void showPremium(){
        for(Cliente i : getClientes()){
            if(i instanceof Premium){
                System.out.println(i);
            }
        }
    }*/

    public void addArticulo(Articulo articulo){
        getArticulos().add(articulo);
    }

    /*public void showArticulos(){
        System.out.println(getArticulos());
    }*/

    public void addPedido(Pedido pedido){
        getPedidos().add(pedido);
    }

    public int buscarNumeroPedido(){
        return getPedidos().getLast().getNumPedido() + 1;
    }

    public void removePedido(Pedido pedido){
        getPedidos().removeIf(p -> p.getNumPedido() == pedido.getNumPedido());
    }

    /*public boolean showPedidos(boolean pendiente, Cliente cliente){
        boolean estadoPedido;
        boolean contador = false;
        if(cliente == null){
            for(Pedido i : getPedidos()){
                estadoPedido = i.envioPendiente();
                if(estadoPedido == pendiente){
                    System.out.println(i);
                    contador = true;
                }
            }
        }else{
            for(Pedido i : getPedidos()){
                if(i.getCliente().equals(cliente)){
                    estadoPedido = i.envioPendiente();
                    if(estadoPedido == pendiente){
                        System.out.println(i);
                        contador = true;
                    }
                }
            }
        }
        return contador;
    }*/
}