package org.bibloteca.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.UUID;

@Builder @Getter
public class Livro {
    private final String id = UUID.randomUUID().toString();
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private Year anoDaPublicacao;
    private int classificacaoIndicativa;
    @Setter private double valorDoAluguelPorDia;
    @Setter private boolean disponibilidade = true;
}
