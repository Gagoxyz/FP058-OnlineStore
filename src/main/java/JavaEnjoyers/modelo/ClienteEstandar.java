package JavaEnjoyers.modelo;

public class ClienteEstandar extends Cliente {

    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    @Override
    public double calcularDescuento(double totalCompra) {
        return 0; // Los clientes estándar no tienen descuento
    }
}