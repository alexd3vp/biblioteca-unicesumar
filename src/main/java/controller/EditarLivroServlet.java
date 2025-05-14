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

@WebServlet("/editar")
public class EditarLivroServlet extends HttpServlet {

    private LivroService livroService;

    @Override
    public void init() throws ServletException {
        livroService = new LivroServiceImpl(new LivroDAOImpl(ConnectionFactory.getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Livro livro = livroService.getBookById(id); // Buscar o livro pelo ID no serviço

        if (livro == null) {
            response.sendRedirect("catalogo?message=Livro não encontrado&messageType=error");
            return;
        }

        // Passa o livro para a página JSP para ser editado
        request.setAttribute("livro", livro);
        request.getRequestDispatcher("/WEB-INF/editarLivro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));

        Long isbn = Long.valueOf(request.getParameter("isbn"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        LocalDate ano = LocalDate.parse(request.getParameter("anoPublicacao"));

        // Criar o livro com os dados atualizados
        Livro novoLivro = new Livro(id, isbn, titulo, autor, ano); // Passando o id corretamente
        livroService.updateBook(id, novoLivro); // Atualiza o livro no banco

        // Redireciona para o catálogo após salvar
        response.sendRedirect("catalogo?message=Livro atualizado com sucesso&messageType=success");
    }
}
