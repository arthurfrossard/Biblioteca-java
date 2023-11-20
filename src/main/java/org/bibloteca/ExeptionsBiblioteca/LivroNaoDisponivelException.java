package org.bibloteca.ExeptionsBiblioteca;

public class LivroNaoDisponivelException extends Exception {
    public LivroNaoDisponivelException(String message) {
        super(message);
    }
}
