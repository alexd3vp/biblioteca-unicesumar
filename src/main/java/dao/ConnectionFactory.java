package dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe para configurar e fabricar a conexão com o banco de dados
 * utilizando JNDI DataSource
 */
public class ConnectionFactory {

    /**
     * Realiza a busca do DataSource via JNDI e retorna uma conexão com o banco de
     * dados.
     * O DataSource deve estar previamente configurado no servidor de aplicação.
     *
     * @return Conexão ativa com o banco de dados.
     * @throws RuntimeException caso ocorra erro na resolução JNDI ou obtenção da
     *                          conexão.
     */

    public static Connection getConnection() {
        try {
            // Obtém o contexto inicial JNDI
            Context initContext = new InitialContext();

            // Obtém o contexto de ambiente Java
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            // Busca o DataSource pelo nome registrado no context.xml
            DataSource ds = (DataSource) envContext.lookup("jdbc/bibliotecaDB");

            // Retorna uma conexão do pool de conexões
            return ds.getConnection();

        } catch (NamingException | SQLException e) {
            // Imprime erro no console caso capturar alguma exceção.
            System.err.println("Erro ao obter conexão via JNDI: " + e.getMessage());

            // Imprime a plha de erros
            e.printStackTrace();

            // Lança uma exceção de runtime para notificar sobre o problema
            throw new RuntimeException("Não foi possível estabelecer conexão com o banco de dados. " +
                    "Verifique se o DataSource está configurado corretamente.", e);
        }
    }
}