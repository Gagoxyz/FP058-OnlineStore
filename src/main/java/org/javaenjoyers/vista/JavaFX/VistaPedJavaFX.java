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
import org.javaenjoyers.controlador.ControladorPedido;
import org.javaenjoyers.modelo.Articulo;
import org.javaenjoyers.modelo.Cliente;
import org.javaenjoyers.modelo.Pedido;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class VistaPedJavaFX {
    private ControladorPedido contrPed;
    private Controlador controlador;
    private VistaJavaFX vistaJFX;

    public VistaPedJavaFX(Controlador controlador, VistaJavaFX vistaJFX, ControladorPedido contrPed) {
        this.controlador = controlador;
        this.vistaJFX = vistaJFX;
        this.contrPed = contrPed;
    }

    public Parent menuPedidos(){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Label subtitulo = new Label("Gestión de pedidos");
        subtitulo.getStyleClass().add("titulo");

        Label linea = new Label("¿Qué quieres hacer?");
        linea.getStyleClass().add("subtitulo");

        Button botAdd = new Button("Añadir pedido");
        botAdd.setOnAction(e -> vistaJFX.cambiarVista(clientePedidoNuevo()));

        Button botDel = new Button("Eliminar pedido");
        botDel.setOnAction(e -> vistaJFX.cambiarVista(pedirNumeroPedido()));

        Button botShowPend = new Button("Mostrar pedidos pendientes");
        botShowPend.setOnAction(e -> vistaJFX.cambiarVista(tipoBusqueda(true)));

        Button botshowEnv = new Button("Mostrar pedidos enviados");
        botshowEnv.setOnAction(e -> vistaJFX.cambiarVista(tipoBusqueda(false)));

        Button atras = new Button("Atrás");
        atras.getStyleClass().add("buttonAtras");
        atras.setOnAction(e -> controlador.menuPrincipal());

        VBox.setMargin(subtitulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(linea, new Insets(0, 0, 0, 0));
        VBox.setMargin(botAdd, new Insets(15, 0, 0, 0));
        VBox.setMargin(botDel, new Insets(15, 0, 0, 0));
        VBox.setMargin(botShowPend, new Insets(15, 0, 0, 0));
        VBox.setMargin(botshowEnv, new Insets(15, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(subtitulo, linea, botAdd, botDel, botShowPend, botshowEnv, atras);

        return root;
    }

    public Parent clientePedidoNuevo(){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Cliente del pedido");

        Button botReg = new Button("Registrado");
        botReg.setOnAction(e -> vistaJFX.cambiarVista(comprobarEmail(1, true)));

        Button botNew = new Button("Nuevo");
        botNew.setOnAction(e -> contrPed.crearClienteFX());

        Button atras = new Button("Atrás");
        atras.getStyleClass().add("buttonAtras");
        atras.setOnAction(e -> contrPed.menuPedidosFX());

        VBox.setMargin(botReg, new Insets(10, 0, 0, 0));
        VBox.setMargin(botNew, new Insets(10, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(linea, botReg, botNew, atras);

        return root;
    }

    public Parent comprobarEmail(int caso, boolean pendiente){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Indica el email del cliente");

        TextField email = new TextField();
        email.setPromptText("Email");
        email.getStyleClass().add("entrada");

        Button buscar;
        if(caso == 1){
            buscar = new Button("Buscar cliente");
        }else{
            buscar = new Button("Buscar pedidos");
        }
        buscar.setOnAction(e -> contrPed.comprobarEmail(email.getText(), caso, pendiente));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        VBox.setMargin(buscar, new Insets(20, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(20, 0, 0, 0));

        form.getChildren().addAll(linea, email, buscar, cancelar);

        return form;
    }

    public Parent inexistente(Cliente cliente, int caso){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label();

        Button aceptar = new Button("OK");

        switch (caso){
            case 1:
                linea = new Label("Este cliente no exite");
                aceptar.setOnAction(e -> vistaJFX.cambiarVista(comprobarEmail(1, true)));
                break;
            case 2:
                linea = new Label("Este artículo no exite");
                aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirCodigo(cliente)));
                break;
            case 3:
                linea = new Label("Este pedido no exite");
                aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirNumeroPedido()));
                break;
        }
        linea.getStyleClass().add("subtitulo");

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent pedirCodigo(Cliente cliente){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Indica el código del artículo");

        TextField codigo = new TextField();
        codigo.setPromptText("Código");
        codigo.getStyleClass().add("entrada");

        Button buscar = new Button("Buscar artículo");
        buscar.setOnAction(e -> contrPed.buscarArticulo(codigo.getText(), cliente));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        VBox.setMargin(buscar, new Insets(20, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(20, 0, 0, 0));

        form.getChildren().addAll(linea, codigo, buscar, cancelar);

        return form;
    }

    public Parent infoPedido(Cliente cliente, Articulo articulo){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Indica la cantidad del artículo cliente");

        TextField cantidad = new TextField();
        cantidad.setPromptText("Cantidad");
        cantidad.getStyleClass().add("entrada");

        Button crear = new Button("Crear pedido");
        crear.setOnAction(e -> contrPed.crearPedido(cliente, articulo, cantidad.getText()));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        VBox.setMargin(crear, new Insets(20, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(20, 0, 0, 0));

        form.getChildren().addAll(linea, cantidad, crear, cancelar);

        return form;
    }

    public Parent pedidoInsertado(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Pedido añadido correctamente");
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrPed.menuPedidosFX());

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent clienteInsertado(Cliente cliente){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Cliente añadido correctamente");
        linea.getStyleClass().add("subtitulo");

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirCodigo(cliente)));

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent formatoIncorrecto(Cliente cliente, Articulo articulo, int caso){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea;
        Button aceptar = new Button("Volver a introducir el valor");
        String objeto;

        if(caso == 1){
            objeto = "la cantidad";
            aceptar.setOnAction(e -> vistaJFX.cambiarVista(infoPedido(cliente, articulo)));
        }else{
            objeto = "el número de pedido";
            aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirNumeroPedido()));
        }
        linea = new Label("No has introducido bien " + objeto);
        linea.getStyleClass().add("subtitulo");

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent pedirNumeroPedido(){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("Indica el número del pedido que quieres eliminar");

        TextField numero = new TextField();
        numero.setPromptText("Número");
        numero.getStyleClass().add("entrada");

        Button buscar = new Button("Buscar artículo");
        buscar.setOnAction(e -> contrPed.buscarPedido(numero.getText()));

        Button cancelar = new Button("Cancelar");
        cancelar.getStyleClass().add("buttonAtras");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        VBox.setMargin(buscar, new Insets(20, 0, 0, 0));
        VBox.setMargin(cancelar, new Insets(20, 0, 0, 0));

        form.getChildren().addAll(linea, numero, buscar, cancelar);

        return form;
    }

    public Parent eliminacionPedido(int caso){
        VBox mensaje = new VBox();
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Label linea;

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrPed.menuPedidosFX());

        if(caso == 1){
            linea = new Label("El pedido ya ha sido enviado, no se puede eliminar.");
        }else{
            linea = new Label("El pedido se ha eliminado correctamente.");
        }
        linea.getStyleClass().add("subtitulo");

        VBox.setMargin(linea, new Insets(30, 0, 0, 0));
        VBox.setMargin(aceptar, new Insets(30, 0, 0, 0));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent tipoBusqueda(boolean pendiente){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Label linea = new Label("¿Qué pedidos quieres ver?");

        Button botTodos = new Button("Todos");
        botTodos.setOnAction(e -> contrPed.mostrarPedidos(pendiente, null));

        Button botCliente = new Button("Por cliente");
        botCliente.setOnAction(e -> vistaJFX.cambiarVista(comprobarEmail(2, pendiente)));

        Button atras = new Button("Atrás");
        atras.getStyleClass().add("buttonAtras");
        atras.setOnAction(e -> vistaJFX.cambiarVista(menuPedidos()));

        VBox.setMargin(botTodos, new Insets(20, 0, 0, 0));
        VBox.setMargin(botCliente, new Insets(20, 0, 0, 0));
        VBox.setMargin(atras, new Insets(20, 0, 0, 0));

        root.getChildren().addAll(linea, botTodos, botCliente, atras);

        return root;
    }

    public Parent mostrarPedidos(List<Pedido> listaPed, String email){
        VBox muestra = new VBox(5);
        muestra.setPadding(new Insets(50, 50, 50, 50));

        Label linea;

        if(email == null){
            linea= new Label("Todos los pedidos\n\n");
        }else{
            linea = new Label("Pedidos de " + email + "\n\n");
        }
        linea.getStyleClass().add("subtitulo");
        VBox.setMargin(linea, new Insets(0, 0, 30, 0));

        muestra.getChildren().add(linea);

        for(Pedido i : listaPed){
            Label numPresentacion = new Label("Número de pedido: ");

            Label cliPresentacion = new Label("Cliente: ");

            Label artPresentacion = new Label("Artículo: ");

            Label canPresentacion = new Label("Cantidad: ");

            Label tiePresentacion = new Label("Fecha y hora: ");

            //--------------------------------

            int numPedInt = i.getNumPedido();
            String numPedString = String.valueOf(numPedInt);
            Label numValor = new Label(numPedString);
            numValor.getStyleClass().add("destacado");

            Label cliValor = new Label(i.getCliente().getEmail());
            cliValor.getStyleClass().add("destacado");

            Label artValor = new Label(i.getArticulo().getCodigoProducto());
            artValor.getStyleClass().add("destacado");

            int cantidadInt = i.getCantidad();
            String cantidadString = String.valueOf(cantidadInt);
            Label canValor = new Label(cantidadString);
            canValor.getStyleClass().add("destacado");

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String tiempoString = i.getFechaHora().format(formato);
            Label tieValor = new Label(tiempoString);
            tieValor.getStyleClass().add("destacado");

            GridPane tabla = new GridPane();
            tabla.setHgap(10);
            tabla.setVgap(10);
            int row = 0;
            tabla.add(numPresentacion, 0, row);
            tabla.add(numValor, 1, row++);
            tabla.add(cliPresentacion, 0, row);
            tabla.add(cliValor, 1, row++);
            tabla.add(artPresentacion, 0, row);
            tabla.add(artValor, 1, row++);
            tabla.add(canPresentacion, 0, row);
            tabla.add(canValor, 1, row++);
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
        atras.setOnAction(e -> vistaJFX.cambiarVista(menuPedidos()));

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
