package org.javaenjoyers.vista.JavaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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

        Text titulo = new Text("Tienda Online");
        titulo.setFont(Font.font("Calibri", 26));
        titulo.setFill(Color.DARKRED);

        Text subtitulo = new Text("Gestión de artículos");
        subtitulo.setFont(Font.font("Calibri", 20));
        subtitulo.setFill(Color.DARKRED);

        Text linea = new Text("¿Qué quieres hacer?");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button botAdd = new Button("Añadir artículo");
        botAdd.setOnAction(e -> contrArt.pedirCodigo());

        Button botShow = new Button("Mostrar todos los artículos");
        botShow.setOnAction(e -> contrArt.mostrarArticulos());

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> controlador.menuPrincipal());

        VBox.setMargin(titulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(subtitulo, new Insets(7, 0, 0, 0));
        VBox.setMargin(linea, new Insets(0, 0, 0, 0));
        VBox.setMargin(botAdd, new Insets(15, 0, 0, 0));
        VBox.setMargin(botShow, new Insets(15, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(titulo, subtitulo, linea, botAdd, botShow, atras);

        return root;
    }

    public Parent codigoArticulo(){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Indica el código del nuevo artículo");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        TextField codigo = new TextField();
        codigo.setPromptText("Código");

        Button crear = new Button("Crear artículo");
        crear.setOnAction(e -> contrArt.comprobarCodigo(codigo.getText()));

        form.getChildren().addAll(linea, codigo, crear);

        return form;
    }

    public Parent codigoIncorreco(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Este artículo ya exite");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrArt.pedirCodigo());

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent infoArticulo(String codigo){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea1 = new Text("Indica la descripción del nuevo artículo");
        linea1.setFont(Font.font("Calibri", 15));
        linea1.setFill(Color.DARKRED);

        TextField descripcion = new TextField();
        descripcion.setPromptText("Descripción");

        Text linea2 = new Text("Indica el precio de venta del nuevo artículo");
        linea2.setFont(Font.font("Calibri",  15));
        linea2.setFill(Color.DARKRED);

        TextField precio = new TextField();
        precio.setPromptText("Precio de venta");

        Text linea3 = new Text("Indica los gastos de envío del nuevo artículo");
        linea3.setFont(Font.font("Calibri", 15));
        linea3.setFill(Color.DARKRED);

        TextField gastos = new TextField();
        gastos.setPromptText("Gastos de envío");

        Text linea4 = new Text("Indica el tiempo de preparación del nuevo artículo");
        linea4.setFont(Font.font("Calibri", 15));
        linea4.setFill(Color.DARKRED);

        TextField tiempo = new TextField();
        tiempo.setPromptText("Tiempo de preparación");

        Button crear = new Button("Crear artículo");
        crear.setOnAction(e -> contrArt.crearArticulo(codigo, descripcion.getText(), precio.getText(), gastos.getText(), tiempo.getText()));

        form.getChildren().addAll(linea1, descripcion, linea2, precio, linea3, gastos, linea4, tiempo, crear);

        return form;
    }

    public Parent formatoIncorrecto(int x, String codigo){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text();
        switch (x){
            case 0:
                linea = new Text("No has introducido el precio de venta correctamente");
                break;
            case 1:
                linea = new Text("No has introducido los gastos de envío correctamente");
                break;
            case 2:
                linea = new Text("No has introducido el tiempo de preparación correctamente");
                break;
        }
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("Volver a introducir la información del artículo");
        aceptar.setOnAction(e -> vistaJFX.cambiarVista(infoArticulo(codigo)));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent articuloInsertado(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Artículo añadido correctamente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrArt.menuArticulosJFX());

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent mostrarListaArticulos(List<Articulo> listaArt){
        VBox muestra = new VBox(5);
        muestra.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Todos los artículos\n\n");
        linea.setFont(Font.font("Calibri", 20));
        linea.setFill(Color.DARKRED);

        muestra.getChildren().add(linea);

        for(Articulo i : listaArt){
            Text codPresentacion = new Text("Código: ");
            codPresentacion.setFont(Font.font("Calibri", 15));

            Text descPresentacion = new Text("Descripción: ");
            descPresentacion.setFont(Font.font("Calibri", 15));

            Text prePresentacion = new Text("Precio de venta: ");
            prePresentacion.setFont(Font.font("Calibri", 15));

            Text gasPresentacion = new Text("Gastos de envío: ");
            gasPresentacion.setFont(Font.font("Calibri", 15));

            Text tiePresentacion = new Text("Tiempo de preparación: ");
            tiePresentacion.setFont(Font.font("Calibri", 15));

            //--------------------------------

            Text codValor = new Text(i.getCodigoProducto());
            codValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text descValor = new Text(i.getDescripcion());
            descValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            double precioDouble = i.getPrecioVenta();
            String precioString = String.valueOf(precioDouble);
            Text preValor = new Text(precioString + " €");
            preValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            double gastosDouble = i.getGastosEnvio();
            String gastosString = String.valueOf(gastosDouble);
            Text gasValor = new Text(gastosString + " €");
            gasValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            int tiempoInt = i.getTiempoPrepEnvio();
            String tiempoString = String.valueOf(tiempoInt);
            Text tieValor = new Text(tiempoString + " minutos");
            tieValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            TextFlow codigo = new TextFlow(codPresentacion, codValor);
            TextFlow descripcion = new TextFlow(descPresentacion, descValor);
            TextFlow precio = new TextFlow(prePresentacion, preValor);
            TextFlow gastos = new TextFlow(gasPresentacion, gasValor);
            TextFlow tiempo = new TextFlow(tiePresentacion, tieValor);

            Text separacion = new Text("\n----------------------------------\n");

            muestra.getChildren().addAll(codigo, descripcion, precio, gastos, tiempo, separacion);
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
