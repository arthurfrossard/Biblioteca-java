package org.bibloteca.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;

import org.bibloteca.entities.Cliente;
import org.bibloteca.entities.Livro;

@Builder @Getter
public class Biblioteca {
    @Singular
    private ArrayList<Livro> livros;
    @Singular("clientes")
    private ArrayList<Cliente> clientes;
    @Singular("alugueis")
    private ArrayList<Aluguel> alugueis;
}
