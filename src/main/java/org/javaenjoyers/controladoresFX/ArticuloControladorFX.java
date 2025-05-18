package org.javaenjoyers.controladoresFX;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.javaenjoyers.modelos.Articulo;
import org.javaenjoyers.modelos.ArticuloModelo;

import java.util.Optional;

public class ArticuloControladorFX {

    private final ArticuloModelo articuloModelo;

    public ArticuloControladorFX(ArticuloModelo articuloModelo) {
        this.articuloModelo = articuloModelo;
    }

    public void procesarOpcion(String opcionVista) {
        switch (opcionVista) {
            case "1":
                nuevoArticulo();
                break;
            case "2":
                mostrarArticulos();
                break;
            default:
                mostrarMensaje("Opción no válida.");
        }
    }

    public void nuevoArticulo() {
        String codigoProducto = solicitarDato("Código del artículo:");
        if (codigoProducto == null) return;
        codigoProducto = codigoProducto.toUpperCase();

        Articulo existente = articuloModelo.obtenerArticuloPorCodigo(codigoProducto);
        if (existente != null) {
            mostrarMensaje("Código de producto ya registrado.");
            return;
        }

        String descripcion = solicitarDato("Descripción del artículo:");
        if (descripcion == null) return;

        Float precioVenta = solicitarFloat("Precio de venta (ej. 4.99):");
        if (precioVenta == null) return;

        Float gastosEnvio = solicitarFloat("Gastos de envío:");
        if (gastosEnvio == null) return;

        Integer tiempoPrep = solicitarEntero("Tiempo de preparación del envío (minutos):");
        if (tiempoPrep == null) return;

        Articulo nuevoArticulo = new Articulo(codigoProducto, descripcion, precioVenta, gastosEnvio, tiempoPrep);
        articuloModelo.agregarArticulo(nuevoArticulo);
        mostrarMensaje("Artículo registrado correctamente.");
    }

    public void mostrarArticulos() {
        String listado = articuloModelo.obtenerArticulos().toString();
        mostrarMensaje(listado);
    }

    // Utilidades JavaFX
    private String solicitarDato(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrada de datos");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        Optional<String> result = dialog.showAndWait();
        return result.filter(s -> !s.trim().isEmpty()).orElse(null);
    }

    private Float solicitarFloat(String mensaje) {
        String input = solicitarDato(mensaje);
        if (input == null) return null;

        try {
            input = input.replace(',', '.');
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            mostrarMensaje("Dato no válido. Usa el formato correcto, por ejemplo '4.99'.");
            return null;
        }
    }

    private Integer solicitarEntero(String mensaje) {
        String input = solicitarDato(mensaje);
        if (input == null) return null;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            mostrarMensaje("Dato no válido. Introduce un número entero.");
            return null;
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
