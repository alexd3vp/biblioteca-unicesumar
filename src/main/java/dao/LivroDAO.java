package dao;

import java.util.List;

import model.Livro;

/**
 * Interface que abstrai métodos de persistência para livros
 */
public interface LivroDAO {

    /**
     * Persiste um novo livro no banco de dados
     * 
     * @param livro
     */
    void save(Livro livro);

    /**
     * Atualiza um livro já existente, e persiste no banco de dados
     * 
     * @param id,livro
     */
    void update(Long id, Livro livro);

    /**
     * Deleta um livro do banco de dados pelo seu ID
     * 
     * @param id
     */
    void deleteById(Long id);

    /**
     * Deleta um livro do banco de dados pelo seu ISBN
     * 
     * @param ISBN
     */
    void deleteByISBN(Long ISBN);

    /**
     * Retorna a lista de livros presentes no banco de dados
     * 
     * @return lista de livros
     */
    List<Livro> findAll();

    /**
     * Retorna um livro do banco de dados pelo seu ID
     * 
     * @param id
     * @return livro
     */
    Livro findById(Long id);

    /**
     * Retorna um livro do banco de dados pelo seu ISBN
     * 
     * @param ISBN
     * @return livro
     */
    Livro findByISBN(Long ISBN);

    /**
     * Retorna uma lista de livros do banco de dados que começam com esse título
     * 
     * @param title
     * @return lista de livros
     */
    List<Livro> findByTitle(String title);

}
