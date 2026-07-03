<%@page import="mx.tecmilenio.imc.model.CalculoIMC"%>
<%@page import="mx.tecmilenio.imc.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Calculadora IMC</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <h1>Calculadora de IMC</h1>
    <p>Bienvenido/a, <%= usuario.getNombreCompleto() %></p>
</header>
<nav>
    <a href="index.jsp">Inicio</a>
    <a href="logout">Cerrar sesión</a>
</nav>
<main>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alerta-error"><%= request.getAttribute("error") %></div>
    <% } %>

    <%
        CalculoIMC ultimoCalculoBD = (CalculoIMC) session.getAttribute("ultimoCalculoBD");
        if (ultimoCalculoBD != null) {
    %>
        <div class="card">
            <h2>Último resultado recuperado desde la base de datos</h2>
            <p>Fecha: <strong><%= ultimoCalculoBD.getFecha() %></strong></p>
            <p>Peso: <strong><%= ultimoCalculoBD.getPeso() %> kg</strong></p>
            <p>IMC: <strong><%= ultimoCalculoBD.getImc() %></strong></p>
            <p>Categoría: <strong><%= ultimoCalculoBD.getCategoria() %></strong></p>
            <p class="nota">Después de guardar el cálculo, el servlet vuelve a consultar la base de datos y muestra el registro recuperado.</p>
        </div>
    <% } %>

    <h2>Calcular IMC</h2>
    <form action="calcular" method="post">
        <label>Masa corporal en kg</label>
        <input type="number" step="0.01" min="0.01" name="peso" required>
        <button type="submit">Calcular y guardar</button>
    </form>

    <h2>Historial consumido desde servicio REST</h2>
    <p>Servicio: <code>api/historial/<%= usuario.getId() %></code></p>
    <table>
        <thead>
        <tr>
            <th>Fecha</th>
            <th>Peso</th>
            <th>IMC</th>
            <th>Categoría</th>
        </tr>
        </thead>
        <tbody id="tablaHistorial">
        <tr><td colspan="4">Cargando historial...</td></tr>
        </tbody>
    </table>
</main>
<script>
    const usuarioId = <%= usuario.getId() %>;
    fetch('api/historial/' + usuarioId, { headers: { 'Accept': 'application/json' } })
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('tablaHistorial');
            tbody.innerHTML = '';
            const calculos = data.calculos || [];
            if (calculos.length === 0) {
                tbody.innerHTML = '<tr><td colspan="4">Todavía no hay cálculos registrados.</td></tr>';
                return;
            }
            calculos.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = '<td>' + item.fecha + '</td>' +
                                '<td>' + item.peso + ' kg</td>' +
                                '<td>' + item.imc + '</td>' +
                                '<td>' + item.categoria + '</td>';
                tbody.appendChild(row);
            });
        })
        .catch(() => {
            document.getElementById('tablaHistorial').innerHTML = '<tr><td colspan="4">No se pudo cargar el historial.</td></tr>';
        });
</script>
</body>
</html>
