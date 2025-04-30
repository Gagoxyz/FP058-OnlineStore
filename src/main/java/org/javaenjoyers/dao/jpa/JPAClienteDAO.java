package org.javaenjoyers.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.javaenjoyers.dao.ClienteDAO;
import org.javaenjoyers.modelos.Cliente;
import org.javaenjoyers.modelos.Estandar;
import org.javaenjoyers.modelos.Premium;
import org.javaenjoyers.utilidades.Utilidad;

import java.util.List;

public class JPAClienteDAO implements ClienteDAO {

    private EntityManager entityManager;

    public JPAClienteDAO() {
        this.entityManager = Utilidad.obtenerEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insertar(Cliente c) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(c);  // JPA "persist" para insertar el objeto
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void modificar(Cliente c) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(c);  // JPA "merge" para actualizar el objeto
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void eliminar(Cliente c) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(c) ? c : entityManager.merge(c));  // JPA "remove" para eliminar el objeto
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Cliente> obtenerDatos() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();  // JPQL query
    }

    @Override
    public Cliente obtener(String email) {
        return entityManager.find(Cliente.class, email);  // JPA "find" para obtener el objeto
    }
}
