package org.javaenjoyers.vista.JavaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.javaenjoyers.controlador.Controlador;
import org.javaenjoyers.controlador.ControladorArticulo;
import org.javaenjoyers.modelo.Articulo;

import java.util.List;

public class VistaArtJavaFX {
    private ControladorArticulo contrArt;
    private Controlador controlador;
    private VistaJavaFX vistaJFX;

    public VistaArtJavaFX(ControladorArticulo contrArt, Controlador controlador, VistaJavaFX vistaJFX) {
        this.contrArt = contrArt;
        this.controlador = controlador;
        this.vistaJFX = vistaJFX;
    }

    public Parent menuArticulos(){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Label subtitulo = new Label("Gestión de artículos");
        subtitulo.getStyleClass().add("titulo");

        Label linea = new Label("¿Qué quieres hacer?");
        linea.getStyleClass().add("subtitulo");

        Button botAdd = new Button("Añadir artículo");
        botAdd.setOnAction(e -> contrArt.pedirCodigo());

        Button botShow = new Button("Mostrar todos los artículos");
        botShow.setOnAction(e -> contrArt.mostrarArticulos());

        Button atras = new Button("Atrás");
        atras.getStyleClass().add("buttonAtras");
        atras.setOnAction(e -> controlador.menuPrincipal());

        VBox.setMargin(subtitulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(linea, new Insets(0, 0, 0, 0));
        VBox.setMargin(botAdd, new Insets(15, 0, 0, 0));
        VBox.setMargin(botShow, new Insets(15, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(subtitulo, linea, botAdd, botShow, atras);

        return root;
    }

    public Parent codigoArticulo(){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Indica el código del nuevo artículo");

        TextField codigo = new TextField();
        codigo.setPromptText("Código");
        codigo.getStyleClass().add("entrada");

        Button crear = new Button("Crear artículo");
        crear.setOnAction(e -> contrArt.comprobarCodigo(codigo.getText()));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        cancelar.setOnAction(e -> contrArt.menuArticulosJFX());

        VBox.setMargin(crear, new Insets(20, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(20, 0, 0, 0));

        form.getChildren().addAll(linea, codigo, crear, cancelar);

        return form;
    }

    public Parent codigoIncorreco(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Este artículo ya exite");
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrArt.pedirCodigo());

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent infoArticulo(String codigo){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea1 = new Label("Indica la descripción del nuevo artículo");

        TextField descripcion = new TextField();
        descripcion.setPromptText("Descripción");
        descripcion.getStyleClass().add("entrada");

        Label linea2 = new Label("Indica el precio de venta del nuevo artículo");

        TextField precio = new TextField();
        precio.setPromptText("Precio de venta");
        precio.getStyleClass().add("entrada");

        Label linea3 = new Label("Indica los gastos de envío del nuevo artículo");

        TextField gastos = new TextField();
        gastos.setPromptText("Gastos de envío");
        gastos.getStyleClass().add("entrada");

        Label linea4 = new Label("Indica el tiempo de preparación del nuevo artículo");

        TextField tiempo = new TextField();
        tiempo.setPromptText("Tiempo de preparación");
        tiempo.getStyleClass().add("entrada");

        Button crear = new Button("Crear artículo");
        crear.setOnAction(e -> contrArt.crearArticulo(codigo, descripcion.getText(), precio.getText(), gastos.getText(), tiempo.getText()));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        cancelar.setOnAction(e -> contrArt.menuArticulosJFX());

        VBox.setMargin(crear, new Insets(10, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(10, 0, 0, 0));

        form.getChildren().addAll(linea1, descripcion, linea2, precio, linea3, gastos, linea4, tiempo, crear, cancelar);

        return form;
    }

    public Parent formatoIncorrecto(int x, String codigo){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label();
        switch (x){
            case 0:
                linea = new Label("El precio está mal introducido");
                break;
            case 1:
                linea = new Label("Los gastos están mal introducidos");
                break;
            case 2:
                linea = new Label("El tiempo está mal introducido");
                break;
        }
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("Volver a introducir la información del artículo");
        aceptar.setOnAction(e -> vistaJFX.cambiarVista(infoArticulo(codigo)));

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent articuloInsertado(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Artículo añadido correctamente");
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrArt.menuArticulosJFX());

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent mostrarListaArticulos(List<Articulo> listaArt){
        VBox muestra = new VBox(5);
        muestra.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Todos los artículos\n\n");
        linea.getStyleClass().add("subtitulo");
        VBox.setMargin(linea, new Insets(0, 0, 30, 0));

        muestra.getChildren().add(linea);

        for(Articulo i : listaArt){
            Label codPresentacion = new Label("Código: ");
            codPresentacion.setStyle("-fx-font-weight: normal;");

            Label descPresentacion = new Label("Descripción: ");
            descPresentacion.setStyle("-fx-font-weight: normal;");

            Label prePresentacion = new Label("Precio de venta: ");
            prePresentacion.setStyle("-fx-font-weight: normal;");

            Label gasPresentacion = new Label("Gastos de envío: ");
            gasPresentacion.setStyle("-fx-font-weight: normal;");

            Label tiePresentacion = new Label("Tiempo de preparación: ");
            tiePresentacion.setStyle("-fx-font-weight: normal;");

            //--------------------------------

            Label codValor = new Label(i.getCodigoProducto());
            codValor.getStyleClass().add("destacado");

            Label descValor = new Label(i.getDescripcion());
            descValor.getStyleClass().add("destacado");

            double precioDouble = i.getPrecioVenta();
            String precioString = String.valueOf(precioDouble);
            Label preValor = new Label(precioString + " €");
            preValor.getStyleClass().add("destacado");

            double gastosDouble = i.getGastosEnvio();
            String gastosString = String.valueOf(gastosDouble);
            Label gasValor = new Label(gastosString + " €");
            gasValor.getStyleClass().add("destacado");

            int tiempoInt = i.getTiempoPrepEnvio();
            String tiempoString = String.valueOf(tiempoInt);
            Label tieValor = new Label(tiempoString + " minutos");
            tieValor.getStyleClass().add("destacado");

            GridPane tabla = new GridPane();
            tabla.setHgap(10);
            tabla.setVgap(10);
            int row = 0;
            tabla.add(codPresentacion, 0, row);
            tabla.add(codValor, 1, row++);
            tabla.add(descPresentacion, 0, row);
            tabla.add(descValor, 1, row++);
            tabla.add(prePresentacion, 0, row);
            tabla.add(preValor, 1, row++);
            tabla.add(gasPresentacion, 0, row);
            tabla.add(gasValor, 1, row++);
            tabla.add(tiePresentacion, 0, row);
            tabla.add(tieValor, 1, row++);

            Separator separacion = new Separator();

            muestra.getChildren().addAll(tabla, separacion);
            VBox.setMargin(separacion, new Insets(17, 0, 17, 0));
        }

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(muestra);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> contrArt.menuArticulosJFX());

        HBox barraSuperior = new HBox();
        barraSuperior.setAlignment(Pos.TOP_RIGHT);
        barraSuperior.setPadding(new Insets(20, 40, 20, 20));
        barraSuperior.getChildren().add(atras);

        BorderPane layout = new BorderPane();
        layout.setTop(barraSuperior);
        layout.setCenter(scroll);

        return layout;
    }
}
