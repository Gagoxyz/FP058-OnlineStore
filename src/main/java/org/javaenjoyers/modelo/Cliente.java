package org.javaenjoyers.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String email;
    private String nombre;
    private String domicilio;
    private String nif;
    //private List<Pedido> pedidos;
    private GestorDatos<Pedido> pedidos = new GestorDatos<>();

    public Cliente(String email, String nombre, String domicilio, String nif) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.pedidos = new GestorDatos<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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
        return "Cliente{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }

}
