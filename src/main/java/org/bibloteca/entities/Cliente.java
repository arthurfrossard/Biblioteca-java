package org.bibloteca.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Builder @Getter
public class Cliente {
    private final String id = UUID.randomUUID().toString();
    @Setter private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;

    public int getIdade() {
        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }

}
