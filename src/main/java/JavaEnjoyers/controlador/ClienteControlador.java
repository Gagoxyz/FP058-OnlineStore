package JavaEnjoyers.controlador;

import JavaEnjoyers.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteControlador {
    private List<Cliente> listaClientes;

    public ClienteControlador() {
        this.listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }


    public Cliente obtenerClientePorEmail(String email) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;  // Si no se encuentra el cliente, devolver null
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        }
    }

    public boolean eliminarCliente(String email) {
        Cliente cliente = obtenerClientePorEmail(email);
        if (cliente != null) {
            listaClientes.remove(cliente);
            return true;
        }
        return false;
    }
}