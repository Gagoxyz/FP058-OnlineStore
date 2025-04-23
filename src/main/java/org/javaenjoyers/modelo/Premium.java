package org.javaenjoyers.modelo;

public class Premium extends Cliente{

    public static final double CUOTA = 30;
    public static final int DESCUENTO = 20;

    public Premium(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }


    @Override
    public String toString() {
        return "\nCliente PREMIUM" +
                "\nNombre: " + getNombre() +
                "\nNIF: " + getNif() +
                "\nEmail: " + getEmail() +
                "\nDomicilio: " + getDomicilio() +
                "\nCuota: " + Premium.CUOTA + "€" +
                "\nDescuento: " + Premium.DESCUENTO + "%" +
                "\n";
    }
}
