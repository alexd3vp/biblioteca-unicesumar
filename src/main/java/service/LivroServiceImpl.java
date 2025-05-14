package service;

import java.util.List;

import dao.LivroDAO;
import model.Livro;

/**
 * Implementação do serviço de livros
 */
public class LivroServiceImpl implements LivroService {

    /**
     * Instância do DAO de livros fornecido pela interface
     * LivroDAO
     */
    private final LivroDAO livroDAO;

    // Realiza a injeção de dependência, inicializando a variável com
    // a instância de DAO que foi fornecido no parâmetro
    public LivroServiceImpl(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    /**
     * Retorna todos os livros
     * 
     * @return lista de livros
     */
    @Override
    public List<Livro> getAllBooks() {
        return livroDAO.findAll();
    }

    /**
     * Adiciona um novo livro
     * 
     * @param livro
     */
    @Override
    public void addBook(Livro livro) {
        livroDAO.save(livro);
    }

    /**
     * Atualiza um livro existente
     * 
     * @param id,livro
     */
    @Override
    public Livro updateBook(Long id, Livro livro) {
        Livro existente = livroDAO.findById(id); // verifica se existe
        livroDAO.update(id, livro); // atualiza
        return livro;
    }

    @Override
    public Livro getBookById(Long id) {
        return livroDAO.findById(id);
    }

    @Override
    public Livro getBookByISBN(Long ISBN) {
        return livroDAO.findByISBN(ISBN);
    }

    @Override
    public void deleteBookById(Long id) {
        livroDAO.deleteById(id);
    }

    @Override
    public void deleteBookByISBN(Long ISBN) {
        livroDAO.deleteByISBN(ISBN);
    }

    @Override
    public List<Livro> getBooksByTitle(String title) {
        return livroDAO.findByTitle(title);
    }

}
