package dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Listener que inicializa as tabelas do banco de dados durante a inicialização
 * da aplicação
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Inicializando verificação das tabelas do banco de dados...");
        inicializarTabelas();
    }

    private void inicializarTabelas() {
        try (Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement()) {

            // Tabela de Livros - alinhada com a classe model.Livro
            String criarTabelaLivros = "CREATE TABLE IF NOT EXISTS livros (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "isbn BIGINT NOT NULL, " +
                    "titulo VARCHAR(200) NOT NULL, " +
                    "autor VARCHAR(100) NOT NULL, " +
                    "ano_publicacao DATE " +
                    ")";

            // Execute a criação da tabela de livros
            stmt.execute(criarTabelaLivros);

            System.out.println("Tabelas verificadas/criadas com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Operações de limpeza, se necessário
    }
}