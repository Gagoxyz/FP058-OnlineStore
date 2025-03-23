package JavaEnjoyers.vista;

import JavaEnjoyers.controlador.ArticuloControlador;
import JavaEnjoyers.modelo.Articulo;
import java.util.Scanner;

public class ArticuloVista {
    private final ArticuloControlador articuloControlador;
    private final Scanner scanner;


    public ArticuloVista(ArticuloControlador articuloControlador) {
        this.articuloControlador = articuloControlador;
        this.scanner = new Scanner(System.in);
    }


    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\nMenú de Gestión de Artículos");
            System.out.println("1. Agregar Artículo");
            System.out.println("2. Eliminar Artículo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarArticulo();
                    break;
                case 2:
                    eliminarArticulo();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema de artículos...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }


    private void agregarArticulo() {
        System.out.print("Código del Artículo: ");
        String codigo = scanner.nextLine();

        System.out.print("Descripción del Artículo: ");
        String descripcion = scanner.nextLine();

        System.out.print("Precio del Artículo: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, introduzca un número válido.");
            scanner.next();
        }
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Gastos de Envío: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, introduzca un número válido.");
            scanner.next();
        }
        double gastosEnvio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Tiempo de Preparación (minutos): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, introduzca un número válido.");
            scanner.next();
        }
        int tiempoPreparacion = scanner.nextInt();
        scanner.nextLine();


        Articulo nuevoArticulo = new Articulo(codigo, descripcion, precio, gastosEnvio, tiempoPreparacion);
        articuloControlador.agregarArticulo(nuevoArticulo);
        System.out.println("Artículo agregado correctamente.");
    }


    private void eliminarArticulo() {
        System.out.print("Ingrese el código del artículo a eliminar: ");
        String codigo = scanner.nextLine();

        if (articuloControlador.eliminarArticulo(codigo)) {
            System.out.println("Artículo eliminado correctamente.");
        } else {
            System.out.println("No se encontró un artículo con ese código.");
        }
    }
}
