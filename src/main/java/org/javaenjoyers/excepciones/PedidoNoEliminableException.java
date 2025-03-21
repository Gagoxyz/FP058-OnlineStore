package org.javaenjoyers.excepciones;

public class PedidoNoEliminableException extends RuntimeException {
    public PedidoNoEliminableException(String mensaje) {
        super(mensaje);
    }
}
