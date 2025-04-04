package org.javaenjoyers.modelos;

import org.javaenjoyers.dao.ArticuloDAO;
import org.javaenjoyers.dao.FactoryDAO;

import java.util.List;

public class ArticuloModelo {

    private final ArticuloDAO articuloDAO;

    public ArticuloModelo(){
        articuloDAO = FactoryDAO.getArticuloDAO();
    }

    public void agregarArticulo(Articulo articulo){
        articuloDAO.insertar(articulo);
    }

    public List<Articulo> obtenerArticulos(){
        return articuloDAO.obtenerDatos();
    }

    public Articulo obtenerArticuloPorCodigo(String codigoProducto){
        return articuloDAO.obtener(codigoProducto);
    }
}
