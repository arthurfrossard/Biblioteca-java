package org.bibloteca.services;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import lombok.Setter;
import org.bibloteca.entities.Cliente;
import org.bibloteca.entities.Livro;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Builder @Getter
public class Aluguel {
    private static final AtomicInteger nextId = new AtomicInteger(1);

    private final int id = nextId.getAndIncrement();
    private Livro livro;
    private Cliente cliente;
    @Setter(AccessLevel.PROTECTED) private boolean statusDoAluguel;
    private LocalDate dataDoAluguel;
    private LocalDate dataDevolucao;

    public long calcularQuantidadeDeDiasDoAluguel() {
        return ChronoUnit.DAYS.between(dataDoAluguel, LocalDate.now());
    }

    public double calcularValorTotal() {
        return livro.getValorDoAluguelPorDia() *
                (double) calcularQuantidadeDeDiasDoAluguel();
    }

}
