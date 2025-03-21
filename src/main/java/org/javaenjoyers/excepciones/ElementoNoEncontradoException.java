package org.javaenjoyers.excepciones;

public class ElementoNoEncontradoException extends RuntimeException {
    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
