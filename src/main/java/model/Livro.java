package model;

import java.time.LocalDate;

/**
 * Representa um livro na biblioteca.
 */
public class Livro {

    /**
     * Identificação única do livro no sistema.
     */
    private Long id;

    /**
     * Código internacional padronizado do livro (ISBN).
     */
    private String ISBN;

    /**
     * Título do livro.
     */
    private String titulo;

    /**
     * Nome do autor do livro.
     */
    private String autor;

    /**
     * Data da publicação do livro.
     */
    private LocalDate anoPublicacao;

    /**
     * Construtor padrão.
     */
    public Livro() {
    }

    /**
     * Construtor completo com id.
     * 
     * @param id            Identificador único do livro
     * @param ISBN          Código ISBN do livro
     * @param titulo        Título do livro
     * @param autor         Nome do autor
     * @param anoPublicacao Data de publicação
     */
    public Livro(Long id, String ISBN, String titulo, String autor, LocalDate anoPublicacao) {
        this.id = id;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * Construtor sem o id, usado para criação inicial antes da persistência.
     * 
     * @param ISBN          Código ISBN do livro
     * @param titulo        Título do livro
     * @param autor         Nome do autor
     * @param anoPublicacao Data de publicação
     */
    public Livro(String ISBN, String titulo, String autor, LocalDate anoPublicacao) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * Retorna o id do livro.
     * 
     * @return id do livro
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o id do livro.
     * 
     * @param id id do livro
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o código ISBN.
     * 
     * @return código ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Define o código ISBN.
     * 
     * @param ISBN código ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Retorna o título do livro.
     * 
     * @return título do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do livro.
     * 
     * @param titulo título do livro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna o autor do livro.
     * 
     * @return nome do autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o autor do livro.
     * 
     * @param autor nome do autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Retorna a data de publicação do livro.
     * 
     * @return data de publicação
     */
    public LocalDate getAnoPublicacao() {
        return anoPublicacao;
    }

    /**
     * Define a data de publicação do livro.
     * 
     * @param anoPublicacao data de publicação
     */
    public void setAnoPublicacao(LocalDate anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

}
