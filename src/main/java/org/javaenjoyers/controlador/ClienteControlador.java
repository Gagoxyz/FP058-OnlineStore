package org.javaenjoyers.controlador;


import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;
import org.javaenjoyers.modelo.Premium;

import java.util.HashMap;
import java.util.Map;

public class ClienteControlador {
    private Map<String, Cliente> clientes;

    public ClienteControlador() {
        this.clientes = new HashMap<>();
    }

    public void agregarCliente(String email, String nombre, String domicilio, String nif, boolean esPremium) {
        if (clientes.containsKey(email)) {
            System.out.println("Error: Ya existe un cliente con ese email.");
            return;
        }

        Cliente cliente = esPremium ? new Premium(email, nombre, domicilio, nif)
                : new Estandar(email, nombre, domicilio, nif);
        clientes.put(email, cliente);
        System.out.println("Cliente agregado correctamente.");
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Listado de clientes:");
            clientes.values().forEach(System.out::println);
        }
    }

    public void mostrarClientesPorTipo(boolean premium) {
        clientes.values().stream()
                .filter(c -> (premium && c instanceof Premium) || (!premium && c instanceof Estandar))
                .forEach(System.out::println);
    }

    public Cliente buscarCliente(String email) {
        return clientes.get(email);
    }


}
