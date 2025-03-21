package org.javaenjoyers;

import org.javaenjoyers.controladores.Controlador;
import org.javaenjoyers.modelos.*;
import org.javaenjoyers.vistas.Vista;

public class Main {
    public static void main(String[] args) {

        // Creamos MVC (Modelo Vista Controlador)
        Modelo modelo = new Modelo ();
        Vista vista = new Vista(null);
        Controlador controlador = new Controlador(modelo, vista);
        vista.setControlador(controlador);

        // Realizamos la carga de datos (varios objetos de prueba)
        CargaDatos.cargarDatos(modelo);

        // Iniciamos el controlador
        controlador.bienvenida();
        controlador.inicio();
    }
}

