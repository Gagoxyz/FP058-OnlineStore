package JavaEnjoyers.modelo;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }


    public double calcularPrecioTotal() {
        double precioArticulo = articulo.getPrecio();  // Precio base del artículo
        double precioEnvio = articulo.getGastosEnvio();  // Gastos de envío

        // Si es un cliente premium, se aplica un 20% de descuento en los gastos de envío
        if (cliente instanceof ClientePremium) {
            precioEnvio *= 0.8;  // Descuento del 20%
        }

        // Calcular el total: (Precio artículo * cantidad) + gastos de envío
        return (precioArticulo * cantidad) + precioEnvio;
    }


    public int getNumeroPedido() {
        return numeroPedido;
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
}