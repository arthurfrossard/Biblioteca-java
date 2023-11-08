package org.bibloteca.application;

import org.bibloteca.services.Biblioteca;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Biblioteca biblioteca = Biblioteca.builder().build();
        Scanner sc = new Scanner(System.in);
        int escolhaMenu;

        do {
            UI.menuDeSelecao();
            System.out.print("Selecione a opção desejada: ");
            escolhaMenu = sc.nextInt();
            sc.nextLine();
            switch (escolhaMenu) {
                case 1 -> UI.menuCadastroCliente(biblioteca, sc);
                case 2 -> biblioteca.listarClientes();
                case 3 -> UI.menuRemoverCliente(biblioteca, sc);
                case 4 -> UI.menuCadastroLivro(biblioteca, sc);
                case 5 -> biblioteca.listarLivros();
                case 6 -> UI.menuRemoverLivro(biblioteca, sc);
                //case 7 -> UI.alugarLivro();
                //case 8 -> UI.listarAlugueis();
                //case 9 -> UI.devolverLivro();
            }
        } while (true);

        //sc.close();
    }
}
