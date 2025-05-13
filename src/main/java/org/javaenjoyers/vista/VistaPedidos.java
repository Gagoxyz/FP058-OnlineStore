package org.javaenjoyers.vista;

import org.javaenjoyers.controlador.Herramientas;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VistaPedidos {
    Scanner teclado = new Scanner(System.in);
    private Herramientas herramientas;

    public VistaPedidos(Herramientas herramientas) {
        this.herramientas = herramientas;
    }

    public int menuPedidos(){
        int opcionMenu;
        System.out.println("\nMENÚ PEDIDOS:");
        System.out.println("1. Añadir pedido");
        System.out.println("2. Eliminar pedido");
        System.out.println("3. Mostrar pedido pendientes");
        System.out.println("4. Mostrar pedidos enviados");
        System.out.println("0. Atrás");
        System.out.print("¿Qué deseas hacer? ");
        opcionMenu = herramientas.errorIntEntrada();
        return opcionMenu;
    }

    public int clientePedido(){
        System.out.println("\nCliente del pedido:");
        System.out.println("1. Registrado");
        System.out.println("2. Nuevo");
        return herramientas.errorIntEntrada();
    }

    public Pedido infoPedido(Cliente cliente, Articulo articulo){
        System.out.print("Indica la cantidad del artículo: ");
        int cantidad = herramientas.errorIntEntrada();
        return new Pedido(articulo, cantidad, cliente);
    }

    public int mostrarPedidos(){
        System.out.println("\n1. Todos");
        System.out.println("2. Por cliente");
        System.out.print("¿Qué pedidos quieres ver? ");
        return herramientas.errorIntEntrada();
    }

    public int pedirNum(){
        System.out.print("\nIndica el número del pedido que deseas eliminar: ");
        return herramientas.errorIntEntrada();
    }

    public void showPedidos(List<Pedido> listaPed){
        if(listaPed.isEmpty()){
            System.out.println("No hay pedidos por mostrar");
        }else{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for(Pedido i : listaPed){
                String fechaForm = i.getFechaHora().format(formato);
                System.out.println("\nNúmero de pedido: " + i.getNumPedido() + "\nEmail del cliente: " +
                        i.getCliente().getEmail() + "\nCódigo del artículo: " + i.getArticulo().getCodigoProducto() +
                        "\nCantidad: " + i.getCantidad() + "\nFecha y hora: " + fechaForm + "\n\n--------------");
            }
        }
    }
}
