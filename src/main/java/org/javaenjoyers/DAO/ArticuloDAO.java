package org.javaenjoyers.DAO;

import org.javaenjoyers.modelo.Articulo;

public interface ArticuloDAO {
    void insertarArticulo(Articulo articulo);
    Articulo buscarArticulo(String codigo);
    void mostrarArticulos();
}
