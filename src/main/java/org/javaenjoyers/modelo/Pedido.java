package org.javaenjoyers.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int numPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_nif", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "articulo_codigo", nullable = false)
    private Articulo articulo;

    private int cantidad;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    private boolean pendiente;

    public Pedido() {}

    public Pedido(Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora, boolean pendiente) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
        this.pendiente = pendiente;
    }

    public Pedido(Articulo articulo, int cantidad, Cliente cliente) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.cliente = cliente;
        this.fechaHora = LocalDateTime.now();
        this.pendiente = true;
    }

    public int getNumPedido() { return numPedido; }
    public void setNumPedido(int numPedido) { this.numPedido = numPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public boolean isPendiente() { return pendiente; }
    public void setPendiente(boolean pendiente) { this.pendiente = pendiente; }

    @Override
    public String toString() {
        return "\nPEDIDO #" + numPedido +
                "\nCliente: " + cliente +
                "\nArtículo: " + articulo +
                "\nCantidad: " + cantidad +
                "\nFecha y hora: " + fechaHora +
                "\nPendiente: " + pendiente + "\n";
    }
}
