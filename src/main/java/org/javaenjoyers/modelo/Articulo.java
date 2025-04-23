package org.javaenjoyers.modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @Column(name = "codigo")
    private String codigoProducto;

    private String descripcion;
    private double precioVenta;
    private double gastosEnvio;
    private int tiempoPrepEnvio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Articulo() {}

    public Articulo(String codigoProducto, String descripcion, double precioVenta, double gastosEnvio, int tiempoPrepEnvio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPrepEnvio = tiempoPrepEnvio;
    }

    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getCodigo() { return getCodigoProducto(); }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public double getGastosEnvio() { return gastosEnvio; }
    public void setGastosEnvio(double gastosEnvio) { this.gastosEnvio = gastosEnvio; }

    public int getTiempoPrepEnvio() { return tiempoPrepEnvio; }
    public void setTiempoPrepEnvio(int tiempoPrepEnvio) { this.tiempoPrepEnvio = tiempoPrepEnvio; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    @Override
    public String toString() {
        return "\nARTÍCULO\nCódigo de producto: " + this.codigoProducto +
                "\nDescripción: " + this.descripcion +
                "\nPrecio de venta: " + this.precioVenta +
                "\nGastos de envio: " + this.gastosEnvio +
                "\nTiempo de preparación: " + this.tiempoPrepEnvio + "\n";
    }
}