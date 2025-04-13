package org.javaenjoyers.modelo;
import org.javaenjoyers.controlador.Herramientas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ArticuloDAO {
    Connection conexion;
    Herramientas herramientas;

    public ArticuloDAO(Connection conexion, Herramientas herramientas) {
        this.conexion = conexion;
        this.herramientas = herramientas;
    }
    public void insertarArticulo(Articulo articulo){
        String sql;
        Statement sentencia;
        sql = "INSERT INTO articulos (cod_articulo, descripcion, precio_venta, gasto_envio, tiempo_preparacion)\n" +
                "VALUES (\"" + articulo.getCodigoProducto() + "\", \"" + articulo.getDescripcion() + "\", " +
                articulo.getPrecioVenta() + ", " + articulo.getGastosEnvio() + ", " + articulo.getTiempoPrepEnvio() + ");";
        try{
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }

    }

    public Articulo buscarArticulo(String codigo){
        String sql;
        Statement sentencia;
        Articulo articulo = new Articulo();
        try{
            sentencia = conexion.createStatement();
            sql = "SELECT * FROM articulos WHERE cod_articulo = \"" + codigo + "\";";
            ResultSet resultado = sentencia.executeQuery(sql);
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

    public void mostrarArticulos(){
        String sql;
        Statement sentencia;
        sql = "SELECT *\n" + "FROM articulos;";
        try{
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                String codigo = resultado.getString("cod_articulo");
                String descripcion = resultado.getString("descripcion");
                double precio = resultado.getDouble("precio_venta");
                double gastos = resultado.getDouble("gasto_envio");
                String tiempo = resultado.getString("tiempo_preparacion");
                herramientas.enviarMensaje(0, "\nCódigo del artículo: " + codigo + "\nDescripción: " +
                        descripcion + "\nPrecio de venta: " + precio + " €\nGastos de envio: " + gastos +
                        " €\nTiempo de preparación: " + tiempo + " minutos\n\n--------------");
            }
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }

    }
}
