package org.javaenjoyers.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @Column(name = "codigo_producto")
    private String codigoProducto;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio_venta", nullable = false)
    private float precioVenta;

    @Column(name = "gastos_envio", nullable = false)
    private float gastosEnvio;

    @Column(name = "tiempo_preparacion_envio", nullable = false)
    private int tiempoPrepEnvio;

    // Constructor vacío requerido por JPA
    public Articulo() {}

    // Constructor para creación de nuevos artículos
    public Articulo(String codigoProducto, String descripcion, float precioVenta, float gastosEnvio, int tiempoPrepEnvio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPrepEnvio = tiempoPrepEnvio;
    }

    // Getters y setters
    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
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
                "\nTiempo preparación envío (en minutos): " + tiempoPrepEnvio + "\n";
    }
}
