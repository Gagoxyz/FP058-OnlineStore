package org.javaenjoyers.DAO;

import org.javaenjoyers.modelo.Articulo;

import java.util.List;

public interface ArticuloDAO {
    void insertarArticulo(Articulo articulo);
    Articulo buscarArticulo(String codigo);
    List<Articulo> mostrarArticulos();
}
