package org.javaenjoyers.dao.mysql;

import org.javaenjoyers.dao.PedidoDAO;
import org.javaenjoyers.modelos.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MySQLPedidoDAO implements PedidoDAO {
    final String INSERT = "INSERT INTO Pedidos(cliente_email, codigo_producto, cantidad, fecha_hora_pedido) VALUES(?, ?, ?, ?)";
    final String UPDATE = "UPDATE Pedidos SET cantidad = ?, fecha_hora_pedido = ? WHERE num_pedido = ?";
    final String DELETE = "DELETE FROM Pedidos WHERE num_pedido = ?";
    final String GETALL = "SELECT num_pedido, cliente_email, codigo_producto, cantidad, fecha_hora_pedido FROM Pedidos";
    final String GETONE = "SELECT num_pedido, cliente_email, codigo_producto, cantidad, fecha_hora_pedido FROM Pedidos WHERE num_pedido = ?";

    private final Connection conexion;

    public MySQLPedidoDAO(Connection conexion){
        this.conexion = conexion;
    }

    @Override
    public void insertar(Pedido p) {

        try (PreparedStatement stat = conexion.prepareStatement(INSERT)) {
            stat.setString(1, p.getCliente().getEmail());
            stat.setString(2, p.getArticulo().getCodigoProducto());
            stat.setInt(3, p.getCantidad());
            stat.setTimestamp(4, Timestamp.valueOf(p.getFechaHoraPedido()));

            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificar(Pedido p) {

        try (PreparedStatement stat = conexion.prepareStatement(UPDATE)) {
            stat.setInt(1, p.getCantidad());
            stat.setTimestamp(2, Timestamp.valueOf(p.getFechaHoraPedido()));
            stat.setInt(3, p.getNumPedido());

            if (stat.executeUpdate() == 0) {
                throw new RuntimeException("No se pudo actualizar el pedido.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Pedido p) {

        try (PreparedStatement stat = conexion.prepareStatement(DELETE)) {
            stat.setInt(1, p.getNumPedido());

            if (stat.executeUpdate() == 0) {
                throw new RuntimeException("No se pudo eliminar el pedido.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para convertir el ResultSet a un objeto Pedido
    private Pedido convertir(ResultSet rs) throws SQLException {
        int numPedido = rs.getInt("num_pedido");
        String clienteEmail = rs.getString("cliente_email");
        String codigoProducto = rs.getString("codigo_producto");
        int cantidad = rs.getInt("cantidad");
        LocalDateTime fechaHoraPedido = rs.getTimestamp("fecha_hora_pedido").toLocalDateTime();

        // Obtenemos el cliente a partir del email
        Cliente cliente = obtenerCliente(clienteEmail);

        // Obtenemos el artículo a partir del código de producto
        Articulo articulo = obtenerArticulo(codigoProducto);

        Pedido pedido = new Pedido(cliente, articulo, cantidad, fechaHoraPedido);
        pedido.setNumPedido(numPedido);
        return pedido;
    }

    @Override
    public List<Pedido> obtenerDatos() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();

        try {
            stat = conexion.prepareStatement(GETALL);
            rs = stat.executeQuery();

            while (rs.next()) {
                pedidos.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException _) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException _) {
                }
            }
        }
        return pedidos;
    }

    @Override
    public Pedido obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Pedido pedido;

        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();

            if (rs.next()) {
                pedido = convertir(rs);
            } else {
                throw new RuntimeException("Pedido no encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException _) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException _) {
                }
            }
        }
        return pedido;
    }

    // Método para obtener Cliente por email
    private Cliente obtenerCliente(String email) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente cliente;
        final String query = "SELECT email, nombre, domicilio, nif, tipo_cliente FROM Clientes WHERE email = ?";
        try {
            stat = conexion.prepareStatement(query);
            stat.setString(1, email);
            rs = stat.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String nif = rs.getString("nif");
                String tipoCliente = rs.getString("tipo_cliente");

                if (tipoCliente.equals("estandar")) {
                    cliente = new Estandar(email, nombre, domicilio, nif);
                } else {
                    cliente = new Premium(email, nombre, domicilio, nif);
                }
            } else {
                throw new RuntimeException("Cliente con email " + email + " no encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException _) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException _) {
                }
            }
        }
        return cliente;
    }

    // Método para obtener Articulo por codigoProducto
    private Articulo obtenerArticulo(String codigoProducto) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Articulo articulo;

        // Definimos la consulta para obtener un articulo por su codigo_producto
        final String GET_ARTICULO = "SELECT codigo_producto, descripcion, precio_venta, gastos_envio, tiempo_preparacion_envio FROM Articulos WHERE codigo_producto = ?";

        try {
            // Ejecutamos la consulta
            stat = conexion.prepareStatement(GET_ARTICULO);
            stat.setString(1, codigoProducto); // Establecemos el parámetro codigo_producto
            rs = stat.executeQuery();

            // Si el artículo existe en la base de datos, lo convertimos
            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                float precioVenta = rs.getFloat("precio_venta");
                float gastosEnvio = rs.getFloat("gastos_envio");
                int tiempoPrepEnvio = rs.getInt("tiempo_preparacion_envio");

                // Creamos el objeto Articulo
                articulo = new Articulo(codigoProducto, descripcion, precioVenta, gastosEnvio, tiempoPrepEnvio);
            } else {
                throw new RuntimeException("Articulo con código " + codigoProducto + " no encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el artículo: " + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException _) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException _) {
                }
            }
        }

        return articulo;
    }

    public Pedido obtenerPedidoPorNumPedido(int numPedido){
        String query = "SELECT * FROM Pedidos WHERE num_pedido = ?";
        try (PreparedStatement stat = conexion.prepareStatement(query)){
            stat.setInt(1, numPedido);
            try(ResultSet rs = stat.executeQuery()) {
                if(rs.next()){
                    return convertir(rs);
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> obtenerPedidosPorCliente(String email){
        String sp = "SELECT * FROM Pedidos WHERE cliente_email = ?"; //StoredProcedure creado en la base de datos
        try (PreparedStatement stat = conexion.prepareStatement(sp)){
            stat.setString(1, email);
            try (ResultSet rs = stat.executeQuery()){
                List<Pedido> pedidos = new ArrayList<>();
                while(rs.next()){
                    pedidos.add(convertir(rs));
                }
                return pedidos;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Procedimiento almacenado para la obtención de pedidos pendientes de envío de un cliente
    public List<Pedido> obtenerPedidosPendientesEnvio(Cliente cliente) {
        String sp = "{CALL obtener_pedidos_pendientes_envio(?)}"; //StoredProcedure creado en la base de datos
        List<Pedido> pedidos = new ArrayList<>();

        try (CallableStatement stmt = conexion.prepareCall(sp)) {
            stmt.setString(1, cliente.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(convertir(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los pedidos pendientes de envío", e);
        }

        return pedidos;
    }

    // Procedimiento almacenado para la obtención de los pedidos enviados de un cliente
    public List<Pedido> obtenerPedidosEnviados(Cliente cliente) {
        String sql = "{CALL obtener_pedidos_enviados(?)}";
        List<Pedido> pedidos = new ArrayList<>();

        try (CallableStatement stmt = conexion.prepareCall(sql)) {
            stmt.setString(1, cliente.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(convertir(rs));
                }
            }
        } catch (SQLException e) {

            throw new RuntimeException("Error al obtener los pedidos enviados", e);
        }

        return pedidos;
    }
}