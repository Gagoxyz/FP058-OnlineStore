package org.javaenjoyers.controladoresFX;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.javaenjoyers.modelos.*;
import java.util.List;
import java.util.Optional;

public class PedidoControladorFX {

    private final PedidoModelo pedidoModelo;
    private final ArticuloModelo articuloModelo;
    private final ClienteModelo clienteModelo;

    public PedidoControladorFX(PedidoModelo pedidoModelo, ArticuloModelo articuloModelo, ClienteModelo clienteModelo) {
        this.pedidoModelo = pedidoModelo;
        this.articuloModelo = articuloModelo;
        this.clienteModelo = clienteModelo;
    }

    public void procesarOpcion(String opcionVista) {
        switch (opcionVista) {
            case "1" -> nuevoPedido();
            case "2" -> eliminarPedido();
            case "3" -> mostrarPedidosPendientesEnvio();
            case "4" -> mostrarPedidosEnviados();
            case "5" -> resumenPedidos();
            default -> mostrarAlerta("Opción incorrecta.");
        }
    }

    private void nuevoPedido() {
        String email = pedirDato("Introduce el email del cliente:");
        if (email == null) return;

        Cliente cliente = pedidoModelo.obtenerClientePorEmail(email);
        if (cliente == null) {
            mostrarAlerta("Cliente no encontrado. Debes registrarlo primero.");
            return;
        }

        String codigoProducto = pedirDato("Introduce el código del producto:");
        if (codigoProducto == null) return;
        Articulo articulo = articuloModelo.obtenerArticuloPorCodigo(codigoProducto.toUpperCase());

        if (articulo == null) {
            mostrarAlerta("Código de producto no válido.");
            return;
        }

        String cantidadStr = pedirDato("Cantidad:");
        if (cantidadStr == null) return;

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (Exception e) {
            mostrarAlerta("Cantidad no válida. Debe ser un número entero.");
            return;
        }

        Pedido pedido = new Pedido(cliente, articulo, cantidad);
        pedidoModelo.agregarPedido(pedido);
        mostrarInformacion("Pedido registrado correctamente.");
    }

    private void eliminarPedido() {
        List<Pedido> pedidos = pedidoModelo.obtenerPedidos();
        if (pedidos.isEmpty()) {
            mostrarAlerta("No hay pedidos registrados.");
            return;
        }

        mostrarInformacion(pedidos.toString());

        String numero = pedirDato("Introduce el número del pedido a eliminar:");
        if (numero == null || numero.equals("0")) return;

        int numeroPedido;
        try {
            numeroPedido = Integer.parseInt(numero);
        } catch (Exception e) {
            mostrarAlerta("Número no válido.");
            return;
        }

        Pedido pedido = pedidoModelo.obtenerPedidoPorNumPedido(numeroPedido);
        if (pedido == null) {
            mostrarAlerta("No existe un pedido con ese número.");
            return;
        }

        if (pedidoModelo.verificarTiempoPedido(pedido)) {
            mostrarAlerta("El pedido ya ha sido enviado. No se puede eliminar.");
        } else {
            pedidoModelo.eliminarPedido(pedido);
            mostrarInformacion("Pedido eliminado correctamente.");
        }
    }

    private void mostrarPedidosPendientesEnvio() {
        String email = pedirDato("Introduce el email del cliente:");
        if (email == null) return;

        Cliente cliente = pedidoModelo.obtenerClientePorEmail(email);
        if (cliente == null) {
            mostrarAlerta("Cliente no encontrado.");
            return;
        }

        List<Pedido> pendientes = pedidoModelo.obtenerPedidosPendientesEnvio(cliente);
        if (pendientes.isEmpty()) {
            mostrarInformacion("No hay pedidos pendientes de envío.");
        } else {
            mostrarInformacion(pendientes.toString());
        }
    }

    private void mostrarPedidosEnviados() {
        String email = pedirDato("Introduce el email del cliente:");
        if (email == null) return;

        Cliente cliente = pedidoModelo.obtenerClientePorEmail(email);
        if (cliente == null) {
            mostrarAlerta("Cliente no encontrado.");
            return;
        }

        List<Pedido> enviados = pedidoModelo.obtenerPedidosEnviados(cliente);
        if (enviados.isEmpty()) {
            mostrarInformacion("No hay pedidos enviados.");
        } else {
            mostrarInformacion(enviados.toString());
        }
    }

    private void resumenPedidos() {
        List<Pedido> pedidos = pedidoModelo.obtenerPedidos();
        if (pedidos.isEmpty()) {
            mostrarInformacion("No hay pedidos registrados.");
        } else {
            mostrarInformacion(pedidos.toString());
        }
    }

    // Utilidades JavaFX
    private String pedirDato(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrada requerida");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        Optional<String> result = dialog.showAndWait();
        return result.filter(s -> !s.trim().isEmpty()).orElse(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atención");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
