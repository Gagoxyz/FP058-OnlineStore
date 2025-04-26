package org.javaenjoyers.DAO;

import org.javaenjoyers.modelo.Cliente;

import java.util.List;

public interface ClienteDAO {
    void insertarCliente(Cliente cliente);
    Cliente buscarCliente(String email);
    void mostrarClientes(int opcion);
}
