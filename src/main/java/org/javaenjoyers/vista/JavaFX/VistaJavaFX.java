package org.javaenjoyers.vista.JavaFX;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.javaenjoyers.controlador.Controlador;
import org.javaenjoyers.controlador.ControladorArticulo;
import org.javaenjoyers.controlador.ControladorCliente;
import org.javaenjoyers.controlador.ControladorPedido;

public class VistaJavaFX {
    private Controlador controlador;
    private ControladorCliente contrCli;
    private ControladorArticulo contrArt;
    private ControladorPedido contrPed;
    private StackPane rootPane;

    public VistaJavaFX(ControladorArticulo contrArt, ControladorCliente contrCli, Controlador controlador, ControladorPedido contrPed, StackPane rootPane) {
        this.contrArt = contrArt;
        this.contrCli = contrCli;
        this.controlador = controlador;
        this.contrPed = contrPed;
        this.rootPane = rootPane;
    }

    public Parent crearVista(){
        VBox menuPrincipal = new VBox(10);
        menuPrincipal.setPadding(new Insets(50, 50, 50, 50));

        Text titulo = new Text("Tienda Online");
        titulo.setFont(Font.font("Calibri", 26));
        titulo.setFill(Color.DARKRED);

        Text linea = new Text("¿Qué quieres hacer?");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button botCli = new Button("Gestión de clientes");
        botCli.setOnAction(e -> contrCli.menuClientesJFX());

        Button botArt = new Button("Gestión de artículos");
        botArt.setOnAction(e -> contrArt.menuArticulosJFX());

        Button botPed = new Button("Gestión de pedidos");
        botPed.setOnAction(e -> contrPed.menuPedidosFX());

        Button salir = new Button("Salir");
        salir.setOnAction(e -> Platform.exit());

        VBox.setMargin(titulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(linea, new Insets(7, 0, 0, 0));
        VBox.setMargin(botCli, new Insets(25, 0, 0, 0));
        VBox.setMargin(botArt, new Insets(20, 0, 0, 0));
        VBox.setMargin(botPed, new Insets(20, 0, 0, 0));
        VBox.setMargin(salir, new Insets(20, 0, 0, 0));

        menuPrincipal.getChildren().addAll(titulo, linea, botCli, botArt, botPed, salir);

        rootPane.getChildren().clear();
        rootPane.getChildren().add(menuPrincipal);

        return rootPane;
    }

    public void cambiarVista(Parent nuevaVista){
        rootPane.getChildren().setAll(nuevaVista);
    }
}
