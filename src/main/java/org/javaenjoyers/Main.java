package org.javaenjoyers;

import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.DAO.ClienteDAO;
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
            conexion = Utilidad.establecerConexion(herramientas);
            //OnlineStore tienda = new OnlineStore("63014520P");

            VistaClientes vistaCli = new VistaClientes(herramientas);
            VistaArticulos vistaArt = new VistaArticulos(herramientas);
            VistaPedidos vistaPed = new VistaPedidos(herramientas);
            ClienteDAO cliDAO = new ClienteDAO(conexion, herramientas);
            ControladorCliente contrCli = new ControladorCliente(cliDAO, herramientas, vistaCli);
            ArticuloDAO artDAO = new ArticuloDAO(conexion, herramientas);
            ControladorArticulo contrArt = new ControladorArticulo(artDAO, herramientas, vistaArt);
            PedidoDAO pedDAO = new PedidoDAO(conexion, herramientas);
            ControladorPedido contrPed = new ControladorPedido(contrCli, herramientas, pedDAO, vistaPed);
            Controlador controlador = new Controlador(contrArt, contrCli, contrPed, herramientas, vista);

            herramientas.enviarMensaje(0, "Conectado a la BD con éxito");

            //controlador.datosIniciales(tienda);
            controlador.inicio();
        }catch(SQLException e){
            herramientas.enviarMensaje(2, null);
        }finally{
            if(conexion != null){
                conexion.close();
                herramientas.enviarMensaje(0, "\n\nConexión cerrada. Ciao!\n\n");
            }
        }
    }
}
