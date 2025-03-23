package JavaEnjoyers.modelo;

public class ClientePremium extends Cliente {

    private static final double DESCUENTO = 0.10; // 10% de descuento para clientes premium

    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    @Override
    public double calcularDescuento(double totalCompra) {
        return totalCompra * DESCUENTO;
    }
}