package org.javaenjoyers.controlador;

import org.javaenjoyers.vista.Vista;

public class Herramientas {
    private Vista vista;

    public Herramientas(Vista vista) {
        this.vista = vista;
    }

    public int comprobarOpcion(int eleccion, int inicio, int fin){
        while(eleccion < inicio || eleccion > fin){
            eleccion = vista.intInvalido();
        }
        return eleccion;
    }

    public String repetirString(int opcion){
        String mensaje = "";
        switch (opcion){
            case 1:
                mensaje = vista.repeticionString("Ya existe, introduce uno distinto");
                break;
            case 2:
                mensaje = vista.repeticionString("No se ha encontrado, vuelve a introducirlo (o 0 para volver atrás)");
                break;
        }
        return mensaje;
    }

    public int errorIntEntrada(){
        return vista.errorIntEntrada();
    }

    public float errorFloatEntrada(){
        return vista.errorFloatEntrada();
    }

    public void enviarMensaje(int opcion, String mensaje){
        if(opcion == 1){
            mensaje = "\n*** La acción se ha llevado a cabo con éxito.***\n";
        }
        vista.enviarMensaje(mensaje);
    }

    public String pedirString(String mensaje){
        return vista.pedirString(mensaje);
    }

    public int repetirInt(){
        return vista.repetirInt();
    }
}
