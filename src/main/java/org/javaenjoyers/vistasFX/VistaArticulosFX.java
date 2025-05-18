package org.javaenjoyers.vistasFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.javaenjoyers.controladoresFX.ArticuloControladorFX;
import org.javaenjoyers.modelos.ArticuloModelo;

public class VistaArticulosFX {

    private final Stage stage;
    private final ArticuloControladorFX controladorFX;

    public VistaArticulosFX(Stage stage) {
        this.stage = stage;

        // Instanciamos el modelo y el controlador
        ArticuloModelo modelo = new ArticuloModelo();
        this.controladorFX = new ArticuloControladorFX(modelo);
    }

    public void mostrar() {
        // Layout y estilo
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Label titulo = new Label("Gestión de Artículos");

        // Botones del menú
        Button btnAgregar = new Button("1. Añadir artículo");
        Button btnMostrar = new Button("2. Mostrar artículos");
        Button btnVolver = new Button("0. Volver al menú principal");

        // Acciones de los botones
        btnAgregar.setOnAction(e -> controladorFX.procesarOpcion("1"));
        btnMostrar.setOnAction(e -> controladorFX.procesarOpcion("2"));
        btnVolver.setOnAction(e -> new VistaFX(stage).mostrarMenuPrincipal());

        layout.getChildren().addAll(titulo, btnAgregar, btnMostrar, btnVolver);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Artículos");
        stage.show();
    }
}
