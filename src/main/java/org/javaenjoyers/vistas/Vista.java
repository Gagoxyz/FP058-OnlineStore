package org.javaenjoyers.vistas;

import org.javaenjoyers.controladores.Controlador;

import java.util.Scanner;

/**
 * Clase Vista, se encargará de realizar las interacciones con el usuario
 */

public class Vista {

    Scanner teclado = new Scanner(System.in);

    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Setter para asignar un controlador a la vista
     * @param controlador Tipo Controlador
     */
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Prints para mostrar un mensaje de bienvenida al iniciar la aplicación
     */
    public void bienvenida(){
        System.out.println("\n\n");
        System.out.println("\t**************************************************");
        System.out.println("\t**                                              **");
        System.out.println("\t**                 JavaEnjoyers                 **");
        System.out.println("\t**           Bienvenido a OnlineStore           **");
        System.out.println("\t**              Gestión de pedidos              **");
        System.out.println("\t**                                              **");
        System.out.println("\t**************************************************\n");
    }

    /**
     * Menú principal mostrando opciones disponibles, cada opción llamará a un submenú
     */
    public void menuPrincipal(){
        String opcion;

        while(true) {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1. Gestión artículos");
            System.out.println("2. Gestión clientes");
            System.out.println("3. Gestión pedidos");
            System.out.println("0. Salir");
            opcion = teclado.nextLine();

            switch (opcion){
                case "1":
                    menuArticulos();
                    break;
                case "2":
                    menuClientes();
                    break;
                case "3":
                    menuPedidos();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nOpción incorrecta.\n");
                    break;
            }
        }
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

        controlador.opcionGestionArticulos(opcion);
    }

    /**
     * Submenú para la gestión de clientes, la opción indicada por el usuario se le pasará al controlador
     */
    public void menuClientes(){
        String opcion;

        System.out.println("\nMenú clientes:");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar clientes Estandar");
        System.out.println("4. Mostrar clientes Premium");
        System.out.println("0. Atrás");
        opcion = teclado.nextLine();

        controlador.opcionGestionClientes(opcion);
    }

    /**
     * Submenú para la gestión de pedidos, la opción indicada por el usuario se le pasará al controlador
     */
    public void menuPedidos(){
        String opcion;

        System.out.println("\nMenú pedidos:");
        System.out.println("1. Añadir pedido");
        System.out.println("2. Eliminar pedido");
        System.out.println("3. Mostrar pedidos pendientes");
        System.out.println("4. Mostrar pedidos enviados");
        System.out.println("5. Resumen de pedidos");
        System.out.println("0. Atrás");
        opcion = teclado.nextLine();

        controlador.opcionGestionPedidos(opcion);
    }

    /**
     * Se utilizará desde el controlador para solicitar un dato al usuario
     * @param string Cadena que se recibe del controlador y se mostrará por pantalla
     * @return Devuelve la entrada por el usuario
     */
    public String solicitarDato(String string){
        System.out.println(string);
        return teclado.nextLine();
    }

    /**
     * Se utilizará desde el controlador para mostrar mensajes por pantalla
     * @param string Cadena que se recibe del controlador
     */
    public void mostrarMensaje(String string){
        System.out.println(string);
    }
}
