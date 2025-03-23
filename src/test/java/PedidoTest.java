import JavaEnjoyers.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    private Cliente clienteEstandar;
    private Cliente clientePremium;
    private Articulo articulo;
    private Pedido pedidoEstandar;
    private Pedido pedidoPremium;

    @BeforeEach
    void setUp() {
        clienteEstandar = new ClienteEstandar("Juan Pérez", "Calle Londres 543", "12345678A", "juan@example.com");
        clientePremium = new ClientePremium("Ana López", "Calle Corsega 124", "87654321B", "ana@example.com");
        articulo = new Articulo("A001", "Portátil", 1000.0, 20.0, 30);

        // Corrección: Asegurar que los parámetros coinciden con el constructor en Pedido.java
        pedidoEstandar = new Pedido(1, clienteEstandar, articulo, 2);
        pedidoPremium = new Pedido(2, clientePremium, articulo, 2);
    }

    @Test
    void testCalcularPrecioTotalClienteEstandar() {
        double esperado = (1000.0 * 2) + 20.0; // Precio base * cantidad + gastos de envío
        assertEquals(esperado, pedidoEstandar.calcularPrecioTotal(), 0.01);
    }

    @Test
    void testCalcularPrecioTotalClientePremium() {
        double gastosEnvioConDescuento = 20.0 * 0.8; // 20% de descuento en gastos de envío
        double esperado = (1000.0 * 2) + gastosEnvioConDescuento;
        assertEquals(esperado, pedidoPremium.calcularPrecioTotal(), 0.01);
    }
}