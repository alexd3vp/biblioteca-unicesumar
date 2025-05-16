package util;

import java.time.LocalDate;

import model.Livro;

public class LivroValidator {

    public static void validarCamposObrigatorios(Livro livro) {
        if (livro.getISBN() == null || livro.getISBN().isEmpty() || livro.getISBN().isBlank()) {
            throw new IllegalArgumentException("Campo 'ISBN' é obrigatório.");
        }

        if (livro.getAutor() == null || livro.getAutor().isEmpty() || livro.getAutor().isBlank()) {
            throw new IllegalArgumentException("Campo 'Autor' é obrigatório.");
        }

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty() || livro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Campo 'Título' é obrigatório.");
        }

        if (livro.getAnoPublicacao().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Campo 'Ano de Publicação' não deve ser no futuro.");
        }
    }
}
