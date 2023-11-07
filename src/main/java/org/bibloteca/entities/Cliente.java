package org.bibloteca.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Getter
public class Cliente {
    private final UUID numeroCadastro;
    @Setter
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;

    public Cliente(String nome, String cpf, LocalDate dataDeNascimento) {
        this.numeroCadastro = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getIdade() {
        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }

}
