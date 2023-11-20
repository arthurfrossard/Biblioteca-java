package org.bibloteca.ExeptionsBiblioteca;

public class ElementoNaoEncontradoException extends RuntimeException  {
    public ElementoNaoEncontradoException(String message) {
        super(message);
    }
}
