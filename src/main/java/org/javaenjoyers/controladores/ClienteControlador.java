package org.javaenjoyers.controladores;


import org.javaenjoyers.RepositorioGenerico;
import org.javaenjoyers.modelos.Cliente;

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
