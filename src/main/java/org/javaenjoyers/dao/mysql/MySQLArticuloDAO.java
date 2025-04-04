package org.javaenjoyers.dao.mysql;

import org.javaenjoyers.dao.ArticuloDAO;
import org.javaenjoyers.modelos.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLArticuloDAO implements ArticuloDAO {

    private static final String INSERT = "INSERT INTO Articulos(codigo_producto, descripcion, precio_venta, gastos_envio, tiempo_preparacion_envio) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Articulos SET descripcion = ?, precio_venta = ?, gastos_envio = ?, tiempo_preparacion_envio = ? WHERE codigo_producto = ?";
    private static final String DELETE = "DELETE FROM Articulos WHERE codigo_producto = ?";
    private static final String GETALL = "SELECT codigo_producto, descripcion, precio_venta, gastos_envio, tiempo_preparacion_envio FROM Articulos";
    private static final String GETONE = "SELECT codigo_producto, descripcion, precio_venta, gastos_envio, tiempo_preparacion_envio FROM Articulos WHERE codigo_producto = ?";

    private final Connection conexion;

    public MySQLArticuloDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Articulo a) {
        try (PreparedStatement stat = conexion.prepareStatement(INSERT)) {
            stat.setString(1, a.getCodigoProducto());
            stat.setString(2, a.getDescripcion());
            stat.setFloat(3, a.getPrecioVenta());
            stat.setFloat(4, a.getGastosEnvio());
            stat.setInt(5, a.getTiempoPrepEnvio());
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar artículo", e);
        }
    }

    @Override
    public void modificar(Articulo a) {
        try (PreparedStatement stat = conexion.prepareStatement(UPDATE)) {
            stat.setString(1, a.getDescripcion());
            stat.setFloat(2, a.getPrecioVenta());
            stat.setFloat(3, a.getGastosEnvio());
            stat.setInt(4, a.getTiempoPrepEnvio());
            stat.setString(5, a.getCodigoProducto());
            if (stat.executeUpdate() == 0) {
                throw new RuntimeException("No se pudo actualizar el artículo: " + a.getCodigoProducto());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar artículo", e);
        }
    }

    @Override
    public void eliminar(Articulo a) {
        try (PreparedStatement stat = conexion.prepareStatement(DELETE)) {
            stat.setString(1, a.getCodigoProducto());
            if (stat.executeUpdate() == 0) {
                throw new RuntimeException("No se pudo eliminar el artículo: " + a.getCodigoProducto());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar artículo", e);
        }
    }

    @Override
    public List<Articulo> obtenerDatos() {
        List<Articulo> articulos = new ArrayList<>();
        try (PreparedStatement stat = conexion.prepareStatement(GETALL);
             ResultSet rs = stat.executeQuery()) {
            while (rs.next()) {
                articulos.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener lista de artículos", e);
        }
        return articulos;
    }

    @Override
    public Articulo obtener(String codigoProducto) {
        Articulo articulo = null;
        try (PreparedStatement stat = conexion.prepareStatement(GETONE)) {
            stat.setString(1, codigoProducto);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    articulo = convertir(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener artículo con código: " + codigoProducto, e);
        }
        return articulo;
    }

    private Articulo convertir(ResultSet rs) throws SQLException {
        String codigoProducto = rs.getString("codigo_producto");
        String descripcion = rs.getString("descripcion");
        float precioVenta = rs.getFloat("precio_venta");
        float gastosEnvio = rs.getFloat("gastos_envio");
        int tiempoPrepEnvio = rs.getInt("tiempo_preparacion_envio");

        return new Articulo(codigoProducto, descripcion, precioVenta, gastosEnvio, tiempoPrepEnvio);
    }
}
