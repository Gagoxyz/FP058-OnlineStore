package org.onlinestore;

import java.util.ArrayList;
import java.util.List;

public class OnlineStore {

    private String nif;
    private ArrayList<Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;

    public OnlineStore(String nif) {
        this.nif = nif;
        this.clientes = new ArrayList<>();
        this.articulos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "OnlineStore{" +
                "nif='" + nif + '\'' +
                ", clientes=" + clientes +
                ", articulos=" + articulos +
                ", pedidos=" + pedidos +
                '}';
    }
}
