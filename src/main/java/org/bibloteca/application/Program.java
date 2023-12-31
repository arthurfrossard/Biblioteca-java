package org.bibloteca.application;

import org.bibloteca.services.Biblioteca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    private static final Logger logger = LoggerFactory.getLogger(Program.class);
    public static void main(String[] args) {
        logger.info("Programa iniciado!");
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int escolhaMenu = 0;

        do {
            logger.debug("O loop do console reiniciou.");
            UI.menuDeSelecao();
            try {
                System.out.print("Selecione a opção desejada: ");
                escolhaMenu = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                logger.error("O usuário inseriu um número de escolha inválido.", e);
                System.out.println("Por favor, insira um número válido.");
                sc.nextLine();
            }

            switch (escolhaMenu) {
                case 1 -> UI.menuCadastroCliente(biblioteca, sc);
                case 2 -> biblioteca.listarClientes();
                case 3 -> UI.menuRemoverCliente(biblioteca, sc);
                case 4 -> UI.menuCadastroLivro(biblioteca, sc);
                case 5 -> biblioteca.listarLivros();
                case 6 -> UI.menuAlterarValorDoLivro(biblioteca, sc);
                case 7 -> UI.menuRemoverLivro(biblioteca, sc);
                case 8 -> UI.menuAlugarLivro(biblioteca, sc);
                case 9 -> biblioteca.listarAlugueis();
                case 10 -> UI.menuDevolverLivro(biblioteca, sc);
                case 11 -> System.out.println("Programa encerrado!");
            }
        } while (escolhaMenu != 11);

        logger.info("Programa encerrado!");
        sc.close();

    }
}
