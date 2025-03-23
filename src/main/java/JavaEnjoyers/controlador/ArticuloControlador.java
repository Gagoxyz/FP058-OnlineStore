package JavaEnjoyers.controlador;

import JavaEnjoyers.modelo.Articulo;
import java.util.ArrayList;
import java.util.List;

public class ArticuloControlador {
    private List<Articulo> listaArticulos;

    public ArticuloControlador() {
        this.listaArticulos = new ArrayList<>();
    }


    public void agregarArticulo(Articulo articulo) {
        listaArticulos.add(articulo);
    }


    public boolean eliminarArticulo(String codigo) {
        for (Articulo articulo : listaArticulos) {
            if (articulo.getCodigo().equals(codigo)) {
                listaArticulos.remove(articulo);
                return true;
            }
        }
        return false;
    }


    public Articulo obtenerArticuloPorCodigo(String codigo) {
        for (Articulo articulo : listaArticulos) {
            if (articulo.getCodigo().equals(codigo)) {
                return articulo;
            }
        }
        return null;
    }
}