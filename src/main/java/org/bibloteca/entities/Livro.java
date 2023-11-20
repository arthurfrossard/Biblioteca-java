package org.bibloteca.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

@Builder @Getter
public class Livro {
    private static final AtomicInteger nextId = new AtomicInteger(1);

    private final int id = nextId.getAndIncrement();
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private Year anoDaPublicacao;
    private int classificacaoIndicativa;
    @Setter private double valorDoAluguelPorDia;
    @Setter private boolean disponibilidade;
}
