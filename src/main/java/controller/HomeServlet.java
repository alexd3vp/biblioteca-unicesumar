package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por lidar com requisições para a URL "/inicio".
 * <p>
 * Essa servlet serve como ponto de entrada para a aplicação web, redirecionando
 * o usuário para a página principal (index.jsp) localizada dentro da pasta
 * protegida
 * <code>/WEB-INF/views/</code>.
 * </p>
 */
@WebServlet(urlPatterns = { "/inicio" })
public class HomeServlet extends HttpServlet {

    /**
     * Trata requisições GET.
     * Encaminha a requisição para a view <code>index.jsp</code>.
     *
     * @param req  objeto que contém os dados da requisição HTTP do cliente
     * @param resp objeto para enviar a resposta HTTP ao cliente
     * @throws ServletException se ocorrer um erro ao despachar a requisição
     * @throws IOException      se ocorrer um erro de entrada/saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Encaminha para a view de página inicial: index.jsp
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
