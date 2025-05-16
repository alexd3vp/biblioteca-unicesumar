package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConnectionFactory;
import dao.LivroDAOImpl;
import model.Livro;
import service.LivroService;
import service.LivroServiceImpl;

/**
 * Servlet responsável por lidar com requisições relacionadas ao catálogo de
 * livros.
 * <p>
 * Mapeada para a URL <code>/catalogo</code>, esta classe permite:
 * <ul>
 * <li>Visualizar todos os livros cadastrados (GET)</li>
 * <li>Buscar livros por título (GET)</li>
 * <li>Adicionar novos livros ao catálogo via formulário (POST)</li>
 * </ul>
 * </p>
 */
@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    /**
     * Serviço de negócios para operações com livros.
     */
    private LivroService livroService;

    /**
     * Inicializa o serviço de livros ao iniciar o servlet.
     *
     * @throws ServletException em caso de falha na inicialização
     */
    @Override
    public void init() throws ServletException {
        livroService = new LivroServiceImpl(new LivroDAOImpl(ConnectionFactory.getConnection()));
    }

    /**
     * Trata requisições GET para exibir o catálogo de livros.
     * Se o parâmetro <code>search</code> for informado, realiza uma busca por
     * título.
     * Caso contrário, exibe todos os livros disponíveis.
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP a ser enviada
     * @throws ServletException em caso de erro ao encaminhar para a view
     * @throws IOException      em caso de erro de entrada/saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recupera o valor do parâmetro "search"
        String search = req.getParameter("search");

        // Inicia a lista de livros (será preenchida depois da verificação do parâmetro
        // search)
        List<Livro> livros;

        // Verifica o parâmetro search
        if (search != null && !search.trim().isEmpty()) {
            livros = livroService.getBooksByTitle(search); // recupera todo livro que começar com o parâmetro informado,
                                                           // se o parâmetro estiver válido.
        } else {
            livros = livroService.getAllBooks(); // recupera todos os livros.
        }

        // Disponibiliza a lista para a view JSP, passando para o atributo "livros" a
        // lista com os livros.
        req.setAttribute("livros", livros);

        // Encaminha para a página JSP do catálogo
        req.getRequestDispatcher("/WEB-INF/views/catalogo.jsp").forward(req, resp);
    }

    /**
     * Trata requisições POST para cadastrar um novo livro no catálogo.
     * Os dados são recebidos do formulário e convertidos para um objeto
     * {@link Livro}.
     *
     * @param req requisição HTTP contendo os dados do formulário
     * @param res resposta HTTP de redirecionamento
     * @throws ServletException em caso de erro na requisição
     * @throws IOException      em caso de erro de entrada/saída
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Captura os parâmetros do formulário
        String isbn = String.valueOf(req.getParameter("isbn")); // pega o parâmetro como sendo uma String
        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        String anoPublicacaoStr = req.getParameter("anoPublicacao");

        try {
            // Converte a data (assumindo formato yyyy-MM-dd)
            LocalDate anoPublicacao = LocalDate.parse(anoPublicacaoStr);

            // Cria e popula o objeto Livro
            Livro livro = new Livro();
            livro.setISBN(isbn);
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAnoPublicacao(anoPublicacao);

            // Salva o livro no banco de dados
            livroService.addBook(livro);

            // Redireciona para o catálogo com mensagem de sucesso
            res.sendRedirect("./catalogo?message=Livro cadastrado com sucesso!&messageType=success");

        } catch (Exception e) {

            // Imprime a pilha de erro
            e.printStackTrace();

            // Redireciona com mensagem de erro
            res.sendRedirect("./catalogo?message=Erro ao cadastrar livro.&messageType=error");
        }
    }
}
