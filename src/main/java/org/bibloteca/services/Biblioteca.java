package org.bibloteca.services;

import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import org.bibloteca.entities.Cliente;
import org.bibloteca.entities.Livro;

@Getter @Setter(AccessLevel.PRIVATE)
public class Biblioteca {
    @Singular private HashSet<Livro> livros;
    @Singular("addCliente") private HashSet<Cliente> clientes;
    @Singular("addAluguel") private HashSet<Aluguel> alugueis;

    @Builder
    public Biblioteca() {
        this.livros = new HashSet<>();
        this.clientes = new HashSet<>();
        this.alugueis = new HashSet<>();
    }

    public void cadastrarCliente(String nome, String cpf, LocalDate dataDeNascimento) {
        Cliente novoCliente = Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .dataDeNascimento(dataDeNascimento)
                .build();

        this.clientes.add(novoCliente);
    }

    public void listarClientes() {
        if (this.getClientes().isEmpty()) {
            System.out.println("Não há clientes cadastrados na biblioteca.");
        } else {
            System.out.println("Lista de clientes:");
            System.out.println("ID - Nome - Data de Nascimento");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Cliente cliente : this.getClientes()) {
                String dataNascimentoFormatada = cliente.getDataDeNascimento().format(formatter);
                String info = cliente.getId() + " - " + cliente.getNome() + " - " + dataNascimentoFormatada;
                System.out.println(info);
            }
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
            this.clientes.remove(clienteEncontrado);
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
                .disponibilidade(true)
                .build();

        this.livros.add(novoLivro);
    }

    public void listarLivros() {
        if (this.getLivros().isEmpty()) {
            System.out.println("Não há livros cadastrados na biblioteca.");
        } else {
            System.out.println("ID - Título - Disponibilidade - Valor do Aluguel por Dia - Autor - Gênero - Ano de Publicação - Classificação Indicativa");
            for (Livro livro : this.getLivros()) {
                String info = livro.getId() + " - " + livro.getTitulo() + " - "
                        + livro.isDisponibilidade() + " - " + livro.getValorDoAluguelPorDia()
                        + " - " + livro.getAutor() + " - " + livro.getGenero() + " - "
                        + livro.getAnoDaPublicacao() + " - " + livro.getClassificacaoIndicativa();
                System.out.println(info);
            }
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
            this.livros.remove(livroEncontrado);
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado com o ID especificado.");
        }
    }

    public void alterarValorDoLivro(String livroId, double novoValor) {
        Livro livroEncontrado = null;

        for (Livro livro : this.getLivros()) {
            if (livro.getId().equals(livroId)) {
                livroEncontrado = livro;
                break;
            }
        }

        if (livroEncontrado != null) {
            livroEncontrado.setValorDoAluguelPorDia(novoValor);
            System.out.println("Valor do livro alterado com sucesso!");
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
                if (cliente.getIdade() >= livro.getClassificacaoIndicativa()) {
                    Aluguel novoAluguel = Aluguel.builder()
                            .livro(livro)
                            .cliente(cliente)
                            .dataDoAluguel(LocalDate.now())
                            .statusDoAluguel(true)
                            .build();

                    this.alugueis.add(novoAluguel);

                    livro.setDisponibilidade(false);
                    System.out.println("Livro alugado com sucesso!");
                } else {
                    System.out.println("O cliente não tem idade suficiente para alugar este livro.");
                }
            } else {
                System.out.println("Livro não disponível para aluguel.");
            }
        } else {
            System.out.println("Livro ou cliente não encontrado na biblioteca!");
        }
    }

    public void listarAlugueis() {
        if (this.getAlugueis().isEmpty()) {
            System.out.println("Não há aluguéis registrados na biblioteca.");
        } else {
            for (Aluguel aluguel : this.getAlugueis()) {
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
