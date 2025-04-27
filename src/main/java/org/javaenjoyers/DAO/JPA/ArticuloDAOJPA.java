package org.javaenjoyers.DAO.JPA;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Articulo;

import java.util.List;

public class ArticuloDAOJPA implements ArticuloDAO {

    private final EntityManager em;
    public Herramientas herramientas;

    public ArticuloDAOJPA(EntityManager em, Herramientas herramientas) {
        this.em = em;
        this.herramientas = herramientas;
    }

    @Override
    public void insertarArticulo(Articulo articulo) {
        em.getTransaction().begin();
        em.persist(articulo);
        em.getTransaction().commit();
    }

    @Override
    public Articulo buscarArticulo(String codigo) {
        return em.find(Articulo.class, codigo);
    }

    @Override
    public void mostrarArticulos() {
        List<Articulo> articulos;
        articulos = em.createQuery("SELECT a FROM Articulo a", Articulo.class).getResultList();
        for(Articulo i : articulos){
            String codigo = i.getCodigoProducto();
            String descripcion = i.getDescripcion();
            double precio = i.getPrecioVenta();
            double gastos = i.getGastosEnvio();
            int tiempo = i.getTiempoPrepEnvio();
            herramientas.enviarMensaje(0, "\nCódigo del artículo: " + codigo + "\nDescripción: " +
                    descripcion + "\nPrecio de venta: " + precio + " €\nGastos de envio: " + gastos +
                    " €\nTiempo de preparación: " + tiempo + " minutos\n\n--------------");
        }
    }
}