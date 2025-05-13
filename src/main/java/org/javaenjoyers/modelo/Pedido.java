package org.javaenjoyers.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_pedido")
    private int numPedido;

    @ManyToOne
    @JoinColumn(name = "email_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cod_articulo", nullable = false)
    private Articulo articulo;

    private int cantidad;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    public Pedido() {}

    public Pedido(Articulo articulo, int cantidad, Cliente cliente, LocalDateTime fechaHora, int numPedido) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.numPedido = numPedido;
    }

    public Pedido(Articulo articulo, int cantidad, Cliente cliente) {
        this.articulo = articulo;
        this.cantidad = cantidad;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    @Override
    public String toString() {
        return "\nPEDIDO #" + numPedido +
                "\nCliente: " + cliente +
                "\nArtículo: " + articulo +
                "\nCantidad: " + cantidad +
                "\nFecha y hora: " + fechaHora + "\n";
    }
}
