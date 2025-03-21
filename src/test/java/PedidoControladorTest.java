import org.javaenjoyers.controlador.PedidoControlador;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;
import org.javaenjoyers.modelo.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoControladorTest {
    private PedidoControlador pedidoControlador;
    private Cliente cliente;
    private Articulo articulo;

    @BeforeEach
    void setUp() {
        pedidoControlador = new PedidoControlador();
        cliente = new Estandar("cliente@email.com", "Juan Pérez", "Calle 123", "12345678X");
        articulo = new Articulo("A002", "Smartphone", 500.0f, 10.0f, 20);
    }

    @Test
    void testContarPedidos() {
        assertEquals(0, pedidoControlador.contarPedidos(), "Debería haber 0 pedidos al inicio.");

        Pedido pedido1 = new Pedido(1, cliente, articulo, 2, LocalDateTime.now());
        pedidoControlador.agregarPedido(1, pedido1);

        assertEquals(1, pedidoControlador.contarPedidos(), "Después de agregar 1 pedido, debería haber 1.");
    }
}
