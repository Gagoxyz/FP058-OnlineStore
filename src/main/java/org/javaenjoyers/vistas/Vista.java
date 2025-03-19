package org.javaenjoyers.vistas;

import org.javaenjoyers.controladores.Controlador;

import java.util.Scanner;

public class Vista {

    Scanner teclado = new Scanner(System.in);

    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

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

    public void menuPrincipal(){
        int opcion;
        boolean app = true;

        while(app) {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1. Gestión clientes");
            System.out.println("2. Gestión artículos");
            System.out.println("3. Gestión pedidos");
            System.out.println("0. Salir");
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuArticulos();
                    break;
                case 3:
                    menuPedidos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nOpción incorrecta.\n");
                    break;
            }
        }
    }

    public void menuClientes(){
        int opcion;

        System.out.println("\nMenú clientes:");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar clientes Estandar");
        System.out.println("4. Mostrar clientes Premium");
        System.out.println("0. Atrás");
        opcion = teclado.nextInt();
        teclado.nextLine();

        controlador.opcionGestionClientes(opcion);
    }

    public void menuArticulos(){
        int opcion;

        System.out.println("\nMenú artículos:");
        System.out.println("1. Añadir artículo");
        System.out.println("2. Mostrar artículos");
        System.out.println("0. Atrás");
        opcion = teclado.nextInt();
        teclado.nextLine();

        controlador.opcionGestionArticulos(opcion);;
    }

    public void menuPedidos(){
        int opcion;

        System.out.println("\nMenú pedidos:");
        System.out.println("1. Añadir pedido");
        System.out.println("2. Eliminar pedido");
        System.out.println("3. Mostrar pedidos pendientes");
        System.out.println("4. Mostrar pedidos enviados");
        System.out.println("0. Atrás");
        opcion = teclado.nextInt();
        teclado.nextLine();

        controlador.opcionGestionPedidos(opcion);;
    }

    public String solicitarDato(String string){
        System.out.println(string);
        return teclado.nextLine();
    }

    public void mostrarMensaje(String string){
        System.out.println(string);
    }
}
