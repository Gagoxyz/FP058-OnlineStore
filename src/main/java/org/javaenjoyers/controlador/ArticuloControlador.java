package org.javaenjoyers.controlador;


import org.javaenjoyers.modelo.Articulo;

import java.util.HashMap;
import java.util.Map;

public class ArticuloControlador {
    private Map<String, Articulo> articulos;

    public ArticuloControlador() {
        this.articulos = new HashMap<>();
    }

    public void agregarArticulo(String codigo, String descripcion, float precio, float gastosEnvio, int tiempoPreparacion) {
        if (articulos.containsKey(codigo)) {
            System.out.println("Error: Ya existe un artículo con ese código.");
            return;
        }
        Articulo articulo = new Articulo(codigo, descripcion, precio, gastosEnvio, tiempoPreparacion);
        articulos.put(codigo, articulo);
        System.out.println("Artículo agregado correctamente.");
    }

    public void mostrarArticulos() {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos registrados.");
        } else {
            System.out.println("Listado de artículos:");
            articulos.values().forEach(System.out::println);
        }
    }

    public Articulo buscarArticulo(String codigo) {
        return articulos.get(codigo);
    }


}
