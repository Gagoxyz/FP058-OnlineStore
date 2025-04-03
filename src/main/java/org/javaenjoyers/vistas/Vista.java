package org.javaenjoyers.vistas;

import org.javaenjoyers.controladores.Controlador;

import java.util.Scanner;

/**
 * Clase Vista, se encargará de realizar las interacciones con el usuario
 */

public class Vista {

    Scanner teclado = new Scanner(System.in);

    private Controlador controlador;
    private ClienteVista clienteVista;
    private ArticuloVista articuloVista;
    private PedidoVista pedidoVista;

    public Vista(Controlador controlador, ClienteVista clienteVista, ArticuloVista articuloVista, PedidoVista pedidoVista) {
        this.controlador = controlador;
        this.clienteVista = clienteVista;
        this.articuloVista = articuloVista;
        this.pedidoVista = pedidoVista;
    }

    /**
     * Setter para asignar un controlador a la vista
     * @param controlador Tipo Controlador
     */
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setClienteVista(ClienteVista clienteVista) {
        this.clienteVista = clienteVista;
    }

    public void setArticuloVista(ArticuloVista articuloVista) {
        this.articuloVista = articuloVista;
    }

    public void setPedidoVista(PedidoVista pedidoVista) {
        this.pedidoVista = pedidoVista;
    }

    /**
     * Prints para mostrar un mensaje de bienvenida al iniciar la aplicación
     */
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

    /**
     * Menú principal mostrando opciones disponibles, cada opción llamará a un submenú
     */
    public void menuPrincipal(){
        String opcion;

        while(true) {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1. Gestión artículos");
            System.out.println("2. Gestión clientes");
            System.out.println("3. Gestión pedidos");
            System.out.println("0. Salir");
            opcion = teclado.nextLine();

            switch (opcion){
                case "1":
                    articuloVista.menuArticulos();
                    break;
                case "2":
                    clienteVista.menuClientes();
                    break;
                case "3":
                    pedidoVista.menuPedidos();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nOpción incorrecta.\n");
                    break;
            }
        }
    }

    /**
     * Se utilizará desde el controlador para solicitar un dato al usuario
     * @param string Cadena que se recibe del controlador y se mostrará por pantalla
     * @return Devuelve la entrada por el usuario
     */
    public String solicitarDato(String string){
        System.out.println(string);
        return teclado.nextLine();
    }

    /**
     * Se utilizará desde el controlador para mostrar mensajes por pantalla
     * @param string Cadena que se recibe del controlador
     */
    public void mostrarMensaje(String string){
        System.out.println(string);
    }
}
