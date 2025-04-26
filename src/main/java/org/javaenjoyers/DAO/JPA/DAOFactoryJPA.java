package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.DAOFactory;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.Herramientas;

public class DAOFactoryJPA extends DAOFactory {

    private EntityManager entityManager;
    Herramientas herramientas;

    public DAOFactoryJPA(EntityManager entityManager, Herramientas herramientas) {
        this.entityManager = entityManager;
        this.herramientas = herramientas;
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAOJPA(entityManager, herramientas);
    }

    @Override
    public ArticuloDAO getArticuloDAO() {
        return new ArticuloDAOJPA(entityManager, herramientas);
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new PedidoDAOJPA(entityManager, herramientas);
    }
}