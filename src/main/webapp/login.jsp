<%@page import="mx.tecmilenio.imc.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio de sesión - IMC Saludable</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header><h1>Inicio de sesión</h1></header>
<nav>
    <a href="index.jsp">Inicio</a>
    <a href="registro.jsp">Registro</a>
</nav>
<main>
    <% if (request.getAttribute("mensaje") != null) { %>
        <div class="alerta-exito"><%= request.getAttribute("mensaje") %></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alerta-error"><%= request.getAttribute("error") %></div>
    <% } %>
    <%
        Usuario usuarioRegistrado = (Usuario) request.getAttribute("usuarioRegistrado");
        if (usuarioRegistrado != null) {
    %>
        <div class="card">
            <h2>Datos recuperados desde la base de datos</h2>
            <p><strong>ID:</strong> <%= usuarioRegistrado.getId() %></p>
            <p><strong>Nombre:</strong> <%= usuarioRegistrado.getNombreCompleto() %></p>
            <p><strong>Usuario:</strong> <%= usuarioRegistrado.getUsername() %></p>
            <p><strong>Edad:</strong> <%= usuarioRegistrado.getEdad() %></p>
            <p><strong>Sexo:</strong> <%= usuarioRegistrado.getSexo() %></p>
            <p><strong>Estatura:</strong> <%= usuarioRegistrado.getEstatura() %> m</p>
            <p class="nota">Esta sección comprueba que, después del registro, la aplicación hizo una consulta SELECT a la base de datos para traer la información nuevamente.</p>
        </div>
    <% } %>

    <form action="login" method="post">
        <label>Nombre de usuario</label>
        <input type="text" name="username" required>

        <label>Contraseña</label>
        <input type="password" name="password" required>

        <button type="submit">Entrar</button>
    </form>
</main>
</body>
</html>
