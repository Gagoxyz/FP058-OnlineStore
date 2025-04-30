package org.javaenjoyers.modelos;

import jakarta.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_pedido")
    private int numPedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_email", referencedColumnName = "email", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_producto", nullable = false)
    private Articulo articulo;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_hora_pedido", nullable = false)
    private LocalDateTime fechaHoraPedido;

    public Pedido() {} // Constructor vacío requerido por JPA

    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHoraPedido = LocalDateTime.now();
    }

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

    public Articulo getArticulo() {
        return articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFechaHoraPedido() {
        return fechaHoraPedido;
    }

    public float precioPedido() {
        float precioArticulos = articulo.getPrecioVenta() * cantidad;
        float gastosEnvio = articulo.getGastosEnvio();

        if (cliente instanceof Premium) {
            gastosEnvio -= (gastosEnvio * Premium.DESCUENTO / 100f);
        }

        float precioPedido = precioArticulos + gastosEnvio;

        DecimalFormat df = new DecimalFormat("#.00");
        String precioFormateado = df.format(precioPedido).replace(',', '.');

        return Float.parseFloat(precioFormateado);
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "\nNúmero de pedido: #" + numPedido +
                "\nCliente: " + cliente.getNombre() +
                "\nArtículo: " + articulo.getCodigoProducto() +
                "\nCantidad: " + cantidad +
                "\nPrecio pedido: " + precioPedido() + "€" +
                "\nFecha/hora del pedido: " + fechaHoraPedido.format(formato) + "\n";
    }
}
