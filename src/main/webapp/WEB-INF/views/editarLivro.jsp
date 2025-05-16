<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="model.Livro" %>

            <% Livro livro=(Livro) request.getAttribute("livro"); // Recebe o livro passado pelo Servlet %>



                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>Biblioteca - Editar Livro</title>
                </head>

                <body>
                    <header>
                        <h1>Biblioteca Unicesumar</h1>
                    </header>

                    <nav>
                        <ul>
                            <li><a href="<%= request.getContextPath() %>/inicio">Início</a></li>
                            <li><a href="<%= request.getContextPath() %>/catalogo">Catálogo</a></li>
                        </ul>
                    </nav>

                    <div class="container">
                        <div class="content">
                            <h3>Editar Livro</h3>
                            <form action="<%= request.getContextPath() %>/editar?<%= livro.getId() %>" method="post">
                                <!-- Aciona o servlet no post -->
                                <div class="form-group">
                                    <label>ID:</label>
                                    <span>
                                        <%= livro.getId() %>
                                    </span>
                                </div>

                                <input type="hidden" name="id" value="<%= livro.getId() %>">

                                <div class="form-group">
                                    <label for="isbn">ISBN:</label>
                                    <input type="text" id="isbn" name="isbn" value="<%= livro.getISBN() %>" required>
                                </div>

                                <div class="form-group">
                                    <label for="titulo">Título:</label>
                                    <input type="text" id="titulo" name="titulo" value="<%= livro.getTitulo() %>"
                                        required>
                                </div>

                                <div class="form-group">
                                    <label for="autor">Autor:</label>
                                    <input type="text" id="autor" name="autor" value="<%= livro.getAutor() %>" required>
                                </div>

                                <div class="form-group">
                                    <label for="anoPublicacao">Data de Publicação:</label>
                                    <input type="date" id="anoPublicacao" name="anoPublicacao"
                                        value="<%= livro.getAnoPublicacao().toString() %>" required>
                                </div>

                                <button type="submit" class="btn btn-primary">Salvar</button>
                            </form>
                        </div>
                    </div>
                </body>

                </html>