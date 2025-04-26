package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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

    /*public ClienteDAOJPA(EntityManager em) {
        this.em = em;
    }*/

    //@Override
    public void insertarCliente(Cliente cliente) {
        /*EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error insertando cliente: " + e.getMessage());

        }*/

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    //@Override
    /*public boolean eliminar(String nif) {
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
    }*/

    //@Override
    public Cliente buscarCliente(String email) {
        /*try {
            return em.find(Cliente.class, email);
        } catch (Exception e) {
            System.err.println("Error buscando cliente: " + e.getMessage());
            return null;
        }*/
        return em.find(Cliente.class, email);
    }

    @Override
    public void mostrarClientes(int opcion) {
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
        for(Cliente i : clientes){
            String email = i.getEmail();
            String nombre = i.getNombre();
            String domicilio = i.getDomicilio();
            String nif = i.getNif();
            String tipo;
            if(i instanceof Estandar){
                tipo = "Estándar";
            }else{
                tipo = "Premium";
            }
            herramientas.enviarMensaje(0, "\nEmail: "+ email + "\nNombre: " + nombre + "\nDomicilio: " +
                    domicilio + "\nNIF: " + nif + "\nTipo: " + tipo + "\n\n--------------");
        }
    }

    //@Override
    /*public List<Cliente> obtenerTodos() {
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error obteniendo clientes: " + e.getMessage());
            return null;
        }
    }*/


    //@Override
    /*public boolean actualizar(Cliente cliente) {
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
    }*/
}