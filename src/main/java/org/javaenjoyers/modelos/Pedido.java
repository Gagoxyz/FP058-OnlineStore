package org.javaenjoyers.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private int numPedido; //autoincrementable (es el índice +1)
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHoraPedido;

    // constructor para número de pedido autoincrementable (será el índice + 1)
    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHoraPedido = LocalDateTime.now();
    }

    // constructor para crear objetos con diferentes fechas
    public Pedido(Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHoraPedido) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHoraPedido = fechaHoraPedido;
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

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public String toString() {
        return "\nNúmero de pedido: #" + numPedido +
                "\nCliente: " + cliente.getNombre() +
                "\nArtículo: " + articulo.getCodigoProducto() +
                "\nCantidad: " + cantidad +
                "\nFehca/hora del pedido: " + fechaHoraPedido.format(formato);
    }
}
