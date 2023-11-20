package org.bibloteca.ExeptionsBiblioteca;

public class LivroNaoDisponivelException extends RuntimeException  {
    public LivroNaoDisponivelException(String message) {
        super(message);
    }
}
