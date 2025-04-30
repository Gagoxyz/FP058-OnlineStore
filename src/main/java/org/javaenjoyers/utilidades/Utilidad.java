package org.javaenjoyers.utilidades;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilidad {

    // Configuración JDBC
    private static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";


    // Conexión JDBC
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Configuración JPA
    private static final String PERSISTENCE_UNIT_NAME = "onlineStorePersistenceUnit";
    private static EntityManagerFactory emf;

    // Conexión JPA - Obtener el EntityManagerFactory
    public static EntityManagerFactory obtenerEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

    // Cerrar EntityManagerFactory
    public static void cerrarEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }
}
