package org.bibloteca.services;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.bibloteca.entities.Cliente;
import org.bibloteca.entities.Livro;

import java.util.ArrayList;

@Builder
public class Biblioteca {
    @Getter
    @Singular
    private ArrayList<Livro> livros;
    @Getter
    @Singular("clientes")
    private ArrayList<Cliente> clientes;
    @Getter
    @Singular("alugueis")
    private ArrayList<Aluguel> alugueis;


}
