package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por exibir o formulário de cadastro de um novo livro.
 * <p>
 * Mapeada para a URL <code>/novo</code>, essa servlet trata requisições GET
 * e apenas encaminha o usuário para a página JSP de criação de livro.
 * </p>
 */
@WebServlet("/novo")
public class NovoLivroServlet extends HttpServlet {

    /**
     * Trata requisições GET encaminhando para a página de cadastro de novo
     * livro.
     *
     * @param req  a requisição HTTP recebida
     * @param resp a resposta HTTP a ser enviada
     * @throws ServletException em caso de erro ao encaminhar para a view
     * @throws IOException      em caso de erro de entrada/saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Encaminha para a view de formulário de cadastro: novoLivro.jsp, onde o
        // usuário poderá adicionar um novo livro.
        req.getRequestDispatcher("/WEB-INF/views/novoLivro.jsp").forward(req, resp);
    }
}
