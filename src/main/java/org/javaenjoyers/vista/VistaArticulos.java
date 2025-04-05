package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Articulo;

import java.util.List;
import java.util.Scanner;

public class VistaArticulos {
    Scanner teclado = new Scanner(System.in);
    private Herramientas herramientas;

    public VistaArticulos(Herramientas herramientas) {
        this.herramientas = herramientas;
    }

    public int menuArticulos(){
        int opcionMenu;
        System.out.println("\nMENÚ ARTÍCULOS:");
        System.out.println("1. Añadir artículo");
        System.out.println("2. Mostrar artículos");
        System.out.println("0. Atrás");
        System.out.print("¿Qué deseas hacer? ");
        opcionMenu = herramientas.errorIntEntrada();
        return opcionMenu;
    }

    public String codigoArticulo(){
        System.out.print("\nIndica el código del artículo: ");
        return teclado.nextLine();
    }

    public Articulo infoArticulo(String codigo){
        System.out.print("Indica la descripción del artículo: ");
        String descripcion = teclado.nextLine();
        System.out.print("Indica el precio del artículo: ");
        float precio = herramientas.errorFloatEntrada();
        System.out.print("Indica los gastos de envío del artículo: ");
        float gastos = herramientas.errorFloatEntrada();
        System.out.print("Indica el tiempo de preparación del artículo: ");
        int tiempo = herramientas.errorIntEntrada();
        return new Articulo(codigo, descripcion, precio, gastos, tiempo);
    }

    public void showArticulos(List<Articulo> articulos){
        System.out.println(articulos);
    }
}
