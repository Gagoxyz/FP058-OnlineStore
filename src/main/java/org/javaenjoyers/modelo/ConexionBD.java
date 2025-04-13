package org.javaenjoyers.modelo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class ConexionBD {
    public static Connection obtenerConexion() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/online_store";
        String usuario = "root";
        String contrasena = "1234";
        return DriverManager.getConnection(url, usuario, contrasena);
    }

}

