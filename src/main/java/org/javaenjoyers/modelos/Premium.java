package org.javaenjoyers.modelos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("premium") // Coincide con el ENUM en la tabla MySQL
public class Premium extends Cliente {

    public static final double CUOTA = 30;
    public static final int DESCUENTO = 20;

    public Premium() {
        super();
    }

    public Premium(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    @Override
    public String getTipoCliente() {
        return "premium";
    }

    @Override
    public String toString() {
        return "\nCliente PREMIUM" +
                "\nNombre: " + getNombre() +
                "\nNIF: " + getNif() +
                "\nEmail: " + getEmail() +
                "\nDomicilio: " + getDomicilio() +
                "\nCuota: " + CUOTA + "€" +
                "\nDescuento: " + DESCUENTO + "%" +
                "\n";
    }
}
