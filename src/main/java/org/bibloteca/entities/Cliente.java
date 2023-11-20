package org.bibloteca.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.atomic.AtomicInteger;

@Builder @Getter
public class Cliente {
    private static final AtomicInteger nextId = new AtomicInteger(1);

    private final int id = nextId.getAndIncrement();
    @Setter private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;

    public int getIdade() {
        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }

}
