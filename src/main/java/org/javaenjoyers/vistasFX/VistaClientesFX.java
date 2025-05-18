package org.javaenjoyers.vistasFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.javaenjoyers.controladoresFX.ClienteControladorFX;

public class VistaClientesFX {

    private final Stage stage;
    private final ClienteControladorFX clienteControladorFX;

    public VistaClientesFX(Stage stage, ClienteControladorFX clienteControladorFX) {
        this.stage = stage;
        this.clienteControladorFX = clienteControladorFX;
    }

    public void mostrar() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titulo = new Label("Gestión de Clientes");

        Button btnAgregar = new Button("1. Añadir cliente");
        Button btnMostrar = new Button("2. Mostrar clientes");
        Button btnEstandar = new Button("3. Mostrar clientes Estándar");
        Button btnPremium = new Button("4. Mostrar clientes Premium");
        Button btnVolver = new Button("0. Atrás");

        // Asociar botones a las acciones del controlador FX
        btnAgregar.setOnAction(e -> clienteControladorFX.procesarOpcion("1"));
        btnMostrar.setOnAction(e -> clienteControladorFX.procesarOpcion("2"));
        btnEstandar.setOnAction(e -> clienteControladorFX.procesarOpcion("3"));
        btnPremium.setOnAction(e -> clienteControladorFX.procesarOpcion("4"));

        // Volver al menú principal
        btnVolver.setOnAction(e -> new VistaFX(stage).mostrarMenuPrincipal());

        layout.getChildren().addAll(
                titulo, btnAgregar, btnMostrar, btnEstandar, btnPremium, btnVolver
        );

        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
        stage.setTitle("Clientes");
        stage.show();
    }
}
