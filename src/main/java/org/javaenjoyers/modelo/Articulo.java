package org.javaenjoyers.modelo;

public class Articulo {

    private String codigoProducto;
    private String descripcion;
    private double precioVenta;
    private double gastosEnvio;
    private int tiempoPrepEnvio;

    public Articulo(String codigoProducto, String descripcion, double precioVenta, double gastosEnvio, int tiempoPrepEnvio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPrepEnvio = tiempoPrepEnvio;
    }

    public Articulo() {
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public int getTiempoPrepEnvio() {
        return tiempoPrepEnvio;
    }

    public void setTiempoPrepEnvio(int tiempoPrepEnvio) {
        this.tiempoPrepEnvio = tiempoPrepEnvio;
    }

    @Override
    public String toString() {
        return "\nARTÍCULO" +
                "\nCódigo de producto: " + codigoProducto +
                "\nDescripción: " + descripcion +
                "\nPrecio de venta: " + precioVenta +
                "\nGastos de envio: " + gastosEnvio +
                "\nTiempo de preparación: " + tiempoPrepEnvio + "\n";
    }
}
