<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Biblioteca - Novo Livro</title>
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
                <!-- Formulário para adicionar novo livro -->
                <h3>Adicionar Novo Livro</h3>
                <form action="<%= request.getContextPath() %>/catalogo" method="post">
                    <div class="form-group">
                        <label for="isbn">ISBN:</label>
                        <input type="text" id="isbn" name="isbn" required>
                    </div>

                    <div class="form-group">
                        <label for="titulo">Título:</label>
                        <input type="text" id="titulo" name="titulo" required>
                    </div>

                    <div class="form-group">
                        <label for="autor">Autor:</label>
                        <input type="text" id="autor" name="autor" required>
                    </div>

                    <div class="form-group">
                        <label for="anoPublicacao">Data de Publicação:</label>
                        <input type="date" id="anoPublicacao" name="anoPublicacao" required>
                    </div>

                    <button type="submit">Salvar</button>
                </form>
            </div>
        </div>

    </body>

    </html>