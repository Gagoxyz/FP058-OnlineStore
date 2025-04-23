package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.util.List;

public class PedidoDAOJPA implements PedidoDAO {

    private final EntityManager em;

    public PedidoDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarPedido(Pedido pedido, boolean confirmar) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pedido);
            if (confirmar) {
                tx.commit();
            } else {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error insertando pedido: " + e.getMessage());
        }
    }

    @Override
    public Cliente buscarCliente(String email) {
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.correo = :email", Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("Error buscando cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Articulo buscarArticulo(String codigo) {
        try {
            return em.find(Articulo.class, codigo);
        } catch (Exception e) {
            System.err.println("Error buscando artículo: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Pedido buscarPedido(int numPedido) {
        return buscarPorId(numPedido);
    }

    @Override
    public boolean estadoPedido(int numero) {
        Pedido pedido = buscarPorId(numero);
        return pedido != null && pedido.isPendiente();
    }

    @Override
    public void eliminarPedido(Pedido pedido) {
        eliminar(pedido.getNumPedido()); // Cambiado 'getId()' por 'getNumPedido()'
    }

    @Override
    public void mostrarPedidos(boolean pendiente, Cliente cliente) {
        try {
            List<Pedido> pedidos = em.createQuery(
                            "SELECT p FROM Pedido p WHERE p.cliente = :cliente AND p.pendiente = :pendiente", Pedido.class)
                    .setParameter("cliente", cliente)
                    .setParameter("pendiente", pendiente)
                    .getResultList();
            for (Pedido p : pedidos) {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.err.println("Error mostrando pedidos: " + e.getMessage());
        }
    }

    //@Override
    public boolean eliminar(int numPedido) { // Cambiado 'id' por 'numPedido'
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido pedido = em.find(Pedido.class, numPedido); // Cambiado 'id' por 'numPedido'
            if (pedido != null) {
                em.remove(pedido);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error eliminando pedido: " + e.getMessage());
            return false;
        }
    }

    //@Override
    public Pedido buscarPorId(int numPedido) { // Cambiado 'id' por 'numPedido'
        try {
            return em.find(Pedido.class, numPedido); // Cambiado 'id' por 'numPedido'
        } catch (Exception e) {
            System.err.println("Error buscando pedido: " + e.getMessage());
            return null;
        }
    }

    //@Override
    public List<Pedido> obtenerTodos() {
        try {
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error obteniendo pedidos: " + e.getMessage());
            return List.of(); // lista vacía en lugar de null
        }
    }

    //@Override
    public boolean actualizar(Pedido pedido) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(pedido);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error actualizando pedido: " + e.getMessage());
            return false;
        }
    }
}