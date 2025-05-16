package service;

import java.util.List;

import model.Livro;

/**
 * Serviço para operações com livros na biblioteca.
 */
public interface LivroService {

    /**
     * Retorna todos os livros salvos na biblioteca.
     *
     * @return uma lista contendo todos os livros; a lista pode estar vazia se não
     *         houver livros cadastrados
     */
    List<Livro> getAllBooks();

    /**
     * Adiciona um novo livro na biblioteca.
     *
     * @param livro o objeto Livro que será adicionado; não deve ser null
     */
    void addBook(Livro livro);

    /**
     * Atualiza os dados de um livro existente na biblioteca.
     *
     * @param id    o identificador único do livro a ser atualizado; não deve ser
     *              null
     * @param livro um objeto Livro contendo os dados atualizados; não deve ser null
     * @return o livro atualizado, ou null se não for encontrado um livro com o id
     *         informado
     */
    Livro updateBook(Long id, Livro livro);

    /**
     * Retorna um livro pelo seu ID.
     *
     * @param id o identificador único do livro; não deve ser null
     * @return o livro correspondente ao ID informado, ou null se não for encontrado
     */
    Livro getBookById(Long id);

    /**
     * Retorna um livro pelo seu ISBN.
     *
     * @param ISBN o código ISBN do livro
     * @return o livro correspondente ao ISBN informado, ou null se não for
     *         encontrado
     */
    Livro getBookByISBN(Long ISBN);

    /**
     * Exclui um livro da biblioteca pelo seu ID.
     *
     * @param id o identificador único do livro a ser excluído; não deve ser null
     */
    void deleteBookById(Long id);

    /**
     * Exclui um livro da biblioteca pelo seu ISBN.
     *
     * @param ISBN o código ISBN do livro a ser excluído
     */
    void deleteBookByISBN(Long ISBN);

    /**
     * Retorna uma lista de livros cujo título começa com o texto informado.
     *
     * @param title a string com o início do título dos livros buscados; não deve
     *              ser null ou vazia
     * @return uma lista de livros que possuem títulos que começam com o texto
     *         fornecido; a lista pode estar vazia
     */
    List<Livro> getBooksByTitle(String title);
}
