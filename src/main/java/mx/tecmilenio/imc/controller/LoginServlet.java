package mx.tecmilenio.imc.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.tecmilenio.imc.dao.UsuarioDAO;
import mx.tecmilenio.imc.model.Usuario;
import mx.tecmilenio.imc.util.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Usuario usuario = usuarioDAO.login(username, PasswordUtil.hash(password));

            if (usuario == null) {
                throw new IllegalArgumentException("Usuario o contraseña incorrectos.");
            }

            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("calculadora.jsp");
        } catch (IllegalArgumentException | SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
