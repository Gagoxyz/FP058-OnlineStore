package org.javaenjoyers.modelos;

public class Articulo {

    private final String codigoProducto;
    private final String descripcion;
    private final float precioVenta;
    private final float gastosEnvio;
    private final int tiempoPrepEnvio;

    public Articulo(String codigoProducto, String descripcion, float precioVenta, float gastosEnvio, int tiempoPrepEnvio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPrepEnvio = tiempoPrepEnvio;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public float getGastosEnvio() {
        return gastosEnvio;
    }

    public int getTiempoPrepEnvio() {
        return tiempoPrepEnvio;
    }

    @Override
    public String toString() {
        return "\nCódigo del producto: " + codigoProducto +
                "\nDescripción: " + descripcion +
                "\nPrecio de venta: " + precioVenta + "€" +
                "\nGastos de envío: " + gastosEnvio + "€" +
                "\nTiempo preparación envío (en segundos): " + tiempoPrepEnvio;
    }
}
