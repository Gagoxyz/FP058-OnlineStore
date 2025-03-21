package org.javaenjoyers.controlador;


import org.javaenjoyers.RepositorioGenerico;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;
import org.javaenjoyers.modelo.Premium;

import java.util.HashMap;
import java.util.Map;

public class ClienteControlador {
    private RepositorioGenerico<Cliente> clientes;

    public ClienteControlador() {
        this.clientes = new RepositorioGenerico<>();
    }

    public void agregarCliente(String email, Cliente cliente) {
        clientes.agregar(email, cliente);
    }

    public Cliente obtenerCliente(String email) {
        return clientes.obtener(email);
    }

    public void eliminarCliente(String email) {
        clientes.eliminar(email);
    }

    public void mostrarClientes() {
        clientes.mostrarTodos();
    }

}
