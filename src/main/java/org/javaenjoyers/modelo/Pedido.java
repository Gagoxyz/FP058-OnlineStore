package org.javaenjoyers.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private int numPedido; //pendiente hacer autoincrementable
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHoraPedido;

    public Pedido(int numPedido, Cliente cliente, Articulo articulo, int cantidad) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHoraPedido = LocalDateTime.now();
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHoraPedido() {
        return fechaHoraPedido;
    }

    //Metodo para convertir la fecha y hora en algo más legible
    public String formatoFecha(LocalDateTime fechaOrginal){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFinal = fechaOrginal.format(formato);
        return fechaFinal;
    }

    @Override
    public String toString() {
        return "\nPEDIDO: " +
                "\nNúmero de pedido: " + numPedido +
                "\nCliente: " + cliente.getEmail() +
                "\nArtículo: " + articulo.getCodigoProducto() +
                "\nCantidad: " + cantidad +
                "\nFecha y hora del pedido: " + formatoFecha(fechaHoraPedido) + "\n";
    }
}
