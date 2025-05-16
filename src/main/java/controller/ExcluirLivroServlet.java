package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConnectionFactory;
import dao.LivroDAOImpl;
import exception.LivroNotFoundException;
import service.LivroService;
import service.LivroServiceImpl;

/**
 * Servlet responsável por lidar com a remoção de livros no sistema.
 * <p>
 * Mapeada para a URL <code>/excluir</code>, essa servlet permite:
 * <ul>
 * <li>Excluir completamente o registro de um livro (POST)</li>
 * </ul>
 * </p>
 */
@WebServlet("/excluir")
public class ExcluirLivroServlet extends HttpServlet {

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
     * Trata requisições GET.
     * 
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP a ser enviada
     * @throws ServletException em caso de falha ao encaminhar para a view
     * @throws IOException      em caso de erro de entrada/saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Recupera o ID do parâmetro
            Long id = Long.parseLong(req.getParameter("id"));

            // Deleta o Livro passando o ID necessário
            livroService.deleteBookById(id);

            // Redireciona para o catálogo com a mensagem e o tipo "sucesso".
            resp.sendRedirect("catalogo?message=Livro excluído com sucesso&messageType=success");
        } catch (NumberFormatException e) {

            // Redireciona para o catálogo com a mensagem e o tipo "erro".
            resp.sendRedirect("catalogo?message=ID inválido para exclusão&messageType=error");
        } catch (LivroNotFoundException e) {

            // Redireciona para o catálogo com a mensagem detalhada da exceção
            // LivroNotFoundException e o tipo "erro".
            resp.sendRedirect("catalogo?message=" + e.getMessage() + "&messageType=error");
        } catch (Exception e) {

            // Imprime a pilha de erros.
            e.printStackTrace();
            // Redireciona para o catalogo com uma mensagem de erro inesperado e o tipo
            // "erro".
            resp.sendRedirect("catalogo?message=Erro inesperado&messageType=error");
        }
    }

}
