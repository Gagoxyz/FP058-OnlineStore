package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.DAOFactory;
import org.javaenjoyers.DAO.PedidoDAO;

public class DAOFactoryJPA extends DAOFactory {

    private EntityManager entityManager;

    public DAOFactoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAOJPA(entityManager);
    }

    @Override
    public ArticuloDAO getArticuloDAO() {
        return new ArticuloDAOJPA(entityManager);
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new PedidoDAOJPA(entityManager);
    }
}