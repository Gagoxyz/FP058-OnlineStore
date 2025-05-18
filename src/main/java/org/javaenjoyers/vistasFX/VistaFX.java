package org.javaenjoyers.vistasFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.javaenjoyers.modelos.*;
import org.javaenjoyers.controladoresFX.*;

public class VistaFX {

    private final Stage stage;
    private final VistaArticulosFX vistaArticulosFX;
    private final VistaClientesFX vistaClientesFX;
    private final VistaPedidosFX vistaPedidosFX;

    public VistaFX(Stage stage) {
        this.stage = stage;

        // Crear modelos
        ArticuloModelo articuloModelo = new ArticuloModelo();
        ClienteModelo clienteModelo = new ClienteModelo();
        PedidoModelo pedidoModelo = new PedidoModelo();

        // Crear controladores FX
        ClienteControladorFX clienteControladorFX = new ClienteControladorFX(clienteModelo);
        ArticuloControladorFX articuloControladorFX = new ArticuloControladorFX(articuloModelo);
        PedidoControladorFX pedidoControladorFX = new PedidoControladorFX(pedidoModelo, articuloModelo, clienteModelo);

        // Crear vistas FX con los controladores FX correctos
        this.vistaArticulosFX = new VistaArticulosFX(stage);
        this.vistaClientesFX = new VistaClientesFX(stage, clienteControladorFX);
        this.vistaPedidosFX = new VistaPedidosFX(stage, pedidoControladorFX);
    }

    public void mostrarMenuPrincipal() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Button btnArticulos = new Button("Gestión Artículos");
        Button btnClientes = new Button("Gestión Clientes");
        Button btnPedidos = new Button("Gestión Pedidos");

        btnArticulos.setOnAction(e -> vistaArticulosFX.mostrar());
        btnClientes.setOnAction(e -> vistaClientesFX.mostrar());
        btnPedidos.setOnAction(e -> vistaPedidosFX.mostrar());

        layout.getChildren().addAll(btnArticulos, btnClientes, btnPedidos);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Menú Principal");
        stage.show();
    }
}
