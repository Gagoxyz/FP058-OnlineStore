package org.javaenjoyers.dao;

import org.javaenjoyers.dao.mysql.MySQLArticuloDAO;
import org.javaenjoyers.dao.mysql.MySQLClienteDAO;
import org.javaenjoyers.dao.mysql.MySQLPedidoDAO;
import org.javaenjoyers.utilidades.Utilidad;

import java.sql.Connection;
import java.sql.SQLException;

public class FactoryDAO {

    private static Connection getConnection() throws SQLException {
        return Utilidad.obtenerConexion();
    }

    public static ClienteDAO getClienteDAO(){
        try {
            return new MySQLClienteDAO(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArticuloDAO getArticuloDAO(){
        try {
            return new MySQLArticuloDAO(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PedidoDAO getPedidoDAO(){
        try {
            return new MySQLPedidoDAO(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
