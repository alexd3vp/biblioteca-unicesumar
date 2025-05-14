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
 * Implementação da Interface LivroDAO
 */
public class LivroDAOImpl implements LivroDAO {

    private final Connection connection;

    public LivroDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Livro livro) {
        String sql = "INSERT INTO livros (isbn, titulo, autor, ano_publicacao) VALUES (?, ?, ?, ?)"; // Insere os
                                                                                                     // valores

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, livro.getISBN());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setDate(4, java.sql.Date.valueOf(livro.getAnoPublicacao()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao salvar livro. ", e);
        }
    }

    public void update(Long id, Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setDate(3, java.sql.Date.valueOf(livro.getAnoPublicacao()));
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao atualizar livro com ID: " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM livros WHERE id = ?"; // Deleta pelo ID

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao deletar livro com ID: " + id, e);
        }
    }

    @Override
    public void deleteByISBN(Long ISBN) {
        String sql = "DELETE FROM livros WHERE isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ISBN);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao deletar livro com ISBN: " + ISBN, e);
        }
    }

    @Override
    public Livro findById(Long id) {
        String sql = "SELECT * FROM livros WHERE id = ?"; // Busca pelo ID

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id); // Passando o ID como parâmetro

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Se o livro for encontrado
                    Livro livro = new Livro();
                    livro.setId(rs.getLong("id"));
                    livro.setISBN(rs.getLong("isbn"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate()); // Convertendo para LocalDate
                    return livro;
                } else {
                    throw new LivroNotFoundException("Livro não encontrado com o ID: " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao buscar livro com o ID: " + id, e);
        }
    }

    @Override
    public Livro findByISBN(Long ISBN) {
        String sql = "SELECT * FROM livros WHERE isbn = ?"; // Busca pelo ISBN

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ISBN); // Passando o ISBN como parâmetro

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Se o livro for encontrado
                    Livro livro = new Livro();
                    livro.setId(rs.getLong("id"));
                    livro.setISBN(rs.getLong("isbn"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate()); // Convertendo para LocalDate
                    return livro;
                } else {
                    throw new LivroNotFoundException("Livro não encontrado com o ISBN: " + ISBN);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao buscar livro com o ISBN: " + ISBN, e);
        }
    }

    @Override
    public List<Livro> findAll() {
        String sql = "SELECT * FROM livros"; // Consulta para pegar todos os livros
        List<Livro> livros = new ArrayList<>(); // Lista para armazenar os livros

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // Itera sobre os resultados
                    Livro livro = new Livro();
                    livro.setId(rs.getLong("id"));
                    livro.setISBN(rs.getLong("isbn"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate()); // Convertendo para LocalDate
                    livros.add(livro); // Adiciona o livro na lista
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erro ao buscar todos os livros", e);
        }

        return livros; // Retorna a lista de livros
    }

    @Override
    public List<Livro> findByTitle(String title) {
        List<Livro> livros = new ArrayList<>();

        String sql = "SELECT * FROM livros WHERE titulo LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id"));
                livro.setISBN(rs.getLong("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getDate("ano_publicacao").toLocalDate());
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

}
