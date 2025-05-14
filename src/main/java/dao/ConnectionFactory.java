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
     * Método para realizar a conexão utilizando JNDI DataSource
     * 
     * @return a conexão com o banco de dados
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
            // Log do erro detalhado
            System.err.println("Erro ao obter conexão via JNDI: " + e.getMessage());
            e.printStackTrace();

            // Lança uma exceção de runtime para notificar o chamador sobre o problema
            throw new RuntimeException("Não foi possível estabelecer conexão com o banco de dados. " +
                    "Verifique se o DataSource está configurado corretamente.", e);
        }
    }
}