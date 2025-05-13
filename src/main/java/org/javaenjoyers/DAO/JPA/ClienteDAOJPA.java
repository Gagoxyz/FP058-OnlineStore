package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;

import java.util.List;

public class ClienteDAOJPA implements ClienteDAO {

    private EntityManager em;
    Herramientas herramientas;

    public ClienteDAOJPA(EntityManager em, Herramientas herramientas) {
        this.em = em;
        this.herramientas = herramientas;
    }

    @Override
    public void insertarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    @Override
    public Cliente buscarCliente(String email) {
        return em.find(Cliente.class, email);
    }

    @Override
    public List<Cliente> mostrarClientes(int opcion) {
        List<Cliente> clientes;
        String consulta ="";
        switch (opcion){
            case 1:
                consulta = "SELECT c FROM Cliente c";
                break;
            case 2:
                consulta = "SELECT c FROM Cliente c WHERE TYPE(c) = Estandar";
                break;
            case 3:
                consulta = "SELECT c FROM Cliente c WHERE TYPE(c) = Premium";
                break;
        }
        clientes = em.createQuery(consulta, Cliente.class).getResultList();
        return clientes;
    }
}