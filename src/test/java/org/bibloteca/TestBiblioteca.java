package org.bibloteca;

import org.bibloteca.services.Biblioteca;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestBiblioteca {

    @Test
    @DisplayName("Test cadastrarCliente method")
    void testCadastrarCliente() {
        Biblioteca biblioteca1 = new Biblioteca();
        biblioteca1.cadastrarCliente("John", "123456789", LocalDate.of(2015, 1, 1));

        // Assuming you have a method to get clients, you can assert the size
        Assertions.assertEquals(1, biblioteca1.getClientes().size());
    }

    @Test
    @DisplayName("Test listarClientes method with no clients")
    void testListarClientesNoClients() {
        Biblioteca biblioteca2 = new Biblioteca();
        Assertions.assertDoesNotThrow(() -> biblioteca2.listarClientes());
    }

    @Test
    @DisplayName("Test listarClientes method with clients")
    void testListarClientesWithClients() {
        Biblioteca biblioteca3 = new Biblioteca();
        biblioteca3.cadastrarCliente("Art", "123456789", LocalDate.of(2015, 1, 1));
        Assertions.assertDoesNotThrow(() -> biblioteca3.listarClientes());
    }

    @Test
    @DisplayName("Test removerClientePorId method")
    void testRemoverClientePorId() {
        Biblioteca biblioteca4 = new Biblioteca();
        biblioteca4.cadastrarCliente("Gui", "123456789", LocalDate.of(2015, 1, 1));

        biblioteca4.removerClientePorId(1);

        Assertions.assertEquals(1, biblioteca4.getClientes().size());
    }

    @Test
    @DisplayName("Test alugarLivro with invalid client age")
    void testAlugarLivroInvalidClientAge() {
        Biblioteca biblioteca5 = new Biblioteca();
        biblioteca5.cadastrarCliente("Gabi", "123456789", LocalDate.of(2015, 1, 1));
        biblioteca5.cadastrarLivro("Book Title", "Author", "123456789", "Genre", 2000, 12, 5.0);

        System.out.println("Client Age: " + biblioteca5.getClientes().iterator().next().getIdade());
        System.out.println("Book Classification Age: " + biblioteca5.getLivros().iterator().next().getClassificacaoIndicativa());
        int initialSize = biblioteca5.getAlugueis().size();
        biblioteca5.alugarLivro(1, 1);
        int finalSize = biblioteca5.getAlugueis().size();
        Assertions.assertEquals(initialSize, finalSize);
    }


}

