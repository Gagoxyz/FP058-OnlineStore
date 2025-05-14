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

        Text titulo = new Text("Tienda Online");
        titulo.setFont(Font.font("Calibri", 26));
        titulo.setFill(Color.DARKRED);

        Text subtitulo = new Text("Gestión de pedidos");
        subtitulo.setFont(Font.font("Calibri", 20));
        subtitulo.setFill(Color.DARKRED);

        Text linea = new Text("¿Qué quieres hacer?");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button botAdd = new Button("Añadir pedido");
        botAdd.setOnAction(e -> vistaJFX.cambiarVista(clientePedidoNuevo()));

        Button botDel = new Button("Eliminar pedido");
        botDel.setOnAction(e -> vistaJFX.cambiarVista(pedirNumeroPedido()));

        Button botShowPend = new Button("Mostrar pedidos pendientes");
        botShowPend.setOnAction(e -> vistaJFX.cambiarVista(tipoBusqueda(true)));

        Button botshowEnv = new Button("Mostrar pedidos enviados");
        botshowEnv.setOnAction(e -> vistaJFX.cambiarVista(tipoBusqueda(false)));

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> controlador.menuPrincipal());

        VBox.setMargin(titulo, new Insets(0, 0, 0, 0));
        VBox.setMargin(subtitulo, new Insets(7, 0, 0, 0));
        VBox.setMargin(linea, new Insets(0, 0, 0, 0));
        VBox.setMargin(botAdd, new Insets(15, 0, 0, 0));
        VBox.setMargin(botDel, new Insets(15, 0, 0, 0));
        VBox.setMargin(botShowPend, new Insets(15, 0, 0, 0));
        VBox.setMargin(botshowEnv, new Insets(15, 0, 0, 0));
        VBox.setMargin(atras, new Insets(10, 0, 0, 0));

        root.getChildren().addAll(titulo, subtitulo, linea, botAdd, botDel, botShowPend, botshowEnv, atras);

        return root;
    }

    public Parent clientePedidoNuevo(){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Cliente del pedido");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button botAdd = new Button("Registrado");
        botAdd.setOnAction(e -> vistaJFX.cambiarVista(comprobarEmail(1, true)));

        Button botShow = new Button("Nuevo");
        botShow.setOnAction(e -> contrPed.crearClienteFX());

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> contrPed.menuPedidosFX());

        root.getChildren().addAll(linea, botAdd, botShow, atras);

        return root;
    }

    public Parent comprobarEmail(int caso, boolean pendiente){
        VBox form = new VBox();
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Indica el email del cliente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        TextField email = new TextField();
        email.setPromptText("Email");

        Button buscar;
        if(caso == 1){
            buscar = new Button("Buscar cliente");
        }else{
            buscar = new Button("Buscar pedidos");
        }
        buscar.setOnAction(e -> contrPed.comprobarEmail(email.getText(), caso, pendiente));

        Button cancelar = new Button("Cancelar");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        form.getChildren().addAll(linea, email, buscar, cancelar);

        return form;
    }

    public Parent inexistente(Cliente cliente, int caso){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text();
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");

        switch (caso){
            case 1:
                linea = new Text("Este cliente no exite");
                aceptar.setOnAction(e -> vistaJFX.cambiarVista(comprobarEmail(1, true)));
                break;
            case 2:
                linea = new Text("Este artículo no exite");
                aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirCodigo(cliente)));
                break;
            case 3:
                linea = new Text("Este pedido no exite");
                aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirNumeroPedido()));
                break;
        }

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent pedirCodigo(Cliente cliente){
        VBox form = new VBox();
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Indica el código del artículo");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        TextField codigo = new TextField();
        codigo.setPromptText("Código");

        Button buscar = new Button("Buscar artículo");
        buscar.setOnAction(e -> contrPed.buscarArticulo(codigo.getText(), cliente));

        Button cancelar = new Button("Cancelar");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        form.getChildren().addAll(linea, codigo, buscar, cancelar);

        return form;
    }

    public Parent infoPedido(Cliente cliente, Articulo articulo){
        VBox form = new VBox(10);
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Indica la cantidad del artículo cliente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        TextField cantidad = new TextField();
        cantidad.setPromptText("Cantidad");

        Button crear = new Button("Crear pedido");
        crear.setOnAction(e -> contrPed.crearPedido(cliente, articulo, cantidad.getText()));

        Button cancelar = new Button("Cancelar");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        form.getChildren().addAll(linea, cantidad, crear, cancelar);

        return form;
    }

    public Parent pedidoInsertado(){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Pedido añadido correctamente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrPed.menuPedidosFX());

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent clienteInsertado(Cliente cliente){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Cliente añadido correctamente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirCodigo(cliente)));

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent formatoIncorrecto(Cliente cliente, Articulo articulo, int caso){
        VBox mensaje = new VBox(10);
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea;
        Button aceptar = new Button("Volver a introducir el valor");
        String objeto;

        if(caso == 1){
            objeto = "la cantidad";
            aceptar.setOnAction(e -> vistaJFX.cambiarVista(infoPedido(cliente, articulo)));
        }else{
            objeto = "el número de pedido";
            aceptar.setOnAction(e -> vistaJFX.cambiarVista(pedirNumeroPedido()));
        }
        linea = new Text("No has introducido " + objeto + " correctamente");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent pedirNumeroPedido(){
        VBox form = new VBox();
        form.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("Indica el número del pedido que quieres eliminar");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        TextField numero = new TextField();
        numero.setPromptText("Número");

        Button buscar = new Button("Buscar artículo");
        buscar.setOnAction(e -> contrPed.buscarPedido(numero.getText()));

        Button cancelar = new Button("Cancelar");
        cancelar.setOnAction(e -> contrPed.menuPedidosFX());

        form.getChildren().addAll(linea, numero, buscar, cancelar);

        return form;
    }

    public Parent eliminacionPedido(int caso){
        VBox mensaje = new VBox();
        mensaje.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text();
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button aceptar = new Button("OK");
        aceptar.setOnAction(e -> contrPed.menuPedidosFX());

        if(caso == 1){
            linea = new Text("El pedido ya ha sido enviado, no se puede eliminar.");
        }else{
            linea = new Text("El pedido se ha eliminado correctamente.");
        }

        mensaje.getChildren().addAll(linea, aceptar);

        return mensaje;
    }

    public Parent tipoBusqueda(boolean pendiente){
        VBox root = new VBox(20);
        root.setPadding(new Insets(50, 50, 50, 50));

        Text linea = new Text("¿Qué pedidos quieres ver?");
        linea.setFont(Font.font("Calibri", 15));
        linea.setFill(Color.DARKRED);

        Button botTodos = new Button("Todos");
        botTodos.setOnAction(e -> contrPed.mostrarPedidos(pendiente, null));

        Button botCliente = new Button("Por cliente");
        botCliente.setOnAction(e -> vistaJFX.cambiarVista(comprobarEmail(2, pendiente)));

        Button atras = new Button("Atrás");
        atras.setOnAction(e -> vistaJFX.cambiarVista(menuPedidos()));

        root.getChildren().addAll(linea, botTodos, botCliente, atras);

        return root;
    }

    public Parent mostrarPedidos(List<Pedido> listaPed, String email){
        VBox muestra = new VBox(5);
        muestra.setPadding(new Insets(50, 50, 50, 50));

        Text linea;

        if(email == null){
            linea= new Text("Todos los pedidos\n\n");
        }else{
            linea = new Text("Pedidos de " + email + "\n\n");
        }
        linea.setFont(Font.font("Calibri", 20));
        linea.setFill(Color.DARKRED);

        muestra.getChildren().add(linea);

        for(Pedido i : listaPed){
            Text numPresentacion = new Text("Número de pedido: ");
            numPresentacion.setFont(Font.font("Calibri", 15));

            Text cliPresentacion = new Text("Cliente: ");
            cliPresentacion.setFont(Font.font("Calibri", 15));

            Text artPresentacion = new Text("Artículo: ");
            artPresentacion.setFont(Font.font("Calibri", 15));

            Text canPresentacion = new Text("Cantidad: ");
            canPresentacion.setFont(Font.font("Calibri", 15));

            Text tiePresentacion = new Text("Fecha y hora: ");
            tiePresentacion.setFont(Font.font("Calibri", 15));

            //--------------------------------

            int numPedInt = i.getNumPedido();
            String numPedString = String.valueOf(numPedInt);
            Text numValor = new Text(numPedString);
            numValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text cliValor = new Text(i.getCliente().getEmail());
            cliValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            Text artValor = new Text(i.getArticulo().getCodigoProducto());
            artValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            int cantidadInt = i.getCantidad();
            String cantidadString = String.valueOf(cantidadInt);
            Text canValor = new Text(cantidadString);
            canValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String tiempoString = i.getFechaHora().format(formato);
            Text tieValor = new Text(tiempoString);
            tieValor.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

            TextFlow numero = new TextFlow(numPresentacion, numValor);
            TextFlow cliente = new TextFlow(cliPresentacion, cliValor);
            TextFlow articulo = new TextFlow(artPresentacion, artValor);
            TextFlow cantidad = new TextFlow(canPresentacion, canValor);
            TextFlow tiempo = new TextFlow(tiePresentacion, tieValor);

            Text separacion = new Text("\n----------------------------------\n");

            muestra.getChildren().addAll(numero, cliente, articulo, cantidad,tiempo, separacion);
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
