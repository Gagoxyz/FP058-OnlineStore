package org.javaenjoyers.modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorDatos<T> {

    private List<T> lista = new ArrayList<>();

    public List<T> getLista() {
        return lista;
    }

    public void agregar(T elemento){
        lista.add(elemento);
    }

    public void eliminar(T elemento){
        lista.remove(elemento);
    }

}
