package org.bibloteca.application;

import org.bibloteca.services.Biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class UI {
    public static void menuDeSelecao() {
        System.out.println("Selecione a opção que deseja:");
        System.out.println("1 - Cadastrar cliente;");
        System.out.println("2 - Listar cliente(s);");
        System.out.println("3 - Remover cliente;");
        System.out.println("4 - Cadastrar livro;");
        System.out.println("5 - Listar livro(s);");
        System.out.println("6 - Alterar valor do livro;");
        System.out.println("7 - Remover livro;");
        System.out.println("8 - Alugar Livro(s);");
        System.out.println("9 - Listar aluguel(is);");
        System.out.println("10 - Devolver livro(s);");
        System.out.println("11 - Encerrar Programa;");
    }

    public static void menuCadastroCliente(Biblioteca biblioteca, Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            System.out.println("Cadastre um novo cliente:");

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            CPFValidator cpfValidator = new CPFValidator();
            try {
                cpfValidator.assertValid(cpf);
            } catch (InvalidStateException e) {
                throw new IllegalArgumentException("CPF inválido.");
            }

            System.out.print("Data de Nascimento (no formato DD/MM/YYYY): ");
            String dataNascimentoString = sc.nextLine();
            LocalDate dataDeNascimento = LocalDate.parse(dataNascimentoString, formatter);

            LocalDate dataAtual = LocalDate.now();

            if (dataDeNascimento.isAfter(dataAtual)) {
                throw new IllegalArgumentException("Data de nascimento não pode ser uma data futura.");
            }

            biblioteca.cadastrarCliente(nome, cpf, dataDeNascimento);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato DD/MM/YYYY.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuRemoverCliente(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Digite o ID do cliente que deseja remover: ");
        String idClienteParaRemover = sc.nextLine();

        biblioteca.removerClientePorId(idClienteParaRemover);
    }

    public static void menuCadastroLivro(Biblioteca biblioteca, Scanner sc) {
        try {
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

            if (anoPublicacao > LocalDate.now().getYear()) {
                throw new IllegalArgumentException("Ano de publicação não pode ser um ano futuro.");
            }

            System.out.print("Classificação indicativa do livro: ");
            int classificacaoIndicativa = sc.nextInt();
            sc.nextLine();

            if (classificacaoIndicativa < 0 || classificacaoIndicativa > 18) {
                throw new IllegalArgumentException("Classificação indicativa deve estar entre 0 e 18.");
            }

            System.out.print("Valor do aluguel por dia: ");
            double valorDoAluguelPorDia = sc.nextDouble();
            sc.nextLine();

            biblioteca.cadastrarLivro(titulo, autor, isbn, genero, anoPublicacao, classificacaoIndicativa, valorDoAluguelPorDia);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de inserir valores numéricos corretos.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuAlterarValorDoLivro(Biblioteca biblioteca, Scanner sc) {
        try {
            System.out.print("Digite o ID do livro que deseja alterar: ");
            String livroId = sc.nextLine();

            System.out.print("Digite o novo valor do aluguel por dia: ");
            double novoValor = sc.nextDouble();
            sc.nextLine();

            biblioteca.alterarValorDoLivro(livroId, novoValor);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de inserir valores corretos.");
        }
    }

    public static void menuRemoverLivro(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Digite o ID do livro que deseja remover: ");
        String idLivroParaRemover = sc.nextLine();

        biblioteca.removerLivroPorId(idLivroParaRemover);
    }

    public static void menuAlugarLivro(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Insira o ID do cliente: ");
        String idCliente = sc.nextLine();

        System.out.print("Insira o ID do livro: ");
        String idLivro = sc.nextLine();

        biblioteca.alugarLivro(idCliente, idLivro);
    }

    public static void menuDevolverLivro(Biblioteca biblioteca, Scanner sc) {
        System.out.print("Digite o ID do aluguel a ser devolvido: ");
        String idAluguel = sc.nextLine();

        biblioteca.devolverLivro(idAluguel);
    }

}
