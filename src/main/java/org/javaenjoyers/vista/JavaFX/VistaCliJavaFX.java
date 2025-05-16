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
import org.javaenjoyers.controlador.ControladorCliente;
import org.javaenjoyers.controlador.ControladorPedido;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Estandar;

import java.util.List;

public class VistaCliJavaFX {
    private ControladorCliente contrCli;
    private ControladorPedido contrPed;
    private Controlador controlador;

    public VistaCliJavaFX(ControladorCliente contrCli, Controlador controlador, ControladorPedido contrPed) {
        this.contrCli = contrCli;
        this.controlador = controlador;
        this.contrPed = contrPed;
    }

    public Parent menuClientes(){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Label subtitulo = new Label("Gestión de clientes");
        subtitulo.getStyleClass().add("titulo");

        Label linea = new Label("¿Qué quieres hacer?");
        linea.getStyleClass().add("subtitulo");

        Button botAdd = new Button("Añadir cliente");
        botAdd.setOnAction(e -> contrCli.pedirEmail(false));

        Button botShow = new Button("Mostrar todos los clientes");
        botShow.setOnAction(e -> contrCli.mostrarClientes(1));

        Button botShowEst = new Button("Mostrar clientes estándar");
        botShowEst.setOnAction(e -> contrCli.mostrarClientes(2));

        Button botShowPre = new Button("Mostrar clientes premium");
        botShowPre.setOnAction(e -> contrCli.mostrarClientes(3));

        Button atras = new Button("Atrás");
        atras.getStyleClass().add("buttonAtras");
        atras.setOnAction(e -> controlador.menuPrincipal());

        VBox.setMargin(subtitulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(linea, new Insets(0, 0, 0, 0));
        VBox.setMargin(botAdd, new Insets(15, 0, 0, 0));
        VBox.setMargin(botShow, new Insets(10, 0, 0, 0));
        VBox.setMargin(botShowEst, new Insets(10, 0, 0, 0));
        VBox.setMargin(botShowPre, new Insets(10, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(subtitulo, linea, botAdd, botShow, botShowEst, botShowPre, atras);

        return root;
    }

    public Parent emailCliente(boolean paraPedido){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Indica el email del nuevo cliente");

        TextField email = new TextField();
        email.setPromptText("Email");
        email.getStyleClass().add("entrada");

        Button crear = new Button("Crear cliente");
        crear.setOnAction(e -> contrCli.comprobarEmail(email.getText(), paraPedido));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        if(paraPedido){
            cancelar.setOnAction(e -> contrPed.menuPedidosFX());
        }else{
            cancelar.setOnAction(e -> contrCli.menuClientesJFX());
        }

        VBox.setMargin(crear, new Insets(20, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(20, 0, 0, 0));

        form.getChildren().addAll(linea, email, crear, cancelar);

        return form;
    }

    public Parent emailIncorrecto(boolean paraPedido){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Este cliente ya exite");
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrCli.pedirEmail(paraPedido));

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent infoCliente(String email, boolean paraPedido){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea1 = new Label("Indica el nombre del nuevo cliente");

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre");
        nombre.getStyleClass().add("entrada");

        Label linea2 = new Label("Indica el NIF del nuevo cliente");

        TextField nif = new TextField();
        nif.setPromptText("NIF");
        nif.getStyleClass().add("entrada");

        Label linea3 = new Label("Indica el domicilio del nuevo cliente");

        TextField domicilio = new TextField();
        domicilio.setPromptText("Domicilio");
        domicilio.getStyleClass().add("entrada");

        Label linea4 = new Label("Indica de qué tipo es el nuevo cliente");

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

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        if(paraPedido){
            cancelar.setOnAction(e -> contrPed.menuPedidosFX());
        }else{
            cancelar.setOnAction(e -> contrCli.menuClientesJFX());
        }

        VBox.setMargin(crear, new Insets(10, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(10, 0, 0, 0));

        form.getChildren().addAll(linea1, nombre, linea2, nif, linea3, domicilio, linea4, estandar, premium, crear, cancelar);

        return form;
    }

    public Parent clienteInsertado(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Cliente añadido correctamente");
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrCli.menuClientesJFX());

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent mostrarListaClientes(List<Cliente> listaCli, int opcion){
        VBox muestra = new VBox(5);
        muestra.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label();
        switch (opcion){
            case 1:
                linea = new Label("Todos los lientes");
                break;
            case 2:
                linea = new Label("Clientes estándar");
                break;
            case 3:
                linea = new Label("Clientes premium");
                break;
        }
        linea.getStyleClass().add("subtitulo");
        VBox.setMargin(linea, new Insets(0, 0, 30, 0));

        muestra.getChildren().add(linea);

        for(Cliente i : listaCli){
            Label emaPresentacion = new Label("Email: ");

            Label nomPresentacion = new Label("Nombre: ");

            Label nifPresentacion = new Label("NIF: ");

            Label domPresentacion = new Label("Domicilio: ");

            Label tipoPresentacion = new Label("Tipo: ");

            //--------------------------------

            Label emaValor = new Label(i.getEmail());
            emaValor.getStyleClass().add("destacado");

            Label nomValor = new Label(i.getNombre());
            nomValor.getStyleClass().add("destacado");

            Label nifValor = new Label(i.getNif());
            nifValor.getStyleClass().add("destacado");

            Label domValor = new Label(i.getDomicilio());
            domValor.getStyleClass().add("destacado");

            Label tipoValor;
            if(i instanceof Estandar){
                tipoValor = new Label("Estándar");
                tipoValor.getStyleClass().add("destacado");
            }else{
                tipoValor = new Label("Premium");
                tipoValor.getStyleClass().add("destacado");
            }

            GridPane tabla = new GridPane();
            tabla.setHgap(10);
            tabla.setVgap(10);
            int row = 0;
            tabla.add(emaPresentacion, 0, row);
            tabla.add(emaValor, 1, row++);
            tabla.add(nomPresentacion, 0, row);
            tabla.add(nomValor, 1, row++);
            tabla.add(nifPresentacion, 0, row);
            tabla.add(nifValor, 1, row++);
            tabla.add(domPresentacion, 0, row);
            tabla.add(domValor, 1, row++);
            tabla.add(tipoPresentacion, 0, row);
            tabla.add(tipoValor, 1, row++);

            Separator separacion = new Separator();

            muestra.getChildren().addAll(tabla, separacion);
            VBox.setMargin(separacion, new Insets(17, 0, 17, 0));
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
