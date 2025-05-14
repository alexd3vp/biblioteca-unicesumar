<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Livro" %>
<%@ page import="dao.LivroDAO" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

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
            <li><a href="./inicio">Início</a></li>
            <li><a href="./catalogo">Catálogo</a></li>
        </ul>
    </nav>

    <div class="container">
        <div class="content">
            <a href="./catalogo">Ver catálogo</a>
        </div>
    </div>
    
</body>
</html>