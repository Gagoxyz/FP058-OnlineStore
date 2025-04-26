package org.javaenjoyers.DAO;

import org.javaenjoyers.modelo.Cliente;

public interface ClienteDAO {
    void insertarCliente(Cliente cliente);
    Cliente buscarCliente(String email);
    void mostrarClientes(int opcion);
}
