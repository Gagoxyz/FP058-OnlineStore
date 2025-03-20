package org.javaenjoyers.modelos;

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

    public List<T> getLista(){
        return lista;
    }

    /**
     * Método para agregar objetos a la lista
     * @param elemento objeto del tipo indicado
     */
    public void agregar(T elemento){
        lista.add(elemento);
    }

    /**
     * Método para eliminar un objeto de la lista
     * @param index Objeto para eliminar según el tipo de dato
     * @return Devuelve True/False dependiendo si se eliminó
     */
    public boolean eliminarElemento(int index){
        T elementoLista = lista.get(index);
        return lista.remove(elementoLista);
    }

    public int listSize(){
        return lista.size();
    }
}
