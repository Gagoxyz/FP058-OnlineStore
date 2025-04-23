package org.javaenjoyers.controlador;

import jakarta.persistence.EntityManager;
import org.javaenjoyers.vista.Vista;

public class Herramientas {
    private Vista vista;
    private EntityManager entityManager; // ✅ Nuevo atributo

    // ✅ Constructor actualizado
    public Herramientas(Vista vista, EntityManager entityManager) {
        this.vista = vista;
        this.entityManager = entityManager;
    }

    // ✅ Nuevo getter
    public EntityManager getEntityManager() {
        return entityManager;
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
                mensaje = vista.repeticionString("Ya existe, introduce uno distinto: ");
                break;
            case 2:
                mensaje = vista.repeticionString("No se ha encontrado, vuelve a introducirlo (o 0 para volver atrás): ");
                break;
        }
        return mensaje;
    }

    public int errorIntEntrada(){
        return vista.errorIntEntrada();
    }

    public double errorDoubleEntrada(){
        return vista.errorDoubleEntrada();
    }

    public void enviarMensaje(int opcion, String mensaje){
        switch(opcion){
            case 0:
                break;
            case 1:
                mensaje = "\n*** La acción se ha llevado a cabo con éxito.***\n";
                break;
            case 2:
                mensaje = "\nError de conexión con la base de datos.";
                break;
        }
        vista.enviarMensaje(mensaje);
    }

    public String pedirString(String mensaje){
        return vista.pedirString(mensaje);
    }

    public int repetirInt(){
        return vista.repetirInt();
    }

    // en persistence.xml /<property name="jakarta.persistence.schema-generation.database.action" value="update"/> para que no salgan los datos en la tabla.
}