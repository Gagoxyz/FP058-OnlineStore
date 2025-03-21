package org.javaenjoyers;

import java.util.HashMap;
import java.util.Map;

public class RepositorioGenerico<T> {
    private Map<String, T> elementos;

    public RepositorioGenerico() {
        this.elementos = new HashMap<>();
    }

    public void agregar(String clave, T elemento) {
        elementos.put(clave, elemento);
    }

    public T obtener(String clave) {
        return elementos.get(clave);
    }

    public void eliminar(String clave) {
        elementos.remove(clave);
    }

    public void mostrarTodos() {
        elementos.values().forEach(System.out::println);
    }

    // 📌 Nuevo método para contar elementos
    public int contarElementos() {
        return elementos.size();
    }
}
