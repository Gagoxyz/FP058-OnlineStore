package org.javaenjoyers.controladores;


import org.javaenjoyers.RepositorioGenerico;
import org.javaenjoyers.excepciones.ElementoNoEncontradoException;
import org.javaenjoyers.modelos.Articulo;


public class ArticuloControlador {
    private RepositorioGenerico<Articulo> articulos;

    public ArticuloControlador() {
        this.articulos = new RepositorioGenerico<>();
    }

    public void agregarArticulo(String codigo, Articulo articulo) {
        if (articulos.obtener(codigo) != null) {
            throw new IllegalArgumentException("El artículo con código " + codigo + " ya existe.");
        }
        articulos.agregar(codigo, articulo);
    }

    public Articulo obtenerArticulo(String codigo) {
        Articulo articulo = articulos.obtener(codigo);
        if (articulo == null) {
            throw new ElementoNoEncontradoException("Artículo con código " + codigo + " no encontrado.");
        }
        return articulo;
    }

    public void eliminarArticulo(String codigo) {
        if (articulos.obtener(codigo) == null) {
            throw new ElementoNoEncontradoException("No se puede eliminar. Artículo con código " + codigo + " no encontrado.");
        }
        articulos.eliminar(codigo);
    }

    public void mostrarArticulos() {
        articulos.mostrarTodos();
    }

}
