package org.javaenjoyers.DAO.MySQL;
import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAOMySQL implements ArticuloDAO {
    Connection conexion;
    Herramientas herramientas;

    public ArticuloDAOMySQL(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }
    public void insertarArticulo(Articulo articulo){
        String sql;
        sql = "INSERT INTO articulos (cod_articulo, descripcion, precio_venta, gasto_envio, tiempo_preparacion)\n" +
                "VALUES (?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, articulo.getCodigoProducto());
            stmt.setString(2, articulo.getDescripcion());
            stmt.setDouble(3, articulo.getPrecioVenta());
            stmt.setDouble(4, articulo.getGastosEnvio());
            stmt.setInt(5, articulo.getTiempoPrepEnvio());
            stmt.execute();
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
    }

    public Articulo buscarArticulo(String codigo){
        String sql;
        Articulo articulo = new Articulo();
        try{
            sql = "SELECT * FROM articulos WHERE cod_articulo = ?;";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                articulo.setCodigoProducto(resultado.getString("cod_articulo"));
            }else{
                articulo = null;
            }
        }catch (SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return articulo;
    }

    public List<Articulo> mostrarArticulos(){
        String sql;
        Statement sentencia;
        List<Articulo> listaArticulos = new ArrayList<>();
        sql = "SELECT * FROM articulos;";
        try{
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                String codigo = resultado.getString("cod_articulo");
                String descripcion = resultado.getString("descripcion");
                double precio = resultado.getDouble("precio_venta");
                double gastos = resultado.getDouble("gasto_envio");
                int tiempo = resultado.getInt("tiempo_preparacion");

                Articulo articulo = new Articulo();
                articulo.setCodigoProducto(codigo);
                articulo.setDescripcion(descripcion);
                articulo.setPrecioVenta(precio);
                articulo.setGastosEnvio(gastos);
                articulo.setTiempoPrepEnvio(tiempo);
                listaArticulos.add(articulo);
            }
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }
        return listaArticulos;
    }
}
