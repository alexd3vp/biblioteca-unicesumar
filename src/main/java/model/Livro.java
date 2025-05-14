package model;

import java.time.LocalDate;

/**
 * Classe modelo para livros na biblioteca
 */
public class Livro {

    /**
     * Identificação do livro no sistema
     */
    private Long id;

    /**
     * Código internacional para identificação padronizada do livro
     */
    private Long ISBN;

    /**
     * Título do livro.
     */
    private String titulo;

    /**
     * Nome do autor que publicou o livro
     */
    private String autor;

    /**
     * Ano no qual o livro foi publicado
     */
    private LocalDate anoPublicacao;

    // Construtor sem argumentos
    public Livro() {

    }

    // Construtor com argumentos
    public Livro(Long id, Long ISBN, String titulo, String autor, LocalDate anoPublicacao) {
        this.id = id;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    // Construtor com argumentos
    public Livro(Long ISBN, String titulo, String autor, LocalDate anoPublicacao) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long iSBN) {
        ISBN = iSBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(LocalDate anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

}
