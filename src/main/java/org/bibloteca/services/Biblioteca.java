package org.bibloteca.services;

import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
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

    public void alugarLivro(String idCliente, String idLivro) {
        Cliente cliente = null;
        Livro livro = null;

        for (Cliente c : clientes) {
            if (c.getId().equals(idCliente)) {
                cliente = c;
                break;
            }
        }

        for (Livro l : livros) {
            if (l.getId().equals(idLivro)) {
                livro = l;
                break;
            }
        }

        if (cliente != null && livro != null) {
            if (livro.isDisponibilidade()) {
                Aluguel novoAluguel = Aluguel.builder()
                        .livro(livro)
                        .cliente(cliente)
                        .dataDoAluguel(LocalDate.now())
                        .build();

                alugueis.add(novoAluguel);
                livro.setDisponibilidade(false);
                System.out.println("Livro alugado com sucesso!");
            } else {
                System.out.println("Livro não disponível para aluguel.");
            }
        } else {
            System.out.println("Livro ou cliente não encontrado na biblioteca!");
        }
    }

    public void listarAlugueis() {
        for (Aluguel aluguel : alugueis) {
            Cliente cliente = aluguel.getCliente();
            Livro livro = aluguel.getLivro();

            String dataAluguelFormatada = aluguel.getDataDoAluguel().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String infoAluguel = String.format(
                    "ID Aluguel: %s - Nome do cliente: %s - Nome do livro: %s - Data do aluguel: %s",
                    aluguel.getId(),
                    cliente.getNome(),
                    livro.getTitulo(),
                    dataAluguelFormatada
            );

            System.out.println(infoAluguel);
        }
    }

    public void devolverLivro(String idAluguel) {
        Aluguel aluguelADevolver = null;

        // Procura o aluguel correspondente ao ID
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getId().equals(idAluguel)) {
                aluguelADevolver = aluguel;
                break;
            }
        }

        if (aluguelADevolver != null) {
            Livro livroDevolvido = aluguelADevolver.getLivro();
            aluguelADevolver.setStatusDoAluguel(false);
            livroDevolvido.setDisponibilidade(true);

            double valorTotal = aluguelADevolver.calcularValorTotal();
            System.out.println("Livro devolvido com sucesso! Valor total do aluguel: " + valorTotal);
        } else {
            System.out.println("Aluguel não encontrado.");
        }
    }
}
