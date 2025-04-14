package org.javaenjoyers.DAO;

import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;
import org.javaenjoyers.modelo.Premium;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {
    Connection conexion;
    Herramientas herramientas;

    public ClienteDAO(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }

    public void insertarCliente(Cliente cliente){
        String sql = "";
        Statement sentencia;
        if(cliente instanceof Estandar){
            sql = "INSERT INTO clientes (email, nombre, domicilio, nif, tipo)\n" +
                    "VALUES (\"" + cliente.getEmail()+"\", \"" + cliente.getNombre() + "\", \"" +
                    cliente.getDomicilio() + "\", \"" + cliente.getNif() + "\", \"E\");";
        }else if(cliente instanceof Premium){
            sql = "INSERT INTO clientes (email, nombre, domicilio, nif, tipo)\n" +
                    "VALUES (\"" + cliente.getEmail()+"\", \"" + cliente.getNombre() + "\", \"" +
                    cliente.getDomicilio() + "\", \"" + cliente.getNif() + "\", \"P\");";
        }
        try{
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }

    public Cliente buscarCliente(String email){
        String sql;
        Statement sentencia;
        Cliente cliente = new Cliente();
        try{
            sentencia = conexion.createStatement();
            sql = "SELECT * FROM clientes WHERE email = \"" + email + "\";";
            ResultSet resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                cliente.setEmail(resultado.getString("email"));
            }else{
                cliente = null;
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return cliente;
    }

    public void mostrarClientes(int opcion){
        String sql = "";
        Statement sentencia;
        switch (opcion){
            case 1:
                sql = "SELECT *\n" + "FROM clientes;";
                break;
            case 2:
                sql = "SELECT *\n" + "FROM clientes\n" + "WHERE tipo = \"E\";";
                break;
            case 3:
                sql = "SELECT *\n" + "FROM clientes\n" + "WHERE tipo = \"P\";";
                break;
        }
        try{
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                String email = resultado.getString("email");
                String nombre = resultado.getString("nombre");
                String domicilio = resultado.getString("domicilio");
                String nif = resultado.getString("nif");
                String tipo = resultado.getString("tipo");
                herramientas.enviarMensaje(0, "\nEmail: "+ email + "\nNombre: " + nombre + "\nDomicilio: " +
                        domicilio + "\nNIF: " + nif + "\nTipo: " + tipo + "\n\n--------------");
            }
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }
}
