package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.modelo.Cliente;

import java.util.List;

public class ClienteDAOJPA implements ClienteDAO {

    private EntityManager em;

    public ClienteDAOJPA(EntityManager em) {
        this.em = em;
    }

    //@Override
    public void insertarCliente(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error insertando cliente: " + e.getMessage());

        }
    }

    //@Override
    public boolean eliminar(String nif) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente cliente = em.find(Cliente.class, nif);
            if (cliente != null) {
                em.remove(cliente);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error eliminando cliente: " + e.getMessage());
            return false;
        }
    }

    //@Override
    public Cliente buscarCliente(String nif) {
        try {
            return em.find(Cliente.class, nif);
        } catch (Exception e) {
            System.err.println("Error buscando cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void mostrarClientes(int opcion) {

    }

    //@Override
    public List<Cliente> obtenerTodos() {
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error obteniendo clientes: " + e.getMessage());
            return null;
        }
    }


    //@Override
    public boolean actualizar(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error actualizando cliente: " + e.getMessage());
            return false;
        }
    }
}