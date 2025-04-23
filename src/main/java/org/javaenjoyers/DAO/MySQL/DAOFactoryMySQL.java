package org.javaenjoyers.DAO.MySQL;

import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.DAOFactory;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.Herramientas;

import java.sql.Connection;

public class DAOFactoryMySQL extends DAOFactory {
    Connection conexion;
    Herramientas herramientas;

    public DAOFactoryMySQL(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }

    @Override
    public ClienteDAO getClienteDAO(){
        return new ClienteDAOMySQL(conexion, herramientas);
    }

    @Override
    public ArticuloDAO getArticuloDAO(){
        return new ArticuloDAOMySQL(conexion, herramientas);
    }

    @Override
    public PedidoDAO getPedidoDAO(){
        return new PedidoDAOMySQL(conexion, herramientas);
    }
}
