package org.javaenjoyers.DAO;

import java.sql.Connection;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.DAO.MySQL.DAOFactoryMySQL;
import org.javaenjoyers.DAO.JPA.DAOFactoryJPA;
import org.javaenjoyers.controlador.Herramientas;

public abstract class DAOFactory {

    public DAOFactory() {
    }

    public abstract ClienteDAO getClienteDAO();

    public abstract ArticuloDAO getArticuloDAO();

    public abstract PedidoDAO getPedidoDAO();

    public static DAOFactory getDAOFactory(String tipoBD, Connection conexion, Herramientas herramientas) {
        switch (tipoBD.toLowerCase()) {
            case "mysql" -> {
                return new DAOFactoryMySQL(conexion, herramientas);
            }
            case "jpa" -> {
                EntityManager em = herramientas.getEntityManager(); // Asegúrate de tener este metodo
                return new DAOFactoryJPA(em, herramientas);
            }
            default -> throw new IllegalArgumentException("Tipo de base de datos no válido.");
        }
    }
}