package org.javaenjoyers.vista.JavaFX;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.javaenjoyers.controlador.ControladorArticulo;
import org.javaenjoyers.controlador.ControladorCliente;
import org.javaenjoyers.controlador.ControladorPedido;

public class VistaJavaFX {
    private ControladorCliente contrCli;
    private ControladorArticulo contrArt;
    private ControladorPedido contrPed;
    private StackPane rootPane;

    public VistaJavaFX(ControladorArticulo contrArt, ControladorCliente contrCli, ControladorPedido contrPed, StackPane rootPane) {
        this.contrArt = contrArt;
        this.contrCli = contrCli;
        this.contrPed = contrPed;
        this.rootPane = rootPane;
    }

    public Parent crearVista(){
        VBox menuPrincipal = new VBox(10);
        menuPrincipal.setPadding(new Insets(50, 50, 50, 50));

        Label titulo = new Label("Online Store");
        titulo.getStyleClass().add("titulo");

        Label linea = new Label("¿Qué quieres hacer?");
        linea.getStyleClass().add("subtitulo");

        Button botCli = new Button("Gestión de clientes");
        botCli.setOnAction(e -> contrCli.menuClientesJFX());

        Button botArt = new Button("Gestión de artículos");
        botArt.setOnAction(e -> contrArt.menuArticulosJFX());

        Button botPed = new Button("Gestión de pedidos");
        botPed.setOnAction(e -> contrPed.menuPedidosFX());

        Button salir = new Button("Salir");
        salir.getStyleClass().add("buttonAtras");
        salir.setOnAction(e -> Platform.exit());

        VBox.setMargin(titulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(linea, new Insets(5, 0, 0, 0));
        VBox.setMargin(botCli, new Insets(20, 0, 0, 0));
        VBox.setMargin(botArt, new Insets(20, 0, 0, 0));
        VBox.setMargin(botPed, new Insets(20, 0, 0, 0));
        VBox.setMargin(salir, new Insets(20, 0, 0, 0));

        menuPrincipal.getChildren().addAll(titulo, linea, botCli, botArt, botPed, salir);

        //---------------------------------

        Image logo = new Image(getClass().getResourceAsStream("/logoOS.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);
        //logoView.setSmooth(true);

        VBox logoBox = new VBox(logoView);
        logoBox.setAlignment(Pos.TOP_RIGHT);
        logoBox.setPadding(new Insets(50, 50, 0, 0));

        HBox contenido = new HBox(10);
        contenido.getChildren().addAll(menuPrincipal, logoBox);
        contenido.setAlignment(Pos.TOP_CENTER);

        rootPane.getChildren().clear();
        rootPane.getChildren().add(contenido);

        return rootPane;
    }

    public void cambiarVista(Parent nuevaVista){
        rootPane.getChildren().setAll(nuevaVista);
    }
}
