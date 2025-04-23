package org.javaenjoyers.DAO.MySQL;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.*;

import java.sql.*;
import java.time.LocalDateTime;

public class PedidoDAOMySQL implements PedidoDAO {
    Connection conexion;
    Herramientas herramientas;

    public PedidoDAOMySQL(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }

    public void insertarPedido(Pedido pedido, boolean clienteNuevo){
        Cliente cli = pedido.getCliente();
        String tipo = "";
        if(clienteNuevo){
            try{
                conexion.setAutoCommit(false);
                String sql1 = "INSERT INTO clientes (email, nombre, domicilio, nif, tipo)\n" +
                        "VALUES (?, ?, ?, ?, ?);";
                if(pedido.getCliente() instanceof Estandar){
                    tipo = "E";
                }else if(pedido.getCliente() instanceof Premium){
                    tipo = "P";
                }
                PreparedStatement stmt1 = conexion.prepareStatement(sql1);
                stmt1.setString(1, cli.getEmail());
                stmt1.setString(2, cli.getNombre());
                stmt1.setString(3, cli.getDomicilio());
                stmt1.setString(4, cli.getNif());
                stmt1.setString(5, tipo);
                String sql2 = "INSERT INTO pedidos (email_cliente, cod_articulo, cantidad, fecha_hora)\n" +
                        "VALUES (?, ?, ?, ?);";
                PreparedStatement stmt2 = conexion.prepareStatement(sql2);
                stmt2.setString(1, cli.getEmail());
                stmt2.setString(2, pedido.getArticulo().getCodigoProducto());
                stmt2.setInt(3, pedido.getCantidad());
                stmt2.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                stmt1.execute();
                stmt2.execute();
                conexion.commit();
                conexion.setAutoCommit(true);
                herramientas.enviarMensaje(1, null);
            }catch(SQLException e){
                try{
                    if(conexion != null){
                        conexion.rollback();
                    }
                }catch (SQLException rollbackEx){
                    rollbackEx.printStackTrace();
                }
            }
        }else{
            String sql = "INSERT INTO pedidos (email_cliente, cod_articulo, cantidad, fecha_hora)\n" +
                    "VALUES (?, ?, ?, ?);";
            try{
                PreparedStatement stmt = conexion.prepareStatement(sql);
                stmt.setString(1, pedido.getCliente().getEmail());
                stmt.setString(2, pedido.getArticulo().getCodigoProducto());
                stmt.setInt(3, pedido.getCantidad());
                stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                stmt.execute();
            }catch(SQLException e){
                herramientas.enviarMensaje(2, null);
            }
            herramientas.enviarMensaje(1, null);
        }
    }

    public Cliente buscarCliente(String email){
        String sql;
        PreparedStatement stmt;
        Cliente cliente = new Cliente();
        boolean vuelta = true;
        try{
            while(vuelta){
                sql = "SELECT * FROM clientes WHERE email = ?;";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, email);
                ResultSet resultado = stmt.executeQuery();
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
        PreparedStatement stmt;
        Articulo articulo = new Articulo();
        boolean vuelta = true;
        try{
            while(vuelta){
                sql = "SELECT * FROM articulos WHERE cod_articulo = ?;";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(resultado.next()){
                    articulo.setCodigoProducto(resultado.getString("cod_articulo"));
                    vuelta = false;
                }else{
                    codigo = herramientas.repetirString(2);
                    if(codigo.equals("0")){
                        articulo = null;
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
        PreparedStatement stmt;
        Pedido pedido = new Pedido();
        boolean vuelta = true;
        try{
            while(vuelta){
                sql = "SELECT * FROM pedidos WHERE num_pedido = ?;";
                stmt = conexion.prepareStatement(sql);
                stmt.setInt(1, numPedido);
                ResultSet resultado = stmt.executeQuery();
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
        boolean pendiente = true;
        sql = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                "WHERE num_pedido = ? && DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, numero);
            ResultSet resultado = stmt.executeQuery();
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
        try{
            sql = "DELETE FROM pedidos WHERE num_pedido = ?;";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, pedido.getNumPedido());
            int resultado = stmt.executeUpdate();
            if(resultado > 0){
                herramientas.enviarMensaje(1, null);
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }

    public void mostrarPedidos(boolean pendiente, Cliente cliente){
        String sql1;
        String sql2;
        ResultSet resultado;
        try{
            if(cliente == null){
                if(pendiente){
                    sql1 = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                            "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
                }else{
                    sql1 = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.cod_articulo\n" +
                            "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW();";
                }
                Statement stmt = conexion.createStatement();
                resultado = stmt.executeQuery(sql1);
            }else{
                if(pendiente){
                    sql2 = "CALL obtener_pedidos_pendientes (?);";
                }else{
                    sql2 = "CALL obtener_pedidos_enviados (?);";
                }
                PreparedStatement stmt = conexion.prepareStatement(sql2);
                stmt.setString(1, cliente.getEmail());
                resultado = stmt.executeQuery();
            }
            if(resultado.next()){
                do{
                    int numPedido = resultado.getInt("num_pedido");
                    String email = resultado.getString("email_cliente");
                    String codArticulo = resultado.getString("cod_articulo");
                    int cantidad = resultado.getInt("cantidad");
                    Timestamp fechaHora = resultado.getTimestamp("fecha_hora");
                    herramientas.enviarMensaje(0, "\nNúmero de pedido: " + numPedido + "\nEmail del cliente: " +
                            email + "\nCódigo del artículo: " + codArticulo + "\nCantidad: " + cantidad + "\nFecha y hora: " +
                            fechaHora + "\n\n--------------");
                }while(resultado.next());
            }else{
                herramientas.enviarMensaje(0, "\nNo hay pedidos por mostrar.");
            }
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }
}
