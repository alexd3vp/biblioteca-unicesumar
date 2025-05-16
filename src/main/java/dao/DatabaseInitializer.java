package dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Listener que inicializa as tabelas do banco de dados durante a inicialização
 * da aplicação web.
 * <p>
 * Ao iniciar o contexto da aplicação, verifica se a tabela "livros" existe no
 * banco de dados;
 * caso não exista, cria a tabela.
 * </p>
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {

    /**
     * Método chamado automaticamente quando a aplicação web é iniciada.
     * Chama o método para inicializar as tabelas do banco.
     *
     * @param sce evento de inicialização do contexto do servlet
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Inicializando verificação das tabelas do banco de dados...");
        inicializarTabelas();
    }

    /**
     * Verifica se a tabela "livros" existe, criando-a se necessário.
     * Utiliza conexão obtida da fábrica de conexões {@link ConnectionFactory}.
     */
    private void inicializarTabelas() {
        // Usa try-with-resources para garantir que a conexão e o statement sejam
        // fechados automaticamente.
        try (
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement()) {

            // String SQL para a criação da tabela no banco de dados.
            String sqlCriarTabelaLivros = "CREATE TABLE IF NOT EXISTS livros (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "isbn VARCHAR(16) NOT NULL, " +
                    "titulo VARCHAR(200) NOT NULL, " +
                    "autor VARCHAR(100) NOT NULL, " +
                    "ano_publicacao DATE " +
                    ")";

            // Statement executa o comando SQL utilizando a string SQL informada no
            // parâmetro.
            stmt.execute(sqlCriarTabelaLivros);

            // Imprime uma mensagem no console caso tudo tenha ocorrido bem.
            System.out.println("Tabelas verificadas/criadas com sucesso!");

        } catch (SQLException e) {
            // Imprime um erro no console caso uma exceção SQL tenha sido capturada.
            System.err.println("Erro ao criar tabelas: " + e.getMessage());

            // Imprime a pilha de erros.
            e.printStackTrace();
        }
    }
}
