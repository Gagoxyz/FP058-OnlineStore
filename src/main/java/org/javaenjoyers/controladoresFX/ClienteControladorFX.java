package org.javaenjoyers.controladoresFX;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.javaenjoyers.modelos.*;
import java.util.Objects;
import java.util.Optional;

public class ClienteControladorFX {

    private final ClienteModelo clienteModelo;

    public ClienteControladorFX(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
    }

    /**
     * Procesa la opción elegida desde la interfaz FX
     */
    public void procesarOpcion(String opcionVista) {
        switch (opcionVista) {
            case "1":
                nuevoCliente();
                break;
            case "2":
                mostrarClientes();
                break;
            case "3":
                mostrarClientesEstandar();
                break;
            case "4":
                mostrarClientesPremium();
                break;
            default:
                mostrarMensaje("Opción incorrecta.");
        }
    }

    /**
     * Diálogo para añadir nuevo cliente
     */
    public void nuevoCliente() {
        String tipoCliente = solicitarDato("Indica tipo cliente (Estandar/Premium):");
        if (tipoCliente == null) return;
        tipoCliente = tipoCliente.trim().toUpperCase();

        if (!Objects.equals(tipoCliente, "ESTANDAR") && !Objects.equals(tipoCliente, "PREMIUM")) {
            mostrarMensaje("Tipo de cliente incorrecto.");
            return;
        }

        String nif = solicitarDato("Indica el NIF:");
        String nombre = solicitarDato("Indica el Nombre:");
        String email = solicitarDato("Indica el Email:");
        String domicilio = solicitarDato("Indica el Domicilio:");

        if (nif == null || nombre == null || email == null || domicilio == null) return;

        Cliente nuevoCliente = Objects.equals(tipoCliente, "ESTANDAR") ?
                new Estandar(email, nombre, domicilio, nif) :
                new Premium(email, nombre, domicilio, nif);

        clienteModelo.agregarCliente(nuevoCliente);

        if (clienteModelo.obtenerClientes().stream().anyMatch(c -> c.getEmail().equals(email))) {
            mostrarMensaje("Cliente registrado correctamente.");
        } else {
            mostrarMensaje("No se registró nuevo cliente. Ya existe en la BBDD.");
        }
    }

    public void mostrarClientes() {
        mostrarMensaje(clienteModelo.obtenerClientes().toString());
    }

    public void mostrarClientesEstandar() {
        mostrarMensaje(clienteModelo.obtenerClientesEstandar().toString());
    }

    public void mostrarClientesPremium() {
        mostrarMensaje(clienteModelo.obtenerClientesPremium().toString());
    }

    private String solicitarDato(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrada de datos");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
