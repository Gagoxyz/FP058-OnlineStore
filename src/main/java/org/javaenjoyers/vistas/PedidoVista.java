package org.javaenjoyers.vistas;

import org.javaenjoyers.controladores.PedidoControlador;

import java.util.Scanner;

public class PedidoVista {

    Scanner teclado = new Scanner(System.in);

    private PedidoControlador pedidoControlador;

    public PedidoVista(PedidoControlador pedidoControlador) {
        this.pedidoControlador = pedidoControlador;
    }

    public void setPedidoControlador(PedidoControlador pedidoControlador) {
        this.pedidoControlador = pedidoControlador;
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

        pedidoControlador.opcionGestionPedidos(opcion);
    }
}
