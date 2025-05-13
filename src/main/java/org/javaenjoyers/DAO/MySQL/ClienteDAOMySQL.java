package org.javaenjoyers.DAO.MySQL;

import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;
import org.javaenjoyers.modelo.Premium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {
    Connection conexion;
    Herramientas herramientas;

    public ClienteDAOMySQL(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }

    public void insertarCliente(Cliente cliente){
        String sql;
        String tipo = "";
        try{
            sql = "INSERT INTO clientes (email, nombre, domicilio, nif, tipo)\n" +
                    "VALUES (?, ?, ?, ?, ?);";
            if(cliente instanceof Estandar){
                tipo = "E";
            }else if(cliente instanceof Premium){
                tipo = "P";
            }
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getDomicilio());
            stmt.setString(4, cliente.getNif());
            stmt.setString(5, tipo);
            stmt.execute();
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }

    public Cliente buscarCliente(String email){
        String sql;
        Cliente cliente = new Cliente();
        try{
            sql = "SELECT * FROM clientes WHERE email = ?;";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet resultado = stmt.executeQuery();
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

    public List<Cliente> mostrarClientes(int opcion){
        String sql = "";
        Statement sentencia;
        List<Cliente> listaClientes = new ArrayList<>();
        switch (opcion){
            case 1:
                sql = "SELECT * FROM clientes;";
                break;
            case 2:
                sql = "SELECT * FROM clientes WHERE tipo = \"E\";";
                break;
            case 3:
                sql = "SELECT * FROM clientes WHERE tipo = \"P\";";
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
                if(tipo.equals("E")){
                    tipo = "Estándar";
                }else{
                    tipo = "Premium";
                }
                if(tipo.equals("Estándar")){
                    Estandar cliEstandar = new Estandar();
                    cliEstandar.setEmail(email);
                    cliEstandar.setNombre(nombre);
                    cliEstandar.setNif(nif);
                    cliEstandar.setDomicilio(domicilio);
                    listaClientes.add(cliEstandar);
                }else{
                    Premium cliPremium = new Premium();
                    cliPremium.setEmail(email);
                    cliPremium.setNombre(nombre);
                    cliPremium.setNif(nif);
                    cliPremium.setDomicilio(domicilio);
                    listaClientes.add(cliPremium);
                }
//                herramientas.enviarMensaje(0, "\nEmail: "+ email + "\nNombre: " + nombre + "\nDomicilio: " +
//                        domicilio + "\nNIF: " + nif + "\nTipo: " + tipo + "\n\n--------------");

            }

        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return listaClientes;
    }
}
