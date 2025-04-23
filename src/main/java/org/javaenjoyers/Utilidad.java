package org.javaenjoyers;

import org.javaenjoyers.controlador.Herramientas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilidad {
    public static Connection establecerConexion(Herramientas herramientas){
        String url = "jdbc:mysql://localhost:3306/online_store";
        String usuario = "root";
        String contrasena = "Admin1234";
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        }catch(SQLException e){
            herramientas.enviarMensaje(0, "Error al conectar: "+e.getMessage());
        }
        return conexion;
    }
}
