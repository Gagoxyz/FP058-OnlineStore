package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.ArticuloControlador;

import java.util.Scanner;

public class ArticuloVista {
    private ArticuloControlador articuloControlador;
    private Scanner scanner;

    public ArticuloVista(ArticuloControlador articuloControlador) {
        this.articuloControlador = articuloControlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n Gestión de Artículos");
            System.out.println("1. Añadir Artículo");
            System.out.println("2. Mostrar Artículos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> agregarArticulo();
                case 2 -> articuloControlador.mostrarArticulos();
                case 3 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void agregarArticulo() {
        System.out.print("Código del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio de venta: ");
        float precio = scanner.nextFloat();
        System.out.print("Gastos de envío: ");
        float gastosEnvio = scanner.nextFloat();
        System.out.print("Tiempo de preparación (minutos): ");
        int tiempoPrep = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        articuloControlador.agregarArticulo(codigo, descripcion, precio, gastosEnvio, tiempoPrep);
    }

}
