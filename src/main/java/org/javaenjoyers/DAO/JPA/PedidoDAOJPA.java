package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PedidoDAOJPA implements PedidoDAO {

    private final EntityManager em;
    public Herramientas herramientas;

    public PedidoDAOJPA(EntityManager em, Herramientas herramientas) {
        this.em = em;
        this.herramientas = herramientas;
    }

    @Override
    public void insertarPedido(Pedido pedido, boolean clienteNuevo) {
        /*EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pedido);
            if (confirmar) {
                tx.commit();
            } else {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error insertando pedido: " + e.getMessage());
        }*/
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        pedido.setFechaHora(LocalDateTime.now());
        if(clienteNuevo){
            Cliente cli = pedido.getCliente();
            em.getTransaction().begin();
            em.persist(cli);
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        herramientas.enviarMensaje(1, null);
    }

    @Override
    public Cliente buscarCliente(String email) {
        /*try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.correo = :email", Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("Error buscando cliente: " + e.getMessage());
            return null;
        }*/
        boolean vuelta = true;
        Cliente cliente = new Cliente();
        while(vuelta){
            cliente = em.find(Cliente.class, email);
            if(cliente == null){
                email = herramientas.repetirString(2);
                if(email.equals("0")){
                    cliente = null;
                    vuelta = false;
                }
            }else{
                vuelta = false;
            }
        }
        return cliente;
    }

    @Override
    public Articulo buscarArticulo(String codigo) {
        /*try {
            return em.find(Articulo.class, codigo);
        } catch (Exception e) {
            System.err.println("Error buscando artículo: " + e.getMessage());
            return null;
        }*/
        boolean vuelta = true;
        Articulo articulo = new Articulo();
        while(vuelta){
            articulo = em.find(Articulo.class, codigo);
            if(articulo == null){
                codigo = herramientas.repetirString(2);
                if(codigo.equals("0")){
                    articulo = null;
                    vuelta = false;
                }
            }else{
                vuelta = false;
            }
        }
        return articulo;
    }

    @Override
    public Pedido buscarPedido(int numPedido) {
        /*return buscarPorId(numPedido);*/

        //ARREGLAR
        Pedido pedido = null;
        return pedido;
    }

    @Override
    public boolean estadoPedido(int numero) {
        //Pedido pedido = buscarPorId(numero);
        //return pedido != null && pedido.isPendiente();

        String consulta = "SELECT COUNT(p) FROM Pedido p JOIN p.articulo a WHERE p.numPedido = :numPedido" +
                "AND FUNCTION('DATE_ADD', p.fechaHora, FUNCTION('INTERVAL', a.tiempoPreparacion * p.cantidad, 'MINUTE'))" +
                "> CURRENT_TIMESTAMP";
        Long cantidad = em.createQuery(consulta, Long.class).setParameter("numPedido", numero).getSingleResult();
        boolean pendiente = cantidad > 0;
        return pendiente;
    }

    @Override
    public void eliminarPedido(Pedido pedido) {
        /*eliminar(pedido.getNumPedido());
        herramientas.enviarMensaje(1, null);*/
    }

    @Override
    public void mostrarPedidos(boolean pendiente, Cliente cliente) {
        /*try {
            List<Pedido> pedidos = em.createQuery(
                            "SELECT p FROM Pedido p WHERE p.cliente = :cliente AND p.pendiente = :pendiente", Pedido.class)
                    .setParameter("cliente", cliente)
                    .setParameter("pendiente", pendiente)
                    .getResultList();
            for (Pedido p : pedidos) {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.err.println("Error mostrando pedidos: " + e.getMessage());
        }*/
        List<Pedido> pedidos;
        String consulta;
        if(cliente == null){
            if(pendiente){
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";;
            }else{
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW();";;
            }
            pedidos = em.createNativeQuery(consulta, Pedido.class).getResultList();
        }else{
            if(pendiente){
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE p.email_cliente = :email AND " +
                        "DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
            }else{
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE p.email_cliente = :email AND " +
                        "DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW();";
            }
            pedidos = em.createNativeQuery(consulta, Pedido.class).setParameter("email", cliente.getEmail()).getResultList();
        }
        for(Pedido i : pedidos){
            int numPedido = i.getNumPedido();
            String email = i.getCliente().getEmail();
            String codigo = i.getArticulo().getCodigoProducto();
            int cantidad = i.getCantidad();
            LocalDateTime fechaHora = i.getFechaHora();
            herramientas.enviarMensaje(0, "\nNúmero de pedido: " + numPedido + "\nEmail del cliente: " +
                    email + "\nCódigo del artículo: " + codigo + "\nCantidad: " + cantidad + "\nFecha y hora: " +
                    fechaHora + "\n\n--------------");
        }
        int cantidad = pedidos.size();
        if(cantidad == 0) {
            herramientas.enviarMensaje(0, "No hay pedidos por mostrar.");
        }
    }

    /*//Esto no sé para qué es
    //@Override
    public boolean eliminar(int numPedido) { // Cambiado 'id' por 'numPedido'
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido pedido = em.find(Pedido.class, numPedido); // Cambiado 'id' por 'numPedido'
            if (pedido != null) {
                em.remove(pedido);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error eliminando pedido: " + e.getMessage());
            return false;
        }
    }

    //@Override
    public Pedido buscarPorId(int numPedido) { // Cambiado 'id' por 'numPedido'
        try {
            return em.find(Pedido.class, numPedido); // Cambiado 'id' por 'numPedido'
        } catch (Exception e) {
            System.err.println("Error buscando pedido: " + e.getMessage());
            return null;
        }
    }

    //@Override
    public List<Pedido> obtenerTodos() {
        try {
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error obteniendo pedidos: " + e.getMessage());
            return List.of(); // lista vacía en lugar de null
        }
    }

    //@Override
    public boolean actualizar(Pedido pedido) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(pedido);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error actualizando pedido: " + e.getMessage());
            return false;
        }
    }
        */
}