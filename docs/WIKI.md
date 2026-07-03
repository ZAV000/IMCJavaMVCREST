# Wiki del proyecto IMCJavaMVCREST

## Acerca de

IMCJavaMVCREST es una aplicación web académica cuyo propósito es calcular el Índice de Masa Corporal de una persona registrada y permitir el monitoreo del histórico de mediciones.

## Proyecto

### Objetivo

Aplicar Java EE, JSP, Servlets, GlassFish, MySQL, Maven, arquitectura MVC y servicios REST para crear una aplicación web funcional.

### Arquitectura MVC

- Modelo: `Usuario`, `CalculoIMC`, `HistorialResponse`, `ErrorResponse`.
- Vista: páginas JSP `index.jsp`, `registro.jsp`, `login.jsp`, `calculadora.jsp`.
- Controlador: `RegistroServlet`, `LoginServlet`, `CalculadoraServlet`, `LogoutServlet`.
- Acceso a datos: `UsuarioDAO`, `CalculoIMCDAO`.
- Servicio REST: `HistorialResource`.
- Lógica de negocio: `IMCService`.

### Servicio REST

Ruta principal:

`GET /api/historial/{usuarioId}`

Representaciones:

- JSON con encabezado `Accept: application/json`.
- XML con encabezado `Accept: application/xml`.

### Reglas de validación

- Edad mínima: 15 años.
- Estatura válida: de 1.00 m a 2.50 m.
- Peso: mayor a 0 kg.
- El cálculo sólo puede realizarse si el usuario inició sesión.

## Guías

### Guía de instalación

1. Abrir el proyecto en NetBeans.
2. Configurar GlassFish como servidor.
3. Ejecutar el script `src/main/resources/db.sql` en MySQL.
4. Editar las credenciales de `DBConnection.java`.
5. Ejecutar el proyecto.

### Guía de uso

1. Entrar a `index.jsp`.
2. Registrarse en `registro.jsp`.
3. Iniciar sesión en `login.jsp`.
4. Capturar peso en `calculadora.jsp`.
5. Revisar el histórico cargado desde REST.

### Guía de pruebas

- Probar registro con edad menor a 15 años: debe mostrar error.
- Probar estatura menor a 1 m o mayor a 2.5 m: debe mostrar error.
- Probar peso 0 o negativo: debe mostrar error.
- Probar consulta REST con JSON.
- Probar consulta REST con XML.


## Observación recibida en el avance

Durante la retroalimentación del avance, se indicó que la aplicación debía consultar nuevamente la base de datos después de registrar información, para traer los datos a las clases y mostrarlos.

### Implementación

- `RegistroServlet` registra al usuario y posteriormente ejecuta una consulta `SELECT` mediante `usuarioDAO.buscarPorUsername(username)`.
- `login.jsp` muestra una tarjeta con los datos recuperados desde la base de datos.
- `CalculadoraServlet` guarda el cálculo y posteriormente consulta `calculoDAO.listarPorUsuario(usuario.getId())`.
- `calculadora.jsp` muestra el último cálculo recuperado desde la base de datos.
- El historial completo también se consulta mediante el servicio REST `GET /api/historial/{usuarioId}`, disponible en JSON y XML.
