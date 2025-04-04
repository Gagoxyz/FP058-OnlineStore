package org.javaenjoyers.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilidad {

    private static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void cerrarConexion(Connection conexion){
        if (conexion != null){
            try {
                conexion.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
