package org.javaenjoyers.modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
@Table(name = "clientes")
public abstract class Cliente {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "domicilio", nullable = false)
    private String domicilio;

    @Column(name = "nif", nullable = false, unique = true)
    private String nif;

    // Relación con Pedido (asumiendo que 'Pedido' tiene la propiedad 'cliente')
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {} // Obligatorio para JPA

    public Cliente(String email, String nombre, String domicilio, String nif) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getNif() {
        return nif;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public abstract String getTipoCliente();

    @Override
    public String toString() {
        return "Cliente{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }
}
