package org.javaenjoyers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.javaenjoyers.DAO.ArticuloDAO;
import org.javaenjoyers.DAO.ClienteDAO;
import org.javaenjoyers.DAO.DAOFactory;
import org.javaenjoyers.DAO.PedidoDAO;
import org.javaenjoyers.controlador.*;
import org.javaenjoyers.vista.*;
import org.javaenjoyers.vista.JavaFX.VistaArtJavaFX;
import org.javaenjoyers.vista.JavaFX.VistaCliJavaFX;
import org.javaenjoyers.vista.JavaFX.VistaJavaFX;
import org.javaenjoyers.vista.JavaFX.VistaPedJavaFX;

import java.sql.Connection;


public class MainJavaFX extends Application {
    @Override
    public void start(Stage stage){
        String tipoBD = "jpa";
        EntityManagerFactory emf;
        EntityManager em = null;
        Connection conexion;

        if(tipoBD.equals("jpa")){
            emf = Persistence.createEntityManagerFactory("EventJPA"); // Usa el nombre de tu unidad de persistencia
            em = emf.createEntityManager();
        }

        //⚠️HABRÁ QUE BORRAR LAS VISTAS AL FINAL
        Vista vistaAntigua = new Vista();
        //⚠️HABRÁ QUE BORRAR LAS VISTAS AL FINAL

        Herramientas herramientas = new Herramientas(vistaAntigua, em);

        conexion = Utilidad.establecerConexion(herramientas);

        DAOFactory factory = DAOFactory.getDAOFactory(tipoBD, conexion, herramientas);
        ClienteDAO cliDAO = factory.getClienteDAO();
        ArticuloDAO artDAO = factory.getArticuloDAO();
        PedidoDAO pedDAO = factory.getPedidoDAO();

        //⚠️HABRÁ QUE BORRAR LAS VISTAS AL FINAL
        VistaClientes vistaCli = new VistaClientes(herramientas);
        VistaArticulos vistaArt = new VistaArticulos(herramientas);
        VistaPedidos vistaPed = new VistaPedidos(herramientas);
        //⚠️HABRÁ QUE BORRAR LAS VISTAS AL FINAL

        ControladorCliente contrCli = new ControladorCliente(cliDAO, herramientas, vistaCli);
        ControladorArticulo contrArt = new ControladorArticulo(artDAO, herramientas, vistaArt);
        ControladorPedido contrPed = new ControladorPedido(cliDAO, herramientas, pedDAO, vistaCli, vistaPed);
        Controlador controlador = new Controlador(contrArt, contrCli, contrPed, herramientas, vistaAntigua);

        StackPane rootPane = new StackPane();
        VistaCliJavaFX vistaCliJFX = new VistaCliJavaFX(contrCli, controlador, contrPed);
        VistaJavaFX vista = new VistaJavaFX(contrArt, contrCli, controlador, contrPed, rootPane);
        VistaArtJavaFX vistaArtJFX = new VistaArtJavaFX(contrArt, controlador, vista);
        VistaPedJavaFX vistaPedJFX = new VistaPedJavaFX(controlador, vista, contrPed);

        //⚠️CREO LAS REFERENCIAS CIRULARES
        contrCli.setVistaClientesJFX(vistaCliJFX);
        contrCli.setVistaJFX(vista);
        contrCli.setContrPed(contrPed);
        contrArt.setVistaArticulosJFX(vistaArtJFX);
        contrArt.setVistaJFX(vista);
        contrPed.setVistaPedidosJFX(vistaPedJFX);
        contrPed.setVistaJFX(vista);
        contrPed.setContrCli(contrCli);
        controlador.setVistaJFX(vista);
        //

        Scene scene = new Scene(vista.crearVista(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Online Store - GUI");
        stage.show();


        /*Label label = new Label("¡Hola desde JavaFX!");
        Group root = new Group();
        Scene scene = new Scene(root,600,400, Color.CORNFLOWERBLUE);
        Image logo = new Image("logo.png");
        stage.getIcons().add(logo);
        stage.setTitle("Online Store - JavaFX");

        //stage.setHeight(800);
        //stage.setWidth(800);

        Text text = new Text();
        text.setText("Qué pasa peñíscolaaaa!!");
        text.setX(10);
        text.setY(70);
        text.setFont(Font.font("Calibri Light", 26));
        text.setFill(Color.DARKRED);


        Image imatge = new Image("logo.png");
        ImageView vista = new ImageView(imatge);
        vista.setX(250);
        vista.setY(300);
        vista.setScaleX(80);


        root.getChildren().add(text);
        root.getChildren().add(vista);

        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args){
        launch(args);
    }
}


//bibliografía
/*https://www.youtube.com/watch?v=Ope4icw6bVk&list=PLZPZq0r_RZOM-8vJA3NQFZB7JroDcMwev&index=3&ab_channel=BroCode
* https://www.youtube.com/watch?v=As7TEjqJ3Ao&list=PLZPZq0r_RZOM-8vJA3NQFZB7JroDcMwev&index=3&ab_channel=BroCode
*https://www.youtube.com/watch?v=7nlU3_kEjTE&list=PLZPZq0r_RZOM-8vJA3NQFZB7JroDcMwev&index=4&ab_channel=BroCode
* */