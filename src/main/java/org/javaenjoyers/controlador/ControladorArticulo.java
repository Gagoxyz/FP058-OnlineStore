package org.javaenjoyers.controlador;

import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.vista.JavaFX.VistaArtJavaFX;
import org.javaenjoyers.vista.JavaFX.VistaJavaFX;
import org.javaenjoyers.vista.VistaArticulos;

import java.util.List;

public class ControladorArticulo {
    private VistaArticulos vistaArticulos;
    private Herramientas herramientas;
    private ArticuloDAO artDAO;

    private VistaArtJavaFX vistaArticulosJFX;
    private VistaJavaFX vistaJFX;

    public ControladorArticulo(ArticuloDAO artDAO, Herramientas herramientas, VistaArticulos vistaArticulos) {
        this.artDAO = artDAO;
        this.herramientas = herramientas;
        this.vistaArticulos = vistaArticulos;
    }

    public void setVistaArticulosJFX(VistaArtJavaFX vistaArticulosJFX) {
        this.vistaArticulosJFX = vistaArticulosJFX;
    }

    public void setVistaJFX(VistaJavaFX vistaJFX) {
        this.vistaJFX = vistaJFX;
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
        List<Articulo> articulos = artDAO.mostrarArticulos();
        vistaArticulos.mostrarArticulos(articulos);
    }

    //MÉTODOS PARA JavaFX
    public void menuArticulosJFX(){
        vistaJFX.cambiarVista(vistaArticulosJFX.menuArticulos());
    }

    public void pedirCodigo(){
        vistaJFX.cambiarVista(vistaArticulosJFX.codigoArticulo());
    }

    public void comprobarCodigo(String codigo){
        Articulo articulo;
        articulo = artDAO.buscarArticulo(codigo);
        if(articulo != null){
            vistaJFX.cambiarVista(vistaArticulosJFX.codigoIncorreco());
        }else{
            vistaJFX.cambiarVista(vistaArticulosJFX.infoArticulo(codigo));
        }
    }

    public void crearArticulo(String codigo, String descripcion, String precioStr, String gastosStr, String tiempoStr){
        int x = 0;
        try{
            double precio = Double.parseDouble(precioStr);
            x = 1;
            double gastos = Double.parseDouble(gastosStr);
            x = 2;
            int tiempo = Integer.parseInt(tiempoStr);
            Articulo articulo = new Articulo(codigo, descripcion, precio, gastos, tiempo);
            artDAO.insertarArticulo(articulo);
            vistaJFX.cambiarVista(vistaArticulosJFX.articuloInsertado());
        }catch(NumberFormatException e){
            vistaJFX.cambiarVista(vistaArticulosJFX.formatoIncorrecto(x, codigo));
        }
    }

    public void mostrarArticulos(){
        List<Articulo> articulos = artDAO.mostrarArticulos();
        vistaJFX.cambiarVista(vistaArticulosJFX.mostrarListaArticulos(articulos));
    }
}
