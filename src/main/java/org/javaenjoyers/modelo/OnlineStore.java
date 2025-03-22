package org.javaenjoyers.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

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
}