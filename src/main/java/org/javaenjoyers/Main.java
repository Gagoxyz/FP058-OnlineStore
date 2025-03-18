package org.javaenjoyers;

import org.javaenjoyers.controlador.Controlador;
import org.javaenjoyers.modelo.*;
import org.javaenjoyers.vista.Vista;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // nueva tienda para JavaEnjoyers
        Modelo modelo = new Modelo ();
        Controlador controlador = new Controlador(modelo);
        Vista vista = new Vista(controlador);

        // clases de prueba
        Cliente cliente01 = new Estandar("manuela@estandar.com", "Manuela", "c/cliente estandar S/N", "123456789");
        Cliente cliente02 = new Premium("david@premium.com", "David", "c/cliente estandar S/N", "87654321X");
        Cliente cliente03 = new Premium("irene@premium.com", "Irene", "c/cliente de al lado S/N", "78420365F");
        Cliente cliente04 = new Estandar("sofia@estandar.com", "Sofía", "c/que maja soy S/N", "89624542S");
        Articulo articulo01 = new Articulo("A001", "Artículo de prueba", 20.99f, 4.99f, 60);
        Pedido pedido01 = new Pedido(1, cliente01, articulo01, 1, LocalDateTime.now());
        Pedido pedido02 = new Pedido(2, cliente03, articulo01, 30);

        // añadimos los clientes, articulos y pedidos a nuestra tienda
        modelo.agregarCliente(cliente01);
        modelo.agregarCliente(cliente02);
        modelo.agregarCliente(cliente03);
        modelo.agregarCliente(cliente04);
        modelo.agregarArticulo(articulo01);
        modelo.agregarPedido(pedido01);


        vista.bienvenida();
        vista.menuPrincipal();
    }
}
