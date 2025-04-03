package org.javaenjoyers.controladores;

import org.javaenjoyers.modelos.Cliente;
import org.javaenjoyers.modelos.Estandar;
import org.javaenjoyers.modelos.Modelo;
import org.javaenjoyers.modelos.Premium;
import org.javaenjoyers.vistas.Vista;

import java.util.Objects;

public class ClienteControlador {

    private Modelo modelo;
    private Vista vista;

    public ClienteControlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    /**
     * Menú para la gestión de clientes, recibirá la opción indicada por el usuario dede la vista
     * @param opcionVista Opción indicada por el usuario
     */
    public void opcionGestionClientes(String opcionVista){
        int opcion;
        try {
            opcion = Integer.parseInt(opcionVista);
        } catch (Exception e){
            vista.mostrarMensaje("\nOpción incorrecta.");
            return;
        }
        switch (opcion){
            case 1:
                nuevoCliente();
                break;
            case 2:
                mostrarClientes();
                break;
            case 3:
                mostrarClientesEstandar();
                break;
            case 4:
                mostrarClientesPremium();
                break;
            case 0:
                return;
            default:
                vista.mostrarMensaje("\nOpción incorrecta.\n");
                break;
        }
    }

    /**
     * Añadirá un nuevo cliente con los datos registrados por la entrada de usuario
     */
    public void nuevoCliente(){
        String tipoCliente = vista.solicitarDato("\nIndica tiepo cliente (Estandar/Premium):");
        tipoCliente = tipoCliente.toUpperCase();
        if(!Objects.equals(tipoCliente, "ESTANDAR") && !Objects.equals(tipoCliente, "PREMIUM")){
            vista.mostrarMensaje("\nTipo de cliente incorrecto.\n");
            return;
        }
        String nif = vista.solicitarDato("Indica el NIF:");
        String nombre = vista.solicitarDato("Indica el Nombre:");
        String email = vista.solicitarDato("Indica el Email:");
        String domicilio = vista.solicitarDato("Indica el Domicilio:");

        Cliente nuevoCliente;
        if (Objects.equals(tipoCliente, "Estandar")){
            nuevoCliente = new Estandar(email, nombre, domicilio, nif);
        } else {
            nuevoCliente = new Premium(email,nombre, domicilio, nif);
        }
        modelo.agregarCliente(nuevoCliente);

        if(modelo.getClientes().getLista().contains(nuevoCliente)){
            vista.mostrarMensaje("\nCliente registrado correctamente.\n");
        } else {
            vista.mostrarMensaje("\nNo se registró nuevo cliente, ya existe en el sistema.");
        }
    }

    /**
     * Mostrará el listado de clientes (se llamará desde el Modelo)
     */
    public void mostrarClientes(){
        vista.mostrarMensaje(modelo.obtenerClientes().toString());
    }

    /**
     * Mostrará el listado de clientes "Estandar" (se llamará desde el Modelo)
     */
    public void mostrarClientesEstandar(){
        vista.mostrarMensaje(modelo.obtenerClientesEstandar().toString());
    }

    /**
     * Mostrará el listado de clientes "Premium" (se llamará desde el Modelo)
     */
    public void mostrarClientesPremium(){
        vista.mostrarMensaje(modelo.obtenerClientesPremium().toString());
    }
}
