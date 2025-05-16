package exception;

/**
 * Exceção lançada quando um livro não é encontrado no sistema.
 * <p>
 * Essa exceção é utilizada principalmente na camada de serviço ou DAO
 * para indicar que a entidade {@code Livro} esperada não existe
 * (por exemplo, ao buscar por um ID inexistente).
 * </p>
 *
 * <p>
 * Por ser uma exceção não verificada ({@link RuntimeException}), não é
 * necessário
 * declará-la em cláusulas <code>throws</code>.
 * </p>
 *
 * Exemplo de uso:
 * 
 * <pre>
 * Livro livro = livroDAO.findById(id);
 * if (livro == null) {
 *     throw new LivroNotFoundException("Livro com ID " + id + " não encontrado.");
 * }
 * </pre>
 */
public class LivroNotFoundException extends RuntimeException {

    /**
     * Cria uma nova exceção {@code LivroNotFoundException} com a mensagem
     * informada.
     *
     * @param message mensagem descritiva do erro
     */
    public LivroNotFoundException(String message) {
        super(message);
    }
}
