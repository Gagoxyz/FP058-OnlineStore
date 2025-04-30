package org.javaenjoyers.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.javaenjoyers.dao.PedidoDAO;
import org.javaenjoyers.modelos.*;
import org.javaenjoyers.utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;

public class JPAPedidoDAO implements PedidoDAO {

    private EntityManager entityManager;

    public JPAPedidoDAO() {
        this.entityManager = Utilidad.obtenerEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insertar(Pedido p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(p);  // JPA persist para insertar el objeto Pedido
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void modificar(Pedido p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(p);  // JPA merge para actualizar el objeto Pedido
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void eliminar(Pedido p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(p) ? p : entityManager.merge(p));  // JPA remove para eliminar el Pedido
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Pedido> obtenerDatos() {
        // JPQL query to get all Pedido records
        return entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    @Override
    public Pedido obtener(Integer id) {
        return entityManager.find(Pedido.class, id);  // JPA find to get a Pedido by its ID (num_pedido)
    }

    // Método para obtener Cliente por email (ahora con JPA)
    private Cliente obtenerCliente(String email) {
        return entityManager.find(Cliente.class, email);  // Utiliza JPA para obtener el cliente por su email
    }

    // Método para obtener Articulo por codigoProducto (ahora con JPA)
    private Articulo obtenerArticulo(String codigoProducto) {
        return entityManager.find(Articulo.class, codigoProducto);  // Utiliza JPA para obtener el artículo por su código de producto
    }

    public Pedido obtenerPedidoPorNumPedido(int numPedido) {
        return entityManager.find(Pedido.class, numPedido);  // Obtener Pedido usando JPA
    }

    public List<Pedido> obtenerPedidosPorCliente(String email) {
        // JPQL query to get all Pedido for a given Cliente
        Query query = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.cliente.email = :email", Pedido.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

    // Procedimiento almacenado para la obtención de pedidos pendientes de envío de un cliente
    public List<Pedido> obtenerPedidosPendientesEnvio(Cliente cliente) {
        String sql = "{CALL obtener_pedidos_pendientes_envio(?)}";  // Procedimiento almacenado

        List<Pedido> pedidos = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(sql, Pedido.class);  // Usamos query nativo con procedimiento almacenado
            query.setParameter(1, cliente.getEmail());
            pedidos = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los pedidos pendientes de envío", e);
        }
        return pedidos;
    }

    // Procedimiento almacenado para la obtención de los pedidos enviados de un cliente
    public List<Pedido> obtenerPedidosEnviados(Cliente cliente) {
        String sql = "{CALL obtener_pedidos_enviados(?)}";  // Procedimiento almacenado

        List<Pedido> pedidos = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(sql, Pedido.class);  // Usamos query nativo con procedimiento almacenado
            query.setParameter(1, cliente.getEmail());
            pedidos = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los pedidos enviados", e);
        }
        return pedidos;
    }
}
