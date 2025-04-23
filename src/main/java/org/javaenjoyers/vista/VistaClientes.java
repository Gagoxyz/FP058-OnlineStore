package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Cliente;

import java.util.Scanner;

public class VistaClientes {

    Scanner teclado = new Scanner(System.in);
    private Herramientas herramientas;

    public VistaClientes(Herramientas herramientas) {
        this.herramientas = herramientas;
    }

    public int menuClientes(){
        int opcionMenu;
        System.out.println("\nMENÚ CLIENTES:");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar clientes Estandar");
        System.out.println("4. Mostrar clientes Premium");
        System.out.println("0. Atrás");
        System.out.print("¿Qué deseas hacer? ");
        opcionMenu = herramientas.errorIntEntrada();
        return opcionMenu;
    }

    public String emailCliente(){
        System.out.print("\nIndica el email del cliente: ");
        return teclado.nextLine();
    }

    public Cliente infoCliente(String email){
        System.out.print("Indica el NIF del cliente: ");
        String nif = teclado.nextLine();
        System.out.print("Indica el nombre del cliente: ");
        String nombre = teclado.nextLine();
        System.out.print("Indica el domicilio del cliente: ");
        String domicilio = teclado.nextLine();
        return new Cliente(email, nombre, domicilio, nif);
    }

    public void tipoCliente(){
        System.out.println("\n1. Estándar");
        System.out.println("2. Premium");
        System.out.print("¿Qué tipo de cliente es? ");
    }
}
