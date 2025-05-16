package controller;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet responsável por lidar com a edição de livros no sistema.
 * <p>
 * Mapeada para a URL <code>/editar</code>, essa servlet permite:
 * <ul>
 * <li>Carregar os dados de um livro existente para edição (GET)</li>
 * <li>Atualizar os dados de um livro a partir de um formulário (POST)</li>
 * </ul>
 * </p>
 */
@WebServlet("/editar")
public class EditarLivroServlet extends HttpServlet {

    /**
     * Serviço responsável pelas operações de negócio relacionadas a livros.
     */
    private LivroService livroService;

    /**
     * Inicializa o serviço de livros ao iniciar o servlet.
     *
     * @throws ServletException caso ocorra erro na inicialização
     */
    @Override
    public void init() throws ServletException {
        livroService = new LivroServiceImpl(new LivroDAOImpl(ConnectionFactory.getConnection()));
    }

    /**
     * Trata requisições GET para carregar os dados de um livro existente.
     * <p>
     * O ID do livro é obtido como parâmetro da URL, e os dados são carregados
     * do banco de dados para serem exibidos em um formulário de edição.
     * </p>
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP a ser enviada
     * @throws ServletException em caso de falha ao encaminhar para a view
     * @throws IOException      em caso de erro de entrada/saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        Livro livro = livroService.getBookById(id); // Buscar o livro pelo ID no serviço

        if (livro == null) {
            // Livro não encontrado, redireciona com mensagem de erro
            res.sendRedirect("catalogo?message=Livro não encontrado&messageType=error");
            return;
        }

        // Disponibiliza o livro para a JSP de edição
        req.setAttribute("livro", livro);

        //
        req.getRequestDispatcher("/WEB-INF/views/editarLivro.jsp").forward(req, res);
    }

    /**
     * Trata requisições HTTP POST para salvar as alterações de um livro.
     * <p>
     * Os dados atualizados são recebidos a partir de um formulário,
     * convertidos em um objeto {@link Livro} e atualizados no banco de dados.
     * </p>
     *
     * @param req requisição HTTP contendo os dados atualizados
     * @param res resposta HTTP de redirecionamento
     * @throws ServletException em caso de falha no processamento
     * @throws IOException      em caso de erro de entrada/saída
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        String isbn = req.getParameter("isbn");
        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        LocalDate ano = LocalDate.parse(req.getParameter("anoPublicacao"));

        // Cria um novo objeto Livro com os dados atualizados
        Livro novoLivro = new Livro(id, isbn, titulo, autor, ano);

        // Atualiza o livro no banco de dados
        livroService.updateBook(id, novoLivro);

        // Redireciona com mensagem de sucesso
        res.sendRedirect("catalogo?message=Livro atualizado com sucesso&messageType=success");
    }
}
