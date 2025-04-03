package org.javaenjoyers.controladores;

import org.javaenjoyers.vistas.Vista;

/**
 * Clase Controlador, se encargará de realizar la lógica e interactuar entre el Modelo y la Vista
 */

public class Controlador {

    private final Vista vista;

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    /**
     * Mostrará en la vista los prints de bienvenida
     */
    public void bienvenida(){
        vista.bienvenida();
    }

    /**
     * Mostrará en la Vista el menú principal
     */
    public void inicio(){
        vista.menuPrincipal();
    }
}