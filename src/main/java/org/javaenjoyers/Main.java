package org.javaenjoyers;

import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.DAOFactory;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.*;
import org.javaenjoyers.vista.Vista;
import org.javaenjoyers.vista.VistaArticulos;
import org.javaenjoyers.vista.VistaClientes;
import org.javaenjoyers.vista.VistaPedidos;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexion = null;
        Vista vista = new Vista();
        Herramientas herramientas = new Herramientas(vista);
        try{
            conexion = Utilidad.establecerConexion(herramientas); //Establece la conexión con la BD

            DAOFactory factory = DAOFactory.getDAOFactory("mysql", conexion, herramientas); //Se le indica al Factory qué tipo de base de datos va a usarse
            ClienteDAO cliDAO = factory.getClienteDAO();
            ArticuloDAO artDAO = factory.getArticuloDAO();
            PedidoDAO pedDAO = factory.getPedidoDAO(); //Crea las clases DAO para la base de datos que se le indica

            VistaClientes vistaCli = new VistaClientes(herramientas);
            VistaArticulos vistaArt = new VistaArticulos(herramientas);
            VistaPedidos vistaPed = new VistaPedidos(herramientas);
            ControladorCliente contrCli = new ControladorCliente(cliDAO, herramientas, vistaCli);
            ControladorArticulo contrArt = new ControladorArticulo(artDAO, herramientas, vistaArt);
            ControladorPedido contrPed = new ControladorPedido(cliDAO, herramientas, pedDAO, vistaCli, vistaPed);
            Controlador controlador = new Controlador(contrArt, contrCli, contrPed, herramientas, vista);

            herramientas.enviarMensaje(0, "\n\nConectado a la BD con éxito");

            controlador.inicio();
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }finally{
            if(conexion != null){
                conexion.close();
                herramientas.enviarMensaje(0, "\n\nConexión cerrada. ¡Ciao!\n\n");
            }
        }
    }
}
