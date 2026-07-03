package mx.tecmilenio.imc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.tecmilenio.imc.dao.CalculoIMCDAO;
import mx.tecmilenio.imc.model.CalculoIMC;
import mx.tecmilenio.imc.model.Usuario;
import mx.tecmilenio.imc.service.IMCService;

@WebServlet("/calcular")
public class CalculadoraServlet extends HttpServlet {
    private final IMCService imcService = new IMCService();
    private final CalculoIMCDAO calculoDAO = new CalculoIMCDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Usuario usuario = session == null ? null : (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            double peso = Double.parseDouble(request.getParameter("peso"));
            double imc = imcService.calcular(peso, usuario.getEstatura());
            String categoria = imcService.clasificar(imc);

            CalculoIMC calculo = new CalculoIMC();
            calculo.setUsuarioId(usuario.getId());
            calculo.setPeso(peso);
            calculo.setImc(imc);
            calculo.setCategoria(categoria);
            calculoDAO.guardar(calculo);

            // Observación del profesor:
            // El resultado no se muestra únicamente desde el objeto creado en memoria.
            // Después de guardar, se vuelve a consultar la base de datos para traer
            // el historial actualizado y el último cálculo persistido.
            List<CalculoIMC> historialActualizado = calculoDAO.listarPorUsuario(usuario.getId());
            CalculoIMC ultimoCalculoBD = historialActualizado.isEmpty() ? calculo : historialActualizado.get(0);

            session.setAttribute("ultimoCalculoBD", ultimoCalculoBD);
            session.setAttribute("historialActualizadoBD", historialActualizado);
            response.sendRedirect("calculadora.jsp");
        } catch (IllegalArgumentException | SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("calculadora.jsp").forward(request, response);
        }
    }
}
