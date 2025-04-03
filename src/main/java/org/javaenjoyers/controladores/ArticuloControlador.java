package org.javaenjoyers.controladores;

import org.javaenjoyers.modelos.Articulo;
import org.javaenjoyers.modelos.Modelo;
import org.javaenjoyers.vistas.Vista;

import java.util.Objects;

public class ArticuloControlador {

    private Modelo modelo;
    private Vista vista;

    public ArticuloControlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    /**
     * Menú para la gestión de artículos, recibirá la opción indicada por el usuario dede la vista
     * @param opcionVista Opción indicada por el usuario
     */
    public void opcionGestionArticulos(String opcionVista){
        int opcion;
        try {
            opcion = Integer.parseInt(opcionVista);
        } catch (Exception e){
            vista.mostrarMensaje("\nOpción incorrecta.");
            return;
        }
        switch (opcion){
            case 1:
                nuevoArticulo();
                break;
            case 2:
                mostrarArticulos();
                break;
            case 0:
                return;
            default:
                vista.mostrarMensaje("\nOpción incorrecta.\n");
                break;
        }
    }

    /**
     * Añadirá un nuevo artículo con los datos registrados por la entrada de usuario
     */
    public void nuevoArticulo(){
        String codigoProducto = vista.solicitarDato("\nCódigo del artículo:");
        codigoProducto = codigoProducto.toUpperCase();
        for (Articulo articulo : modelo.getArticulos().getLista()){
            if(Objects.equals(articulo.getCodigoProducto(), codigoProducto)){
                vista.mostrarMensaje("\nCódigo de producto ya registrado");
                return;
            }
        }
        String descripcion = vista.solicitarDato("Descripción del artículo:");
        String precioVenta = vista.solicitarDato("Precio de venta:");
        precioVenta = precioVenta.replace(',', '.');
        float floatPV;
        try {
            floatPV = Float.parseFloat(precioVenta);
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido. Ejemplo correcto '4.99', '4,99' o '4'");
            return;
        }
        String gastosEnvio = vista.solicitarDato("Gastos de envío:");
        gastosEnvio = gastosEnvio.replace(',', '.');
        float floatGE;
        try {
            floatGE = Float.parseFloat(gastosEnvio);
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido. Ejemplo correcto '4.99', '4,99' o '4'");
            return;
        }
        String tiempoPrepEnvio = vista.solicitarDato("Tiempo preparación envío:");
        int intTPE;
        try {
            intTPE = Integer.parseInt(tiempoPrepEnvio);
        } catch (Exception e){
            vista.mostrarMensaje("\nDato introducido no válido.\nDebe ser el tiempo en minutos (número entero, ej: '5'");
            return;
        }
        Articulo nuevoArticulo = new Articulo(codigoProducto, descripcion, floatPV, floatGE, intTPE);
        modelo.agregarArticulo(nuevoArticulo);

        vista.mostrarMensaje("\nArtículo registado correctamente.\n");
    }

    /**
     * Mostrará el listado de artículos (se llamará desde el Modelo)
     */
    public void mostrarArticulos(){
        vista.mostrarMensaje(modelo.obtenerArticulos().toString());
    }
}
