package org.javaenjoyers.vistas;

import org.javaenjoyers.controladores.ClienteControlador;

import java.util.Scanner;

public class ClienteVista {

    Scanner teclado = new Scanner(System.in);

    private ClienteControlador clienteControlador;

    public ClienteVista(ClienteControlador clienteControlador){
        this.clienteControlador = clienteControlador;
    }

    public void setClienteControlador(ClienteControlador clienteControlador) {
        this.clienteControlador = clienteControlador;
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

        clienteControlador.opcionGestionClientes(opcion);
    }
}
