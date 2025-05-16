package exception;

import java.sql.SQLException;

/**
 * Exceção personalizada para representar erros ocorridos na camada de acesso a
 * dados (DAO).
 * <p>
 * Essa exceção é uma especialização de {@link RuntimeException}, e geralmente é
 * usada para
 * encapsular {@link SQLException}, tornando o tratamento de erros mais
 * consistente em camadas superiores.
 * </p>
 *
 * <p>
 * Por ser uma exceção não verificada (unchecked), ela não precisa ser
 * explicitamente
 * declarada em cláusulas <code>throws</code>.
 * </p>
 *
 * Exemplo de uso:
 * 
 * <pre>
 * try {
 *     // operação com banco de dados
 * } catch (SQLException e) {
 *     throw new DAOException("Erro ao acessar o banco de dados", e);
 * }
 * </pre>
 */
public class DAOException extends RuntimeException {

    /**
     * Cria uma nova instância de {@code DAOException} com a mensagem especificada
     * e a exceção original {@link SQLException} que causou o erro.
     *
     * @param message mensagem descritiva do erro
     * @param e       exceção {@code SQLException} original
     */
    public DAOException(String message, SQLException e) {
        super(message, e);
    }

}
