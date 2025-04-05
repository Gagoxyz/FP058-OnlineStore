package org.javaenjoyers.vista;

import java.util.List;
import java.util.Scanner;

public class Vista {

    Scanner teclado = new Scanner(System.in);

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

    public int menuPrincipal(){
        int opcionMenu;
        System.out.println("\nOPCIONES:");
        System.out.println("1. Gestión de clientes");
        System.out.println("2. Gestión de artículos");
        System.out.println("3. Gestión de pedidos");
        System.out.println("0. Salir");
        System.out.print("¿Qué deseas hacer? ");
        opcionMenu = errorIntEntrada();
        return opcionMenu;
    }

    public String repeticionString(String mensaje){
        System.out.print(mensaje + ": ");
        return teclado.nextLine();
    }

    public int repetirInt(){
        System.out.print("No se ha encontrado, vuelve a introducirlo (o 0 para volver atrás): ");
        return errorIntEntrada();
    }

    public void enviarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public String pedirString(String mensaje){
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    public int intInvalido(){
        System.out.print("Esa opción no es válida, vuelva a escoger: ");
        return errorIntEntrada();
    }

    public int errorIntEntrada(){
        int valor = 0;
        boolean valido = false;
        while(!valido){
            try{
                valor = teclado.nextInt();
                teclado.nextLine();
                valido = true;
            }catch(java.util.InputMismatchException e){
                System.out.print("Has introducido un valor incorrecto. Vuelve a escoger: ");
                teclado.nextLine();
            }
        }
        return valor;
    }

    public float errorFloatEntrada(){
        float valor = 0f;
        boolean valido = false;
        while(!valido){
            try{
                valor = teclado.nextFloat();
                teclado.nextLine();
                valido = true;
            }catch(java.util.InputMismatchException e){
                System.out.print("Has introducido un valor incorrecto. Vuelve a escoger: ");
                teclado.nextLine();
            }
        }
        return valor;
    }

}
