package org.javaenjoyers.vista.JavaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.javaenjoyers.controlador.Controlador;
import org.javaenjoyers.controlador.ControladorCliente;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;

import java.util.List;

public class VistaCliJavaFX {
    private ControladorCliente contrCli;

    private Controlador controlador;

    public VistaCliJavaFX(ControladorCliente contrCli, Controlador controlador) {
        this.contrCli = contrCli;
        this.controlador = controlador;
    }

    public Parent menuClientes(){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Text titulo = new Text("Tienda Online");
        titulo.setFont(Font.font("Calibri", 26));
        titulo.setFill(Color.DARKRED);

        Text subtitulo = new Text("Gestión de clientes");
        subtitulo.setFont(Font.font("Calibri", 20));
        subtitulo.setFill(Color.DARKRED);

        Text linea = new Text("¿Qué quieres hacer?");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button botAdd = new Button("Añadir cliente");
        botAdd.setOnAction(e -> contrCli.pedirEmail(false));

        Button botShow = new Button("Mostrar todos los clientes");
        botShow.setOnAction(e -> contrCli.mostrarClientes(1));

        Button botShowEst = new Button("Mostrar clientes estándar");
        botShowEst.setOnAction(e -> contrCli.mostrarClientes(2));

        Button botShowPre = new Button("Mostrar clientes premium");
        botShowPre.setOnAction(e -> contrCli.mostrarClientes(3));

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> controlador.menuPrincipal());

        VBox.setMargin(titulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(subtitulo, new Insets(7, 0, 0, 0));
        VBox.setMargin(linea, new Insets(0, 0, 0, 0));
        VBox.setMargin(botAdd, new Insets(15, 0, 0, 0));
        VBox.setMargin(botShow, new Insets(10, 0, 0, 0));
        VBox.setMargin(botShowEst, new Insets(10, 0, 0, 0));
        VBox.setMargin(botShowPre, new Insets(10, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(titulo, subtitulo, linea, botAdd, botShow, botShowEst, botShowPre, atras);

        return root;
    }

    public Parent emailCliente(boolean paraPedido){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Indica el email del nuevo cliente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        TextField email = new TextField();
        email.setPromptText("Email");

        Button crear = new Button("Crear cliente");
        crear.setOnAction(e -> contrCli.comprobarEmail(email.getText(), paraPedido));

        form.getChildren().addAll(linea, email, crear);

        return form;
    }

    public Parent emailIncorrecto(boolean paraPedido){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Este cliente ya exite");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrCli.pedirEmail(paraPedido));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent infoCliente(String email, boolean paraPedido){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea1 = new Text("Indica el nombre del nuevo cliente");
        linea1.setFont(Font.font("Calibri", 15));
        linea1.setFill(Color.DARKRED);

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre");

        Text linea2 = new Text("Indica el NIF del nuevo cliente");
        linea2.setFont(Font.font("Calibri", 15));
        linea2.setFill(Color.DARKRED);

        TextField nif = new TextField();
        nif.setPromptText("NIF");

        Text linea3 = new Text("Indica el domicilio del nuevo cliente");
        linea3.setFont(Font.font("Calibri", 15));
        linea3.setFill(Color.DARKRED);

        TextField domicilio = new TextField();
        domicilio.setPromptText("Domicilio");

        Text linea4 = new Text("Indica de qué tipo es el nuevo cliente");
        linea4.setFont(Font.font("Calibri", 15));
        linea4.setFill(Color.DARKRED);

        RadioButton estandar = new RadioButton("Estándar");
        RadioButton premium = new RadioButton("Premium");
        ToggleGroup grupoTipo = new ToggleGroup();
        estandar.setToggleGroup(grupoTipo);
        premium.setToggleGroup(grupoTipo);
        estandar.setSelected(true);

        boolean tipo = false;
        if(estandar.isSelected()){
            tipo = true;
        }
        boolean tipoEstandar = tipo;

        Button crear = new Button("Crear cliente");
        crear.setOnAction(e -> contrCli.crearCliente(email, nombre.getText(), nif.getText(), domicilio.getText(), tipoEstandar, paraPedido));

        form.getChildren().addAll(linea1, nombre, linea2, nif, linea3, domicilio, linea4, estandar, premium, crear);

        return form;
    }

    public Parent clienteInsertado(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Cliente añadido correctamente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrCli.menuClientesJFX());

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent mostrarListaClientes(List<Cliente> listaCli, int opcion){
        VBox muestra = new VBox(5);
        muestra.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text();
        switch (opcion){
            case 1:
                linea = new Text("Todos los lientes\n\n");
                break;
            case 2:
                linea = new Text("Clientes estándar\n\n");
                break;
            case 3:
                linea = new Text("Clientes premium\n\n");
                break;
        }
        linea.setFont(Font.font("Calibri", 20));
        linea.setFill(Color.DARKRED);

        muestra.getChildren().add(linea);

        for(Cliente i : listaCli){
            Text emaPresentacion = new Text("Email: ");
            emaPresentacion.setFont(Font.font("Calibri", 15));

            Text nomPresentacion = new Text("Nombre: ");
            nomPresentacion.setFont(Font.font("Calibri", 15));

            Text nifPresentacion = new Text("NIF: ");
            nifPresentacion.setFont(Font.font("Calibri", 15));

            Text domPresentacion = new Text("Domicilio: ");
            domPresentacion.setFont(Font.font("Calibri", 15));

            Text tipoPresentacion = new Text("Tipo: ");
            tipoPresentacion.setFont(Font.font("Calibri", 15));

            //--------------------------------

            Text emaValor = new Text(i.getEmail());
            emaValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text nomValor = new Text(i.getNombre());
            nomValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text nifValor = new Text(i.getNif());
            nifValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text domValor = new Text(i.getDomicilio());
            domValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text tipoValor;
            if(i instanceof Estandar){
                tipoValor = new Text("Estándar");
                tipoValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
            }else{
                tipoValor = new Text("Premium");
                tipoValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
            }

            TextFlow email = new TextFlow(emaPresentacion, emaValor);
            TextFlow nombre = new TextFlow(nomPresentacion, nomValor);
            TextFlow nif = new TextFlow(nifPresentacion, nifValor);
            TextFlow domicilio = new TextFlow(domPresentacion, domValor);
            TextFlow tipo = new TextFlow(tipoPresentacion, tipoValor);

            Text separacion = new Text("\n----------------------------------\n");

            muestra.getChildren().addAll(email, nombre, nif, domicilio, tipo, separacion);
        }

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(muestra);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> contrCli.menuClientesJFX());

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
