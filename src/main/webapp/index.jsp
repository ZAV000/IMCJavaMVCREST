<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>IMC Saludable</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <h1>IMC Saludable</h1>
    <p>Aplicación web MVC para calcular y monitorear el Índice de Masa Corporal</p>
</header>
<nav>
    <a href="index.jsp">Inicio</a>
    <a href="registro.jsp">Registro</a>
    <a href="login.jsp">Iniciar sesión</a>
</nav>
<main>
    <section class="card">
        <h2>Propósito de la aplicación</h2>
        <p>Esta aplicación permite que un usuario se registre, inicie sesión, capture su peso y consulte el histórico de sus cálculos de IMC mediante un servicio REST.</p>
    </section>
    <h2>Fórmula utilizada</h2>
    <p>IMC = peso / estatura²</p>
    <p>Ejemplo: si una persona pesa 70 kg y mide 1.70 m, su IMC es 70 / (1.70 × 1.70) = 24.22.</p>
</main>
</body>
</html>
