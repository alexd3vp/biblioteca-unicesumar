package dao;

import java.util.List;
import model.Livro;

/**
 * Interface que define as operações de acesso a dados (DAO) para a entidade
 * Livro.
 * Contém apenas as assinaturas dos métodos de persistência, como salvar,
 * buscar, atualizar e deletar.
 */
public interface LivroDAO {

    /**
     * Persiste um novo livro no banco de dados.
     *
     * @param livro O objeto Livro a ser salvo.
     */
    void save(Livro livro);

    /**
     * Atualiza os dados de um livro existente com base no seu ID.
     *
     * @param id    O ID do livro a ser atualizado.
     * @param livro Os novos dados do livro.
     */
    void update(Long id, Livro livro);

    /**
     * Remove um livro do banco de dados com base no seu ID.
     *
     * @param id O ID do livro a ser removido.
     */
    void deleteById(Long id);

    /**
     * Remove um livro do banco de dados com base no seu ISBN.
     *
     * @param ISBN O ISBN do livro a ser removido.
     */
    void deleteByISBN(Long ISBN);

    /**
     * Recupera todos os livros armazenados no banco de dados.
     *
     * @return Uma lista contendo todos os livros.
     */
    List<Livro> findAll();

    /**
     * Busca um livro pelo seu ID.
     *
     * @param id O ID do livro a ser buscado.
     * @return O livro correspondente, ou null se não for encontrado.
     */
    Livro findById(Long id);

    /**
     * Busca um livro pelo seu ISBN.
     *
     * @param ISBN O ISBN do livro a ser buscado.
     * @return O livro correspondente, ou null se não for encontrado.
     */
    Livro findByISBN(Long ISBN);

    /**
     * Busca livros cujo título comece com o texto fornecido.
     *
     * @param title O início do título a ser buscado.
     * @return Uma lista de livros que correspondem ao critério.
     */
    List<Livro> findByTitle(String title);
}
