package org.javaenjoyers.modelo;
import org.javaenjoyers.controlador.Herramientas;

import java.sql.*;
import java.time.LocalDateTime;


public class PedidoDAO {
    Connection conexion;
    Herramientas herramientas;

    public PedidoDAO(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }
    public void insertarPedido(Pedido pedido){
        String sql;
        Statement sentencia;
        try{
            sql = "INSERT INTO pedidos (email_cliente, cod_articulo, cantidad, fecha_hora)\n" +
                    "VALUES (\"" + pedido.getCliente().getEmail() + "\", \"" + pedido.getArticulo().getCodigoProducto() +
                    "\", " + pedido.getCantidad() + ", \"" + LocalDateTime.now() + "\");";
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
        boolean vuelta = true;
        try{
            sentencia = conexion.createStatement();
            while(vuelta){
                sql = "SELECT * FROM clientes WHERE email = \"" + email + "\";";
                ResultSet resultado = sentencia.executeQuery(sql);
                if(resultado.next()){
                    cliente.setEmail(resultado.getString("email"));
                    vuelta = false;
                }else{
                    email = herramientas.repetirString(2);
                    if(email.equals("0")){
                        cliente = null;
                        vuelta = false;
                    }
                }
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return cliente;
    }

    public Articulo buscarArticulo(String codigo){
        String sql;
        Statement sentencia;
        Articulo articulo = new Articulo();
        boolean vuelta = true;
        try{
            sentencia = conexion.createStatement();
            while(vuelta){
                sql = "SELECT * FROM articulos WHERE cod_articulo = \"" + codigo + "\";";
                ResultSet resultado = sentencia.executeQuery(sql);
                if(resultado.next()){
                    articulo.setCodigoProducto(resultado.getString("cod_articulo"));
                    vuelta = false;
                }else{
                    codigo = herramientas.repetirString(2);
                    if(codigo.equals("0")){
                        vuelta = false;
                    }
                }
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return articulo;
    }

    public Pedido buscarPedido (int numPedido){
        String sql;
        Statement sentencia;
        Pedido pedido = new Pedido();
        boolean vuelta = true;
        try{
            sentencia = conexion.createStatement();;
            while(vuelta){
                sql = "SELECT * FROM pedidos WHERE num_pedido = " + numPedido + ";";
                ResultSet resultado = sentencia.executeQuery(sql);
                if(resultado.next()){
                    pedido.setNumPedido(resultado.getInt("num_pedido"));
                    vuelta = false;
                }else{
                    numPedido = herramientas.repetirInt();
                    if(numPedido == 0){
                        pedido = null;
                        vuelta = false;
                    }
                }
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return pedido;
    }

    public boolean estadoPedido (int numero){
        String sql;
        Statement sentencia;
        boolean pendiente = true;
        sql = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                "WHERE num_pedido = " + numero + " && DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
        try{
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            if(!resultado.next()){
                pendiente = false;
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return pendiente;
    }

    public void eliminarPedido (Pedido pedido){
        String sql;
        Statement sentencia;
        try{
            sentencia = conexion.createStatement();
            sql = "DELETE FROM pedidos\n" + "WHERE num_pedido = " + pedido.getNumPedido() + ";";
            int resultado = sentencia.executeUpdate(sql);
            if(resultado > 0){
                herramientas.enviarMensaje(1, null);
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }

    public void mostrarPedidos(boolean pendiente, Cliente cliente){
        String sql;
        Statement sentencia;
        try{
            if(cliente == null){
                if(pendiente){
                    sql = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                            "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
                }else{
                    sql = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                            "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW();";
                }
            }else{
                if(pendiente){
                    sql = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                            "WHERE email_cliente = \"" + cliente.getEmail() + "\" AND DATE_ADD(fecha_hora," +
                            "INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
                }else{
                    sql = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                            "WHERE email_cliente = \"" + cliente.getEmail() + "\" AND DATE_ADD(fecha_hora," +
                            "INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW();";
                }
            }
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                while(resultado.next()){
                    int numPedido = resultado.getInt("num_pedido");
                    String email = resultado.getString("email_cliente");
                    String codArticulo = resultado.getString("cod_articulo");
                    int cantidad = resultado.getInt("cantidad");
                    Timestamp fechaHora = resultado.getTimestamp("fecha_hora");
                    herramientas.enviarMensaje(0, "\nNúmero de pedido: " + numPedido + "\nEmail del cliente: " +
                            email + "\nCódigo del artículo: " + codArticulo + "\nCantidad: " + cantidad + "\nFecha y hora: " +
                            fechaHora + "\n\n--------------");
                }
            }else{
                herramientas.enviarMensaje(0, "\nNo hay pedidos por mostrar.");
            }
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }
}
