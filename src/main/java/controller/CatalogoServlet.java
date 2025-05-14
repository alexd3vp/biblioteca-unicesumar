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

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    private LivroService livroService;

    @Override
    public void init() throws ServletException {
        livroService = new LivroServiceImpl(new LivroDAOImpl(ConnectionFactory.getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String search = req.getParameter("search");

        List<Livro> livros;

        if (search != null && !search.trim().isEmpty()) {
            livros = livroService.getBooksByTitle(search);
        } else {
            livros = livroService.getAllBooks();
        }

        // Coloca na requisição para a JSP acessar
        req.setAttribute("livros", livros);

        // Encaminha para a view (JSP)
        req.getRequestDispatcher("/WEB-INF/views/catalogo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pegar os parâmetros do formulário
        Long isbn = Long.valueOf(request.getParameter("isbn"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anoPublicacaoStr = request.getParameter("anoPublicacao");

        try {
            LocalDate anoPublicacao = LocalDate.parse(anoPublicacaoStr); // Assumindo formato yyyy-MM-dd

            // Criar objeto Livro
            Livro livro = new Livro();
            livro.setISBN(isbn);
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAnoPublicacao(anoPublicacao);

            // Salvar no banco
            livroService.addBook(livro);

            // Redirecionar para catálogo com mensagem de sucesso
            response.sendRedirect("./catalogo?message=Livro cadastrado com sucesso!&messageType=success");

        } catch (Exception e) {
            e.printStackTrace();
            // Redirecionar com mensagem de erro
            response.sendRedirect("catalogo?message=Erro ao cadastrar livro.&messageType=error");
        }
    }

}
