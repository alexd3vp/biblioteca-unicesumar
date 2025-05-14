package service;

import java.util.List;

import model.Livro;

/**
 * Serviço para operações com livros
 */
public interface LivroService {

    /**
     * Método para retornar todos os livros salvos na biblioteca
     * 
     * @return uma lista de objetos do tipo Livro
     */
    public List<Livro> getAllBooks();

    /**
     * Método para adicionar um novo livro na biblioteca
     * 
     * @param livro
     */
    public void addBook(Livro livro);

    /**
     * Método para atualizar um livro da biblioteca
     * 
     * @param id,livro
     * @return o livro atualizado
     */
    public Livro updateBook(Long id, Livro livro);

    /**
     * Método para retornar um livro pelo seu ID
     * 
     * @param id
     * @return um objeto do tipo Livro
     */
    public Livro getBookById(Long id);

    /**
     * Método para retornar um livro pelo seu ISBN
     * 
     * @param ISBN
     * @return um objeto do tipo Livro
     */
    public Livro getBookByISBN(Long ISBN);

    /**
     * Método para excluir um livro da biblioteca pelo seu ID
     * 
     * @param id
     */
    public void deleteBookById(Long id);

    /**
     * Método para excluir um livro da biblioteca pelo seu ISBN
     * 
     * @param ISBN
     */
    public void deleteBookByISBN(Long ISBN);

    /**
     * Método para retornar uma lista de livros que começam com esse título
     * 
     * @param title
     * @return uma lista de livros
     */
    public List<Livro> getBooksByTitle(String title);
}
