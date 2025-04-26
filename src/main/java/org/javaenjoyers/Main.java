package org.javaenjoyers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

        // ✅ Creamos EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventJPA"); // Usa el nombre de tu unidad de persistencia
        EntityManager em = emf.createEntityManager();

        // ✅ Pasamos el EntityManager al constructor de Herramientas
        Herramientas herramientas = new Herramientas(vista, em);

        try {
            // ⚠️ Si aún usas JDBC para algo, conserva esta línea
            conexion = Utilidad.establecerConexion(herramientas);

            // ⚠️ Aquí puedes elegir la factory "jpa" si ya tienes una DAOFactoryJPA creada
            DAOFactory factory = DAOFactory.getDAOFactory("jpa", conexion, herramientas);
            ClienteDAO cliDAO = factory.getClienteDAO();
            ArticuloDAO artDAO = factory.getArticuloDAO();
            PedidoDAO pedDAO = factory.getPedidoDAO();

            // Vistas
            VistaClientes vistaCli = new VistaClientes(herramientas);
            VistaArticulos vistaArt = new VistaArticulos(herramientas);
            VistaPedidos vistaPed = new VistaPedidos(herramientas);

            // Controladores
            ControladorCliente contrCli = new ControladorCliente(cliDAO, herramientas, vistaCli);
            ControladorArticulo contrArt = new ControladorArticulo(artDAO, herramientas, vistaArt);
            ControladorPedido contrPed = new ControladorPedido(cliDAO, herramientas, pedDAO, vistaCli, vistaPed);
            Controlador controlador = new Controlador(contrArt, contrCli, contrPed, herramientas, vista);

            herramientas.enviarMensaje(0, "\n\nConectado a la BD con éxito");

            controlador.inicio();
        } catch (SQLException e) {
            herramientas.enviarMensaje(2, null);
        } finally {
            // JDBC: cerrar conexión si se usó
            if (conexion != null) {
                conexion.close();
                herramientas.enviarMensaje(0, "\n\nConexión cerrada. ¡Ciao!\n\n");
            }

            // JPA: cerrar el entity manager
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}