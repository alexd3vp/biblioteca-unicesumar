package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.LivroDAO;
import exception.LivroNotFoundException;
import model.Livro;
import util.LivroValidator;

/**
 * Implementação do serviço de livros, responsável por
 * gerenciar operações relacionadas a livros na biblioteca,
 * utilizando o DAO para acesso e manipulação dos dados.
 */
public class LivroServiceImpl implements LivroService {

    /**
     * Logger para exibir informações mais customizadas no console
     */
    private static final Logger logger = LoggerFactory.getLogger(LivroServiceImpl.class);

    /**
     * Instância do DAO de livros, injetada via construtor.
     */
    private final LivroDAO livroDAO;

    /**
     * Construtor que realiza a injeção de dependência da instância
     * do DAO responsável pelo acesso aos dados dos livros.
     *
     * @param livroDAO instância do DAO de livros; não deve ser null
     */
    public LivroServiceImpl(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    /**
     * Retorna todos os livros cadastrados na biblioteca.
     *
     * @return lista de livros; pode ser vazia se não houver livros cadastrados
     * @throws
     */
    @Override
    public List<Livro> getAllBooks() {
        logger.info("Buscando todos os livros cadastrados.");
        List<Livro> livros = livroDAO.findAll();
        logger.debug("Total de livros encontrados: {}", livros.size());
        return livros;
    }

    /**
     * Adiciona um novo livro na biblioteca.
     *
     * @param livro o livro a ser adicionado; não deve ser null
     */
    @Override
    public void addBook(Livro livro) {
        logger.info("Adicionando novo livro: {}", livro);
        // Validações antes de salvar
        LivroValidator.validarCamposObrigatorios(livro);
        // Agora sim, salvar
        livroDAO.save(livro);
        logger.info("Livro adicionado com sucesso: {}", livro.getTitulo());
    }

    /**
     * Atualiza um livro existente na biblioteca.
     *
     * Primeiro verifica se o livro com o ID fornecido existe. Caso exista, realiza
     * a atualização
     * dos dados do livro com as informações fornecidas.
     *
     * @param id    o identificador único do livro a ser atualizado; não deve ser
     *              null
     * @param livro objeto contendo os dados atualizados do livro; não deve ser null
     * @return o livro atualizado, ou null se não for encontrado um livro com o ID
     *         informado
     */
    @Override
    public Livro updateBook(Long id, Livro livro) {
        logger.info("Atualizando livro com ID: {}", id);
        Livro existente = livroDAO.findById(id);
        if (existente == null) {
            logger.warn("Livro com ID {} não encontrado para atualização.", id);
            throw new LivroNotFoundException("Livro com ID " + id + " não encontrado para atualização.");
        }

        // Validar o livro
        LivroValidator.validarCamposObrigatorios(livro);
        // Realiza a atualização do livro
        livroDAO.update(id, livro);
        logger.info("Livro com ID {} atualizado com sucesso.", id);

        // Busca de novo no BD para garantir dados atualizados
        return livroDAO.findById(id);
    }

    /**
     * Busca um livro pelo seu ID.
     *
     * @param id o identificador do livro; não deve ser null
     * @return o livro correspondente ao ID, ou null se não encontrado
     * @throws LivroNotFoundException se nenhum livro for encontrado com o ID
     *                                informado
     */
    @Override
    public Livro getBookById(Long id) {
        logger.info("Buscando livro com ID: {}", id);
        Livro existente = livroDAO.findById(id);
        if (existente == null) {
            logger.warn("Livro com ID {} não encontrado.", id);
            throw new LivroNotFoundException("Livro com ID " + id + " não encontrado para atualização.");
        }
        logger.info("Livro encontrado: {}", existente);
        // Retorna o livro encontrado
        return existente;
    }

    /**
     * Busca um livro pelo seu ISBN.
     *
     * @param ISBN o código ISBN do livro
     * @return o livro correspondente ao ISBN, ou null se não encontrado
     * @throws LivroNotFoundException se nenhum livro for encontrado com o ISBN
     *                                informado
     */
    @Override
    public Livro getBookByISBN(Long ISBN) {
        logger.info("Buscando livro com ISBN: {}", ISBN);
        Livro existente = livroDAO.findByISBN(ISBN);
        if (existente == null) {
            logger.warn("Livro com ISBN {} não encontrado.", ISBN);
            throw new LivroNotFoundException("Livro com ISBN " + ISBN + " não encontrado para atualização.");
        }
        logger.info("Livro encontrado: {}", existente);
        // Retorna o livro encontrado
        return existente;
    }

    /**
     * Exclui um livro da biblioteca pelo seu ID.
     *
     * @param id o identificador do livro a ser excluído; não deve ser null
     * @throws LivroNotFoundException se nenhum livro for encontrado com o ID
     *                                informado
     */
    @Override
    public void deleteBookById(Long id) {
        logger.info("Removendo livro com ID: {}", id);
        Livro existente = livroDAO.findById(id);
        if (existente == null) {
            logger.warn("Livro com ID {} não encontrado para remoção.", id);
            throw new LivroNotFoundException("Livro com ID " + id + "não encontrado para remoção.");
        }
        // Deleta o livro encontrado
        livroDAO.deleteById(id);
        logger.info("Livro com ID {} removido com sucesso.", id);
    }

    /**
     * Exclui um livro da biblioteca pelo seu ISBN.
     *
     * @param ISBN o código ISBN do livro a ser excluído
     * @throws LivroNotFoundException se nenhum livro for encontrado com o ISBN
     *                                informado
     */
    @Override
    public void deleteBookByISBN(Long ISBN) {
        logger.info("Removendo livro com ISBN: {}", ISBN);
        Livro existente = livroDAO.findByISBN(ISBN);
        if (existente == null) {
            logger.warn("Livro com ISBN {} não encontrado para remoção.", ISBN);
            throw new LivroNotFoundException("Livro com ISBN " + ISBN + "não encontrado para remoção.");
        }
        // Deleta o livro encontrado
        livroDAO.deleteByISBN(ISBN);
        logger.info("Livro com ISBN {} removido com sucesso.", ISBN);

    }

    /**
     * Retorna uma lista de livros cujo título começa com o texto informado.
     *
     * @param title o início do título para busca; não deve ser null ou vazio
     * @return lista de livros que correspondem ao critério, podendo ser vazia
     */
    @Override
    public List<Livro> getBooksByTitle(String title) {
        logger.info("Buscando livros com título que começa com: '{}'", title);
        List<Livro> livros = livroDAO.findByTitle(title);
        logger.debug("Livros encontrados: {}", livros.size());
        return livros;
    }
}
