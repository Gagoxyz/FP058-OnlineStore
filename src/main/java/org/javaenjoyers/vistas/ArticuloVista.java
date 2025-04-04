package org.javaenjoyers.vistas;

import org.javaenjoyers.controladores.ArticuloControlador;
import java.util.Scanner;

public class ArticuloVista {

    Scanner teclado = new Scanner(System.in);

    private ArticuloControlador articuloControlador;

    public ArticuloVista(ArticuloControlador articuloControlador) {
        this.articuloControlador = articuloControlador;
    }

    public void setArticuloControlador(ArticuloControlador articuloControlador) {
        this.articuloControlador = articuloControlador;
    }

    /**
     * Submenú para la gestión de artículos, la opción indicada por el usuario se le pasará al controlador
     */
    public void menuArticulos(){
        String opcion;

        System.out.println("\nMenú artículos:");
        System.out.println("1. Añadir artículo");
        System.out.println("2. Mostrar artículos");
        System.out.println("0. Atrás");
        opcion = teclado.nextLine();

        articuloControlador.opcionGestionArticulos(opcion);
    }
}
