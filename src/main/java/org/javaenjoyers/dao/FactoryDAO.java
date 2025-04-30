package org.javaenjoyers.dao;

import org.javaenjoyers.dao.jpa.JPAArticuloDAO;
import org.javaenjoyers.dao.jpa.JPAClienteDAO;
import org.javaenjoyers.dao.jpa.JPAPedidoDAO;
import org.javaenjoyers.dao.mysql.MySQLArticuloDAO;
import org.javaenjoyers.dao.mysql.MySQLClienteDAO;
import org.javaenjoyers.dao.mysql.MySQLPedidoDAO;
import org.javaenjoyers.utilidades.Utilidad;

import java.sql.Connection;
import java.sql.SQLException;

public class FactoryDAO {

    //implementamos patrón FactoryDAO con las nuevas clases JPA
    public static ClienteDAO getClienteDAO(){
        return new JPAClienteDAO();
    }

    //implementamos patrón FactoryDAO con las nuevas clases JPA
    public static ArticuloDAO getArticuloDAO(){
        return new JPAArticuloDAO();
    }

    //implementamos patrón FactoryDAO con las nuevas clases JPA
    public static PedidoDAO getPedidoDAO(){
        return new JPAPedidoDAO();
    }

//    private static Connection getConnection() throws SQLException {
//        return Utilidad.obtenerConexion();
//    }
//
//    public static ClienteDAO getClienteDAO(){
//        try {
//            return new MySQLClienteDAO(getConnection());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static ArticuloDAO getArticuloDAO(){
//        try {
//            return new MySQLArticuloDAO(getConnection());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static PedidoDAO getPedidoDAO(){
//        try {
//            return new MySQLPedidoDAO(getConnection());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
