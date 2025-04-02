package org.javaenjoyers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/test_prod3";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public DatabaseConnection() throws SQLException {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }


    public static void main(String[] args) {
        try(Connection conn = DatabaseConnection.getConnection()){
            System.out.println("Conexión exitosa a la base de Datos Mysql");
        }catch (SQLException e){
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }


}

