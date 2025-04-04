package org.javaenjoyers.modelos;

import org.javaenjoyers.dao.ClienteDAO;
import org.javaenjoyers.dao.FactoryDAO;
import java.util.List;

public class ClienteModelo {

    private final ClienteDAO clienteDAO;

    public ClienteModelo() {
        clienteDAO = FactoryDAO.getClienteDAO();
    }

    public List<Cliente> obtenerClientes(){
        return clienteDAO.obtenerDatos();
    }

    public void agregarCliente(Cliente cliente){
        clienteDAO.insertar(cliente);
    }

    public List<Estandar> obtenerClientesEstandar(){
        return clienteDAO.obtenerDatos().stream().filter(cliente -> cliente instanceof Estandar)
                .map(cliente -> (Estandar) cliente).toList();
    }

    public List<Premium> obtenerClientesPremium(){
        return clienteDAO.obtenerDatos().stream().filter(cliente -> cliente instanceof Premium)
                .map(cliente -> (Premium) cliente).toList();
    }
}
