package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DAOException;
import exception.LivroNotFoundException;
import model.Livro;

/** 
 * Desenvolvedor: Alex Martini.
 * Data: Maio de 2025.
 */

/**
 * Implementação da interface LivroDAO.
 * Responsável pelas operações concretas de acesso ao banco de dados para a
 * entidade Livro.
 */
public class LivroDAOImpl implements LivroDAO {

    private final Connection connection;

    /**
     * Cria uma nova instância da DAO com a conexão fornecida.
     *
     * @param connection Conexão com o banco de dados.
     */
    public LivroDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insere um novo livro na base de dados.
     *
     * @param livro Livro a ser persistido.
     * @throws DAOException Em caso de erro na operação de inserção.
     */
    @Override
    public void save(Livro livro) {
        String sql = "INSERT INTO livros (isbn, titulo, autor, ano_publicacao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getISBN());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setDate(4, java.sql.Date.valueOf(livro.getAnoPublicacao()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar livro.", e);
        }
    }

    /**
     * Atualiza os dados de um livro com base no seu ID.
     *
     * @param id    ID do livro a ser atualizado.
     * @param livro Objeto com os novos dados.
     * @throws DAOException Em caso de erro na operação de atualização.
     */
    @Override
    public void update(Long id, Livro livro) {
        String sql = "UPDATE livros SET isbn = ?, titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getISBN());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setDate(4, java.sql.Date.valueOf(livro.getAnoPublicacao()));
            stmt.setLong(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar livro com ID: " + id, e);
        }
    }

    /**
     * Remove um livro com base no seu ID.
     *
     * @param id ID do livro a ser removido.
     * @throws DAOException Em caso de erro na operação de remoção.
     */
    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar livro com ID: " + id, e);
        }
    }

    /**
     * Remove um livro com base no seu ISBN.
     *
     * @param ISBN ISBN do livro a ser removido.
     * @throws DAOException Em caso de erro na operação de remoção.
     */
    @Override
    public void deleteByISBN(Long ISBN) {
        String sql = "DELETE FROM livros WHERE isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ISBN);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Erro ao deletar livro com ISBN: " + ISBN, e);
        }
    }

    /**
     * Busca um livro pelo seu ID.
     *
     * @param id ID do livro.
     * @return O livro correspondente.
     * @throws LivroNotFoundException Se o livro não for encontrado.
     * @throws DAOException           Em caso de erro na operação de busca.
     */
    @Override
    public Livro findById(Long id) {
        String sql = "SELECT * FROM livros WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Livro livro = new Livro();
                    livro.setId(rs.getLong("id"));
                    livro.setISBN(rs.getString("isbn"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate());
                    return livro;
                } else {
                    throw new LivroNotFoundException("Livro não encontrado com o ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar livro com o ID: " + id, e);
        }
    }

    /**
     * Busca um livro pelo seu ISBN.
     *
     * @param ISBN ISBN do livro.
     * @return O livro correspondente.
     * @throws LivroNotFoundException Se o livro não for encontrado.
     * @throws DAOException           Em caso de erro na operação de busca.
     */
    @Override
    public Livro findByISBN(Long ISBN) {
        String sql = "SELECT * FROM livros WHERE isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ISBN);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Livro livro = new Livro();
                    livro.setId(rs.getLong("id"));
                    livro.setISBN(rs.getString("isbn"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate());
                    return livro;
                } else {
                    throw new LivroNotFoundException("Livro não encontrado com o ISBN: " + ISBN);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar livro com o ISBN: " + ISBN, e);
        }
    }

    /**
     * Recupera todos os livros cadastrados no banco de dados.
     *
     * @return Lista de livros encontrados.
     * @throws DAOException Em caso de erro na consulta.
     */
    @Override
    public List<Livro> findAll() {
        String sql = "SELECT * FROM livros";
        List<Livro> livros = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id"));
                livro.setISBN(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate());
                livros.add(livro);
            }

        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar todos os livros", e);
        }

        return livros;
    }

    /**
     * Busca livros cujo título contenha o valor informado.
     *
     * @param title Parte do título a ser buscado.
     * @return Lista de livros cujo título contenha o texto informado.
     */
    @Override
    public List<Livro> findByTitle(String title) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE titulo LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id"));
                livro.setISBN(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate());
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar livros por título: " + title, e);
        }

        return livros;
    }
}
