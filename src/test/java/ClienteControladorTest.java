import JavaEnjoyers.controlador.ClienteControlador;
import JavaEnjoyers.modelo.Cliente;
import JavaEnjoyers.modelo.ClienteEstandar;
import JavaEnjoyers.modelo.ClientePremium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteControladorTest {
    private ClienteControlador clienteControlador;

    @BeforeEach
    void setUp() {
        clienteControlador = new ClienteControlador();
    }

    @Test
    void testAgregarClienteEstandar() {
        Cliente cliente = new ClienteEstandar("Juan Pérez", "Calle A", "12345678A", "juan@example.com");

        clienteControlador.agregarCliente(cliente); // Ahora pasa el objeto a un cliente

        Cliente clienteEncontrado = clienteControlador.obtenerClientePorEmail("juan@example.com"); // Cambiado aquí
        assertNotNull(clienteEncontrado);
        assertInstanceOf(ClienteEstandar.class, clienteEncontrado);
        assertEquals("Juan Pérez", clienteEncontrado.getNombre());
    }

    @Test
    void testAgregarClientePremium() {
        Cliente cliente = new ClientePremium("María López", "Calle B", "87654321B", "maria@example.com");

        clienteControlador.agregarCliente(cliente); // Se pasa un objeto al cliente

        Cliente clienteEncontrado = clienteControlador.obtenerClientePorEmail("maria@example.com"); // Cambiado aquí
        assertNotNull(clienteEncontrado);
        assertInstanceOf(ClientePremium.class, clienteEncontrado);
        assertEquals("María López", clienteEncontrado.getNombre());
    }
}