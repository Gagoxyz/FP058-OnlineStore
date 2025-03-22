package org.javaenjoyers.vista;

import org.javaenjoyers.modelo.*;

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

    public int menuClientes(){
        int opcionMenu;
        System.out.println("\nMENÚ CLIENTES:");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar clientes Estandar");
        System.out.println("4. Mostrar clientes Premium");
        System.out.println("0. Atrás");
        System.out.print("¿Qué deseas hacer? ");
        opcionMenu = errorIntEntrada();
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
        //System.out.print("Indica el email del cliente: ");
        //String email = teclado.nextLine();
        System.out.print("Indica el domicilio del cliente: ");
        String domicilio = teclado.nextLine();
        return new Cliente(email, nombre, domicilio, nif);
    }

    public int tipoCliente(){
        System.out.println("\n1. Estándar");
        System.out.println("2. Premium");
        System.out.print("¿Qué tipo de cliente es? ");
        return errorIntEntrada();
    }

    public int menuArticulos(){
        int opcionMenu;
        System.out.println("\nMENÚ ARTÍCULOS:");
        System.out.println("1. Añadir artículo");
        System.out.println("2. Mostrar artículos");
        System.out.println("0. Atrás");
        System.out.print("¿Qué deseas hacer? ");
        opcionMenu = errorIntEntrada();
        return opcionMenu;
    }

    public String codigoArticulo(){
        System.out.print("\nIndica el código del artículo: ");
        return teclado.nextLine();
    }

    public String repeticionString(){
        System.out.print("Ya existe, introduce uno distinto: ");
        return teclado.nextLine();

    }

    public Articulo infoArticulo(String codigo){
        System.out.print("Indica la descripción del artículo: ");
        String descripcion = teclado.nextLine();
        System.out.print("Indica el precio del artículo: ");
        float precio = errorFloatEntrada();
        System.out.print("Indica los gastos de envío del artículo: ");
        float gastos = errorFloatEntrada();
        System.out.print("Indica el tiempo de preparación del artículo: ");
        int tiempo = errorIntEntrada();
        return new Articulo(codigo, descripcion, precio, gastos, tiempo);
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
        opcionMenu = errorIntEntrada();
        return opcionMenu;
    }

    public int clientePedido(){
        System.out.println("\nCliente del pedido:");
        System.out.println("1. Registrado");
        System.out.println("2. Nuevo");
        return errorIntEntrada();
    }

    public String indicarCliente(){
        System.out.print("\nIndica el email del cliente: ");
        return teclado.nextLine();
    }

    public String corregirString(){
        System.out.print("No se ha encontrado, vuelve a introducirlo (o 0 para volver atrás): ");
        return teclado.nextLine();
    }

    public String indicarArticulo(){
        System.out.print("Indica el código del artículo: ");
        return teclado.nextLine();
    }

    public Pedido infoPedido(Cliente cliente, Articulo articulo, int numero){
        System.out.print("Indica la cantidad del artículo: ");
        int cantidad = errorIntEntrada();
        return new Pedido(numero, cliente, articulo, cantidad);
    }

    public int indicarNumero(){
        System.out.print("\nIndica el número del pedido que deseas eliminar: ");
        return errorIntEntrada();
    }

    public int corregirInt(){
        System.out.print("No se ha encontrado, vuelve a introducirlo (o 0 para volver atrás): ");
        return errorIntEntrada();
    }

    public void noEliminar(){
        System.out.println("El pedido indicado ya ha sido enviado, no puede eliminarse.");
    }

    public void ejecucionExitosa(){
        System.out.println("\n*** La acción se ha llevado a cabo con éxito.***\n");
    }

    public int mostrarPedidos(){
        System.out.println("\n1. Todos");
        System.out.println("2. Por cliente");
        System.out.print("¿Qué pedidos quieres ver? ");
        return errorIntEntrada();
    }

    public int intInvalido(){
        System.out.print("Esa opción no es válida, vuelva a escoger: ");
        return errorIntEntrada();
    }

    public void ceroPedidos(){
        System.out.println("\nNo hay pedidos que mostrar.");
    }

    //No sé si estos metodos pueden estar en VISTA
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
