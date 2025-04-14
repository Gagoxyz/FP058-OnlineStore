package org.javaenjoyers.DAO;

import org.javaenjoyers.DAO.MySQL.DAOFactoryMySQL;
import org.javaenjoyers.controlador.Herramientas;

import java.sql.Connection;

public abstract class DAOFactory {
    public abstract ClienteDAO getClienteDAO();
    public abstract ArticuloDAO getArticuloDAO();
    public abstract PedidoDAO getPedidoDAO();

    public static DAOFactory getDAOFactory(String tipoBD, Connection conexion, Herramientas herramientas){
        switch (tipoBD.toLowerCase()){
            case "mysql":
                return new DAOFactoryMySQL(conexion, herramientas);
            default:
                throw new IllegalArgumentException("Tipo de base de datos no válido.");
        }
    }
}
