<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Recurso não encontrado</title>
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
            <h1>Erro 404 - Página não encontrada</h1>
            <p>A página que você tentou acessar não existe.</p>
            <a href="<%= request.getContextPath() %>/inicio">Voltar para o início</a>
        </div>

    </body>

    </html>