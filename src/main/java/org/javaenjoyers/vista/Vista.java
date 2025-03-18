package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.Controlador;

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
        int opcionMenu;
        boolean app = true;

        while(app) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Gestión clientes");
            System.out.println("2. Gestión artículos");
            System.out.println("3. Gestión pedidos");
            System.out.println("0. Salir");
            opcionMenu = teclado.nextInt();
            teclado.nextLine();

            switch (opcionMenu) {
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
                    app = false;
                    return;
                default:
                    System.out.println("Opción incorrecta\n");
                    break;
            }
        }
    }

    public void menuClientes(){
        int opcionMenu;

        System.out.println("Menú clientes:");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar clientes Estandar");
        System.out.println("4. Mostrar clientes Premium");
        System.out.println("0. Atrás");
        opcionMenu = teclado.nextInt();
        teclado.nextLine();

        switch (opcionMenu) {
            case 1:
                controlador.agregarCliente();
                break;
            case 2:
                controlador.mostrarClientes();
                break;
            case 3:
                controlador.mostrarClientesEstandar();
                break;
            case 4:
                controlador.mostrarClientesPremium();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                System.out.println("Opción incorrecta\n");
                break;
        }
    }

    public void menuArticulos(){
        System.out.println("nada de momento");
    }

    public void menuPedidos(){
        System.out.println("nada de nada");
    }

}
