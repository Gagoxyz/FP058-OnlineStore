package org.javaenjoyers.controlador;


import org.javaenjoyers.RepositorioGenerico;
import org.javaenjoyers.modelo.Articulo;

import java.util.HashMap;
import java.util.Map;

public class ArticuloControlador {
    private RepositorioGenerico<Articulo> articulos;

    public ArticuloControlador() {
        this.articulos = new RepositorioGenerico<>();
    }

    public void agregarArticulo(String codigo, Articulo articulo) {
        articulos.agregar(codigo, articulo);
    }

    public Articulo obtenerArticulo(String codigo) {
        return articulos.obtener(codigo);
    }

    public void eliminarArticulo(String codigo) {
        articulos.eliminar(codigo);
    }

    public void mostrarArticulos() {
        articulos.mostrarTodos();
    }

}
