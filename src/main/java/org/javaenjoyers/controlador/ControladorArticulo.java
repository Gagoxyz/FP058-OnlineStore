package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.vista.VistaArticulos;

public class ControladorArticulo {
    private VistaArticulos vistaArticulos;
    private Herramientas herramientas;
    private ArticuloDAO artDAO;

    public ControladorArticulo(ArticuloDAO artDAO, Herramientas herramientas, VistaArticulos vistaArticulos) {
        this.artDAO = artDAO;
        this.herramientas = herramientas;
        this.vistaArticulos = vistaArticulos;
    }

    public void menuArticulos(){
        boolean seguir = true;
        int opcion;
        while(seguir){
            opcion = vistaArticulos.menuArticulos();
            opcion = herramientas.comprobarOpcion(opcion, 0, 2);
            switch (opcion){
                case 1:
                    addArticulo();
                    break;
                case 2:
                    showArticulos();
                    break;
                case 0:
                    seguir = false;
                    break;
            }
        }
    }

    public void addArticulo(){
        String codigo = vistaArticulos.codigoArticulo();
        boolean repetido = true;
        Articulo articulo;
        while(repetido){
            articulo = artDAO.buscarArticulo(codigo);
            if(articulo == null){
                break;
            }
            codigo = herramientas.repetirString(1);
        }
        articulo = vistaArticulos.infoArticulo(codigo);
        artDAO.insertarArticulo(articulo);
        herramientas.enviarMensaje(1, null);
    }

    public void showArticulos(){
        artDAO.mostrarArticulos();
    }
}
