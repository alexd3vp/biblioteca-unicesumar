<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Livro" %>
<%@ page import="dao.LivroDAO" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
    List<Livro> livros = (List<Livro>) request.getAttribute("livros");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca - Catálogo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/catalog.css">
</head>
<body>
    <header>
        <h1>Biblioteca Unicesumar</h1>
    </header>
    
    <nav>
        <ul>
            <li><a href="./inicio">Início</a></li>
            <li><a href="./catalogo">Catálogo</a></li>
        </ul>
    </nav>

    <div class="container">
        <div class="content">
            <h2>Catálogo de Livros</h2>
            
            <% 
            // Exibir mensagem de sucesso ou erro, se houver
            String message = request.getParameter("message");
            String messageType = request.getParameter("messageType");
            
            if (message != null && !message.isEmpty()) {
            %>
                <div class="message <%= messageType != null ? messageType : "" %>">
                    <%= message %>
                </div>
            <% } %>
            
            <!-- Formulário de pesquisa -->
            <form action="./catalogo" method="get">
                <div class="form-group" style="display: flex; gap: 10px;">
                    <input type="text" name="search" placeholder="Pesquisar livros..." 
                           value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
                    <button type="submit" class="btn btn-primary">Pesquisar</button>
                </div>
            </form>

            <a href="./novo" class="btn btn-primary">Adicionar novo livro</a>
            
            <!-- Tabela de livros -->
            <h3>Livros Disponíveis</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ISBN</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Ano de Publicação</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (livros != null && !livros.isEmpty()) {
                            for (Livro livro : livros) {
                    %>
                                <tr>
                                    <td><%= livro.getId() %></td>
                                    <td><%= livro.getISBN() %></td>
                                    <td><%= livro.getTitulo() %></td>
                                    <td><%= livro.getAutor() %></td>
                                    <td><%= livro.getAnoPublicacao().format(formatter) %></td>
                                    <td>
                                        <a href="./editar?id=<%= livro.getId() %>" class="btn btn-primary">Editar</a>
                                        <a href="./excluir?id=<%= livro.getId() %>" class="btn btn-danger" onclick="return confirm('Tem certeza que deseja excluir este livro?');">Excluir</a>
                                    </td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="6">Nenhum livro encontrado.</td>
                            </tr>
                    <%
                        }
                    %>

                </tbody>
            </table>
        </div>
    </div>
    <script>
        // Espera 3 segundos (3000 ms) e esconde a div com classe "message"
        setTimeout(function() {
            const msg = document.querySelector('.message');
            if (msg) {
                msg.style.display = 'none';
            }
        }, 3000); // 3 segundos
    </script>
</body>
</html>