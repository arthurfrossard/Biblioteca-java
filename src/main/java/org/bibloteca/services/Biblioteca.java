package org.bibloteca.services;

import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import org.bibloteca.entities.Cliente;
import org.bibloteca.entities.Livro;

@Builder @Getter @Setter(AccessLevel.PRIVATE)
public class Biblioteca {
    @Singular private Set<Livro> livros;
    @Singular("addCliente") private Set<Cliente> clientes;
    @Singular("addAluguel") private Set<Aluguel> alugueis;

    public void cadastrarCliente(String nome, String cpf, LocalDate dataDeNascimento) {
        Cliente novoCliente = Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .dataDeNascimento(dataDeNascimento)
                .build();

        Set<Cliente> novaListaClientes = new HashSet<>(this.getClientes());
        novaListaClientes.add(novoCliente);
        this.setClientes(novaListaClientes);
    }

    public void listarClientes() {
        for (Cliente cliente : this.getClientes()) {
            String info = cliente.getId() + " - " + cliente.getNome();
            System.out.println(info);
        }

    }

    public void removerClientePorId(String clienteId) {
        Cliente clienteEncontrado = null;

        for (Cliente cliente : this.getClientes()) {
            if (cliente.getId().equals(clienteId)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            Set<Cliente> novaListaClientes = new HashSet<>(this.getClientes());
            novaListaClientes.remove(clienteEncontrado);
            this.setClientes(novaListaClientes);
        } else {
            System.out.println("Cliente não encontrado com o ID especificado.");
        }
    }

    public void cadastrarLivro(String titulo, String autor, String isbn, String genero, int anoDaPublicacao,
                               int classificacaoIndicativa, double valorDoAluguelPorDia) {

        Livro novoLivro = Livro.builder()
                .titulo(titulo)
                .autor(autor)
                .isbn(isbn)
                .genero(genero)
                .anoDaPublicacao(Year.of(anoDaPublicacao))
                .classificacaoIndicativa(classificacaoIndicativa)
                .valorDoAluguelPorDia(valorDoAluguelPorDia)
                .build();

        Set<Livro> novaListaLivros = new HashSet<>(this.getLivros());
        novaListaLivros.add(novoLivro);
        this.setLivros(novaListaLivros);
    }

    public void listarLivros() {
        for (Livro livro : this.getLivros()) {
            String info = livro.getId() + " - " + livro.getTitulo();
            System.out.println(info);
        }
    }

    public void removerLivroPorId(String livroId) {
        Livro livroEncontrado = null;

        for (Livro livro : this.getLivros()) {
            if (livro.getId().equals(livroId)) {
                livroEncontrado = livro;
                break;
            }
        }

        if (livroEncontrado != null) {
            Set<Livro> novaListaLivros = new HashSet<>(this.getLivros());
            novaListaLivros.remove(livroEncontrado);
            this.setLivros(novaListaLivros);
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado com o ID especificado.");
        }
    }


}
