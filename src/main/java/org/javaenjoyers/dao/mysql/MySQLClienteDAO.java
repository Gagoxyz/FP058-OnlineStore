package org.javaenjoyers.dao.mysql;

import org.javaenjoyers.dao.ClienteDAO;
import org.javaenjoyers.modelos.Cliente;
import org.javaenjoyers.modelos.Estandar;
import org.javaenjoyers.modelos.Premium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLClienteDAO implements ClienteDAO {

    final String INSERT = "INSERT INTO clientes(email, nombre, domicilio, nif, tipo_cliente) VALUES(?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE clientes SET nombre = ?, domicilio = ?, nif = ?, tipo_cliente = ? WHERE email = ?";
    final String DELETE = "DELETE FROM clientes WHERE email = ?";
    final String GETALL = "SELECT email, nombre, domicilio, nif, tipo_cliente FROM clientes";
    final String GETONE = "SELECT email, nombre, domicilio, nif, tipo_cliente FROM clientes WHERE email = ?";

    private final Connection conexion;

    public MySQLClienteDAO(Connection conexion){
        this.conexion = conexion;
    }

    @Override
    public void insertar(Cliente a) {
        try (PreparedStatement stat = conexion.prepareStatement(INSERT)) {
            stat.setString(1, a.getEmail());
            stat.setString(2, a.getNombre());
            stat.setString(3, a.getDomicilio());
            stat.setString(4, a.getNif());
            stat.setString(5, a.getTipoCliente());
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificar(Cliente a) {

        try (PreparedStatement stat = conexion.prepareStatement(UPDATE)) {
            stat.setString(1, a.getEmail());
            stat.setString(2, a.getNombre());
            stat.setString(3, a.getDomicilio());
            stat.setString(4, a.getNif());
            stat.setString(5, a.getTipoCliente());
            if (stat.executeUpdate() == 0) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Cliente a) {

        try (PreparedStatement stat = conexion.prepareStatement(DELETE)) {
            stat.setString(1, a.getEmail());
            if (stat.executeUpdate() == 0)
                throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //email, nombre, domicilio, nif, tipo_cliente
    private Cliente convertir(ResultSet rs) throws SQLException {
        String email = rs.getString("email");
        String nombre = rs.getString("nombre");
        String domicilio = rs.getString("domicilio");
        String nif = rs.getString("nif");
        String tipoCliente = rs.getString("tipo_cliente");

        Cliente cliente;
        if (tipoCliente.equalsIgnoreCase("estandar")){
            cliente = new Estandar(email, nombre, domicilio, nif);
        } else {
            cliente = new Premium(email, nombre, domicilio, nif);
        }
        return cliente;
    }

    @Override
    public List<Cliente> obtenerDatos() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            stat = conexion.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while(rs.next()){
                clientes.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException _) {
                }
            }
            if(stat != null){
                try {
                    stat.close();
                } catch (SQLException _) {
                }
            }
        }
        return clientes;
    }

    @Override
    public Cliente obtener(String email){
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setString(1, email);
            rs = stat.executeQuery();
            if(rs.next()){
                cliente = convertir(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el cliente con el email: " + email,e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stat != null) stat.close();
            } catch (SQLException _) {
                }
            }
        return cliente;
    }
}
