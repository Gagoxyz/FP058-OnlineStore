package org.javaenjoyers.vistasFX;

import javafx.application.Application;
import javafx.stage.Stage;

public class VistaPrincipal extends Application {

    @Override
    public void start(Stage stage) {
        VistaFX vistaFX = new VistaFX(stage); // instanciamos nuestro menú principal
        vistaFX.mostrarMenuPrincipal();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        // Conectar con el controlador ya existente
//        FXControlador controlador = new FXControlador();
//
//        // Crear botón
//        Button boton = new Button("Haz clic");
//        boton.setOnAction(e -> controlador.accionDesdeVista());
//
//        // Layout básico
//        VBox root = new VBox(10);
//        root.getChildren().add(boton);
//
//        // Configuración escena
//        Scene scene = new Scene(root, 300, 200);
//        stage.setTitle("Mi prueba JavaFX");
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
}
