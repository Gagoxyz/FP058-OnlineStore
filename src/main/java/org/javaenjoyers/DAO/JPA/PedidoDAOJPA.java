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
        herramientas.enviarMensaje(1, null);
    }

    @Override
    public Cliente buscarCliente(String email) {
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
        Pedido pedido = new Pedido();
        boolean vuelta = true;
        while (vuelta){
            pedido = em.find(Pedido.class, numPedido);
            if(pedido == null){
                numPedido = herramientas.repetirInt();
                if(numPedido == 0){
                    pedido = null;
                    vuelta = false;
                }
            }else{
                vuelta = false;
            }
        }
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
    public void eliminarPedido(Pedido pedido) {
        em.getTransaction().begin();
        em.remove(pedido);
        em.getTransaction().commit();
        herramientas.enviarMensaje(1, null);
    }

    @Override
    public void mostrarPedidos(boolean pendiente, Cliente cliente) {
        List<Pedido> pedidos;
        String consulta;
        if(cliente == null){
            if(pendiente){
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) > NOW();";
            }else{
                consulta = "SELECT p.* FROM pedidos p JOIN articulos a ON p.cod_articulo = a.codigo\n" +
                        "WHERE DATE_ADD(fecha_hora, INTERVAL tiempo_preparacion*cantidad MINUTE) < NOW();";
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
}