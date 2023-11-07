package org.bibloteca.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.time.Year;

@Builder @Getter
public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    @Singular
    private ArrayList<String> generos;
    private Year anoDaPublicacao;
    private int classificacaoIndicativa;
    @Getter
    private double valorDoAluguelPorDia;
}
