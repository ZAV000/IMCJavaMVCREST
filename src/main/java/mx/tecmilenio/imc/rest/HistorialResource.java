package mx.tecmilenio.imc.rest;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.tecmilenio.imc.dao.CalculoIMCDAO;
import mx.tecmilenio.imc.dao.UsuarioDAO;
import mx.tecmilenio.imc.exception.ApiException;
import mx.tecmilenio.imc.model.CalculoIMC;
import mx.tecmilenio.imc.model.HistorialResponse;

@Path("/historial")
public class HistorialResource {
    private final CalculoIMCDAO calculoDAO = new CalculoIMCDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GET
    @Path("/{usuarioId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public HistorialResponse obtenerHistorial(@PathParam("usuarioId") int usuarioId) throws ApiException {
        try {
            if (usuarioDAO.buscarPorId(usuarioId) == null) {
                throw new ApiException("No se encontró el usuario solicitado.", Response.Status.NOT_FOUND.getStatusCode());
            }
            List<CalculoIMC> historial = calculoDAO.listarPorUsuario(usuarioId);
            return new HistorialResponse(historial);
        } catch (SQLException ex) {
            throw new ApiException("Error al consultar el historial en la base de datos.", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}
