package org.javaenjoyers.vistasFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.javaenjoyers.controladoresFX.PedidoControladorFX;

public class VistaPedidosFX {

    private final Stage stage;
    private final PedidoControladorFX pedidoControladorFX;

    public VistaPedidosFX(Stage stage, PedidoControladorFX pedidoControladorFX) {
        this.stage = stage;
        this.pedidoControladorFX = pedidoControladorFX;
    }

    public void mostrar() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titulo = new Label("Gestión de Pedidos");

        Button btnAgregar = new Button("1. Añadir pedido");
        Button btnEliminar = new Button("2. Eliminar pedido");
        Button btnPendientes = new Button("3. Mostrar pedidos pendientes");
        Button btnEnviados = new Button("4. Mostrar pedidos enviados");
        Button btnResumen = new Button("5. Resumen de pedidos");
        Button btnVolver = new Button("0. Atrás");

        btnAgregar.setOnAction(e -> pedidoControladorFX.procesarOpcion("1"));
        btnEliminar.setOnAction(e -> pedidoControladorFX.procesarOpcion("2"));
        btnPendientes.setOnAction(e -> pedidoControladorFX.procesarOpcion("3"));
        btnEnviados.setOnAction(e -> pedidoControladorFX.procesarOpcion("4"));
        btnResumen.setOnAction(e -> pedidoControladorFX.procesarOpcion("5"));
        btnVolver.setOnAction(e -> new VistaFX(stage).mostrarMenuPrincipal());

        layout.getChildren().addAll(
                titulo, btnAgregar, btnEliminar, btnPendientes, btnEnviados, btnResumen, btnVolver
        );

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Pedidos");
        stage.show();
    }
}
