package org.javaenjoyers.modelos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("estandar") // Coincide con el ENUM en la tabla MySQL
public class Estandar extends Cliente {

    public Estandar() {
        super();
    }

    public Estandar(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    @Override
    public String getTipoCliente() {
        return "estandar";
    }

    @Override
    public String toString() {
        return "\nCliente ESTANDAR" +
                "\nNombre: " + getNombre() +
                "\nNIF: " + getNif() +
                "\nEmail: " + getEmail() +
                "\nDomicilio: " + getDomicilio() + "\n";
    }
}
