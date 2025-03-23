package org.javaenjoyers.modelos;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private static int contadorPedidos = 1; //contador de pedidos
    private int numPedido;
    private final Cliente cliente;
    private final Articulo articulo;
    private final int cantidad;
    private final LocalDateTime fechaHoraPedido;

    // constructor para número de pedido autoincrementable
    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.numPedido = contadorPedidos++;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHoraPedido = LocalDateTime.now();
    }

    // constructor para crear objetos con diferentes fechas
    public Pedido(Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHoraPedido) {
        this.numPedido = contadorPedidos++;
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

    public LocalDateTime getFechaHoraPedido() {
        return fechaHoraPedido;
    }

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Calculará el precio del pedido aplicando los descuentos necesarios
    public float precioPedido(){
        float precioArtiuclos = articulo.getPrecioVenta() * cantidad;
        float gastosEnvio = articulo.getGastosEnvio();
        float precioPedido = precioArtiuclos + gastosEnvio;

        if (cliente instanceof Premium){
            gastosEnvio -= (gastosEnvio * Premium.DESCUENTO / 100f);
            precioPedido = precioArtiuclos + gastosEnvio;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        String precioFormateado = df.format(precioPedido);
        precioFormateado = precioFormateado.replace(',', '.');

        return Float.parseFloat(precioFormateado);
    }

    @Override
    public String toString() {
        return "\nNúmero de pedido: #" + numPedido +
                "\nCliente: " + cliente.getNombre() +
                "\nArtículo: " + articulo.getCodigoProducto() +
                "\nCantidad: " + cantidad +
                "\nPrecio pedido: " + precioPedido() + "€" +
                "\nFehca/hora del pedido: " + fechaHoraPedido.format(formato);
    }
}
