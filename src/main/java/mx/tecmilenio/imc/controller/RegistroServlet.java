package mx.tecmilenio.imc.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.tecmilenio.imc.dao.UsuarioDAO;
import mx.tecmilenio.imc.model.Usuario;
import mx.tecmilenio.imc.util.PasswordUtil;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String nombreCompleto = request.getParameter("nombreCompleto");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String sexo = request.getParameter("sexo");
            double estatura = Double.parseDouble(request.getParameter("estatura"));

            if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre completo es obligatorio.");
            }
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre de usuario es obligatorio.");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("La contraseña es obligatoria.");
            }
            if (edad < 15) {
                throw new IllegalArgumentException("La edad debe ser mayor o igual a 15 años.");
            }
            if (estatura < 1.0 || estatura > 2.5) {
                throw new IllegalArgumentException("La estatura debe estar entre 1.00 m y 2.50 m.");
            }
            if (usuarioDAO.buscarPorUsername(username) != null) {
                throw new IllegalArgumentException("El nombre de usuario ya está registrado.");
            }

            Usuario usuario = new Usuario();
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setUsername(username);
            usuario.setPasswordHash(PasswordUtil.hash(password));
            usuario.setEdad(edad);
            usuario.setSexo(sexo);
            usuario.setEstatura(estatura);
            usuarioDAO.registrar(usuario);

            // Observación del profesor:
            // Después de registrar los datos, se realiza una nueva consulta a la base de datos
            // para recuperar el usuario ya persistido y mostrarlo en la vista.
            Usuario usuarioRegistrado = usuarioDAO.buscarPorUsername(username);

            request.setAttribute("usuarioRegistrado", usuarioRegistrado);
            request.setAttribute("mensaje", "Registro exitoso. Los datos fueron guardados y recuperados desde la base de datos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (IllegalArgumentException | SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}
