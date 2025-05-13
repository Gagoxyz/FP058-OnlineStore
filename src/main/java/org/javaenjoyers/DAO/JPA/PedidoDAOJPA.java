package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.LocalDateTime;
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
        //herramientas.enviarMensaje(1, null);
    }

    @Override
    public Cliente buscarCliente(String email) {
        Cliente cliente = em.find(Cliente.class, email);
        return cliente;
    }

    @Override
    public Articulo buscarArticulo(String codigo) {
        Articulo articulo = em.find(Articulo.class, codigo);
        return articulo;
    }

    @Override
    public Pedido buscarPedido(int numPedido) {
        Pedido pedido;
        pedido = em.find(Pedido.class, numPedido);
        return pedido;
    }

    @Override
    public boolean estadoPedido(int numero) {
        String consulta = "SELECT COUNT(*) FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                "WHERE num_pedido = :numPedido AND DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
        Object resultado = em.createNativeQuery(consulta, Long.class).setParameter("numPedido", numero).getSingleResult();
        Long cantidad = ((Number) resultado).longValue();
        return cantidad > 0;
    }

    @Override
    public boolean eliminarPedido(Pedido pedido) {
        em.getTransaction().begin();
        em.remove(pedido);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public List<Pedido> mostrarPedidos(boolean pendiente, Cliente cliente) {
        List<Pedido> pedidos;
        String consulta;
        if(cliente == null){
            if(pendiente){
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW()" +
                        "ORDER BY p.num_pedido;";
            }else{
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW()" +
                        "ORDER BY p.num_pedido;";
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
        return pedidos;
    }
}