package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.OnlineStore;
import org.javaenjoyers.vista.VistaArticulos;

import java.util.List;

public class ControladorArticulo {
    private VistaArticulos vistaArticulos;
    private Herramientas herramientas;
    private OnlineStore tienda;

    public ControladorArticulo(Herramientas herramientas, OnlineStore tienda, VistaArticulos vistaArticulos) {
        this.herramientas = herramientas;
        this.tienda = tienda;
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
        while(repetido){
            for(Articulo i : tienda.getArticulos().getLista()){
                if(i.getCodigoProducto().equals(codigo)){
                    codigo = herramientas.repetirString(1);
                    repetido = true;
                    break;
                }
                repetido = false;
            }
        }
        Articulo articuloNuevo = vistaArticulos.infoArticulo(codigo);
        tienda.addArticulo(articuloNuevo);
        herramientas.enviarMensaje(1, null);
    }

    public void showArticulos(){
        List<Articulo> art = tienda.getArticulos().getLista();
        vistaArticulos.showArticulos(art);
    }
}
