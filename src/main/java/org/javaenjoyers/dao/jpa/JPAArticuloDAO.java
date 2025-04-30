package org.javaenjoyers.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.javaenjoyers.dao.ArticuloDAO;
import org.javaenjoyers.modelos.Articulo;
import org.javaenjoyers.utilidades.Utilidad;

import java.util.List;

public class JPAArticuloDAO implements ArticuloDAO {

    private EntityManager entityManager;

    public JPAArticuloDAO() {
        this.entityManager = Utilidad.obtenerEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insertar(Articulo a) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(a);  // JPA "persist" para insertar el objeto
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void modificar(Articulo a) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(a);  // JPA "merge" para actualizar el objeto
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void eliminar(Articulo a) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(a) ? a : entityManager.merge(a));  // JPA "remove" para eliminar el objeto
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Articulo> obtenerDatos() {
        return entityManager.createQuery("SELECT a FROM Articulo a", Articulo.class).getResultList();  // HQL (JPA) query
    }

    @Override
    public Articulo obtener(String codigoProducto) {
        return entityManager.find(Articulo.class, codigoProducto);  // JPA "find" para obtener el objeto
    }
}
