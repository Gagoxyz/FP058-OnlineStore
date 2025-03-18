package org.javaenjoyers.controlador;

import org.javaenjoyers.modelo.*;

import java.util.Scanner;

public class Controlador {

    Scanner teclado = new Scanner(System.in);

    private Modelo modelo;

    public Controlador(Modelo modelo) {
        this.modelo = modelo;
    }

    public void agregarCliente(){
        String email, nombre, domicilio, nif;
        Cliente nuevoCliente = null;
        int opcionCliente;

        System.out.println("Indica los datos para el nuevo cliente");
        System.out.println("NIF:");
        nif = teclado.nextLine();
        System.out.println("Nombre:");
        nombre = teclado.nextLine();
        System.out.println("Email:");
        email = teclado.nextLine();
        System.out.println("Domicilio:");
        domicilio = teclado.nextLine();
        System.out.println("Tipo de cliente:");
        System.out.println("1. Estandar");
        System.out.println("2. Premium");
        opcionCliente = teclado.nextInt();
        switch (opcionCliente){
            case 1:
                nuevoCliente = new Estandar(email, nombre, domicilio, nif);
                break;
            case 2:
                nuevoCliente = new Premium(email, nombre, domicilio, nif);
                break;
            default:
                System.out.println("Opción incorrecta");
                return;
        }
        modelo.agregarCliente(nuevoCliente);
    }

    public void mostrarClientes(){
        modelo.mostrarClientes();
    }

    public void mostrarClientesEstandar(){
        modelo.mostrarClientesEstandar();
    }

    public void mostrarClientesPremium(){
        modelo.mostrarClientesPremium();
    }

    public void agregarArticulo(){
        String codigoProducto, descripcion;
        float previoVenta, gastosEnvio;
        int tiempoPrepEnvio;
        Articulo articulo = null;

        System.out.println("Indica los datos del artículo:");
        System.out.println("Código producto:");
        codigoProducto = teclado.nextLine();
    }

}
