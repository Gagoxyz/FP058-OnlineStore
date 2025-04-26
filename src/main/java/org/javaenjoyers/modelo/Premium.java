package org.javaenjoyers.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Premium extends Cliente{

    public static final double CUOTA = 30;
    public static final int DESCUENTO = 20;

    public Premium(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    public Premium() {
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
