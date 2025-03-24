package org.javaenjoyers.modelos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModeloTest {
    private Modelo modelo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        modelo = new Modelo();
        cliente = new Estandar("email@test.com", "Juan", "Calle 123", "12345678A");
    }

    @Test
    void agregarCliente_deberiaAgregarClienteCorrectamente() {
        modelo.agregarCliente(cliente);

        assertEquals(1, modelo.getClientes().listSize());
    }

    @Test
    void eliminarPedido_deberiaEliminarCorrectamente() {
        Pedido pedido = new Pedido(cliente, new Articulo("001", "Producto", 10, 5, 60), 1);
        modelo.agregarPedido(pedido);
        modelo.eliminarPedido(1);

        assertEquals(0, modelo.getPedidos().listSize());
    }

    @Test
    void agregarCliente_deberiaNoAgregarClienteConEmailDuplicado() {
        modelo.agregarCliente(cliente);
        Cliente clienteDuplicado = new Estandar("email@test.com", "Carlos", "Calle 456", "87654321B");
        modelo.agregarCliente(clienteDuplicado);

        assertEquals(1, modelo.getClientes().listSize());
    }

    @Test
    void agregarArticulo_deberiaAgregarArticuloCorrectamente() {
        Articulo articulo = new Articulo("002", "Articulo Prueba", 100.0f, 10.0f, 30);
        modelo.agregarArticulo(articulo);

        assertEquals(1, modelo.getArticulos().listSize());
    }
}