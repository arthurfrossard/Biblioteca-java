package org.bibloteca.application;

import org.bibloteca.services.Biblioteca;

import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    public static void menuDeSelecao() {
        System.out.println("Selecione a opção que deseja:");
        System.out.println("1 - Cadastrar cliente;");
        System.out.println("2 - Listar cliente(s)");
        System.out.println("3 - Remover cliente");
        System.out.println("4 - Cadastrar livro;");
        System.out.println("5 - Listar livro(s)");
        System.out.println("6 - Remover livro;");
        System.out.println("7 - Alugar Livro(s);");
        System.out.println("8 - Listar aluguel(is);");
        System.out.println("9 - Devolver livro(s);");
        System.out.println("10 - Encerrar Programa;");
    }

    public static void menuCadastroCliente(Biblioteca biblioteca, Scanner sc) {
        System.out.println("Cadastre um novo cliente:");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Data de Nascimento (no formato YYYY-MM-DD): ");
        String dataNascimentoString = sc.nextLine();
        LocalDate dataDeNascimento = LocalDate.parse(dataNascimentoString);

        biblioteca.cadastrarCliente(nome, cpf, dataDeNascimento);
    }

    public static void menuRemoverCliente(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Digite o ID do cliente que deseja remover: ");
        String idClienteParaRemover = sc.nextLine();

        biblioteca.removerClientePorId(idClienteParaRemover);
    }

    public static void menuCadastroLivro(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Título do livro: ");
        String titulo = sc.nextLine();

        System.out.print("Autor do livro: ");
        String autor = sc.nextLine();

        System.out.print("ISBN do livro: ");
        String isbn = sc.nextLine();

        System.out.print("Gênero do livro: ");
        String genero = sc.nextLine();

        System.out.print("Ano da publicação do livro: ");
        int anoPublicacao = sc.nextInt();
        sc.nextLine();

        System.out.print("Classificação indicativa do livro: ");
        int classificacaoIndicativa = sc.nextInt();
        sc.nextLine();

        System.out.print("Valor do aluguel por dia: ");
        double valorDoAluguelPorDia = sc.nextDouble();
        sc.nextLine(); //

        biblioteca.cadastrarLivro(titulo, autor, isbn, genero, anoPublicacao, classificacaoIndicativa, valorDoAluguelPorDia);
    }

    public static void menuRemoverLivro(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Digite o ID do livro que deseja remover: ");
        String idLivroParaRemover = sc.nextLine();

        biblioteca.removerLivroPorId(idLivroParaRemover);
    }

}
