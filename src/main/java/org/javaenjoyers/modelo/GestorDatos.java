package org.javaenjoyers.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica para agregar/listar/eliminar clientes, artículos y pedidos
 * @param <T> Tipo de dato (Cliente, Articulo, Pedido)
 */
public class GestorDatos<T> {

    /**
     * Atributo de tipo Lista, contendrá los objetos de cada tipo
     */
    private List<T> lista = new ArrayList<>();

    /**
     * Método para agregar objetos a la lista
     * @param elemento objeto del tipo indicado
     */
    public void agregar(T elemento){
        lista.add(elemento);
    }

    /**
     * Método para listar los objetos de la lista
     * @return Lista de objetos en función del tipo de dato indicado
     */
    public List<T> listar(){
        return new ArrayList<>(lista);
    }

    /**
     * Método para eliminar un objeto de la lista
     * @param elemento Objeto para eliminar según el tipo de dato
     * @return Devuelve True/False dependiendo si se eliminó
     */
    public boolean eliminar(T elemento){
        return lista.remove(elemento);
    }

}
