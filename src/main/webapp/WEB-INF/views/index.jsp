<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Biblioteca - Início</title>
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
                <a href="<%= request.getContextPath() %>/catalogo">Ver catálogo</a>
            </div>
        </div>

    </body>

    </html>