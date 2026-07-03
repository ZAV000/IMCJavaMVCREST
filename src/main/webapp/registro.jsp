<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - IMC Saludable</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header><h1>Registro de usuario</h1></header>
<nav>
    <a href="index.jsp">Inicio</a>
    <a href="login.jsp">Iniciar sesión</a>
</nav>
<main>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alerta-error"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="registro" method="post">
        <label>Nombre completo</label>
        <input type="text" name="nombreCompleto" required>

        <label>Nombre de usuario</label>
        <input type="text" name="username" required>

        <label>Contraseña</label>
        <input type="password" name="password" required>

        <label>Edad</label>
        <input type="number" name="edad" min="15" required>

        <label>Sexo</label>
        <select name="sexo" required>
            <option value="Femenino">Femenino</option>
            <option value="Masculino">Masculino</option>
            <option value="Otro">Otro</option>
        </select>

        <label>Estatura en metros</label>
        <input type="number" step="0.01" name="estatura" min="1" max="2.5" required>

        <button type="submit">Crear cuenta</button>
    </form>
</main>
</body>
</html>
