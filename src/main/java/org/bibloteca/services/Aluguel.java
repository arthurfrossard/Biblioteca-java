package org.bibloteca.services;

import lombok.Builder;
import lombok.Getter;

import org.bibloteca.entities.Cliente;
import org.bibloteca.entities.Livro;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Builder @Getter
public class Aluguel {
    private ArrayList<Livro> livros;
    private Cliente cliente;
    private LocalDate dataDoAluguel;
    private LocalDate dataPrevisaoRetorno;

    public long calcularQuantidadeDeDiasDoAluguel() {
        return ChronoUnit.DAYS.between(dataDoAluguel, LocalDate.now());
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;

        for (Livro livro : livros) {
            valorTotal += livro.getValorDoAluguelPorDia();
        }

        valorTotal *= (double) calcularQuantidadeDeDiasDoAluguel();

        return valorTotal;
    }

}
