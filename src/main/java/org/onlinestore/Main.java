package org.onlinestore;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n");

        // prueba de 2 objetos tipo cliente
        Cliente cli01 = new Estandar("manuela@estandar.com", "Manuela", "c/cliente estandar S/N", "123456789");
        Cliente cli02 = new Premium("david@premium.com", "David", "c/cliente estandar S/N", "87654321X");

        // pruebas print para llamar a los objetos creados
        System.out.println(cli01.toString());
        System.out.println("\n\n");
        System.out.println(cli02.toString());
        System.out.println("\n\n");

        // prueba llamar constante CUOTA
        System.out.println(Premium.CUOTA);

    }
}