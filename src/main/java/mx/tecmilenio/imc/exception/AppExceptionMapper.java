package mx.tecmilenio.imc.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import mx.tecmilenio.imc.model.ErrorResponse;

@Provider
public class AppExceptionMapper implements ExceptionMapper<ApiException> {
    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(ApiException exception) {
        MediaType type = seleccionarTipoRespuesta();
        return Response.status(exception.getStatus())
                .type(type)
                .entity(new ErrorResponse(exception.getMessage()))
                .build();
    }

    private MediaType seleccionarTipoRespuesta() {
        if (headers != null) {
            for (MediaType mediaType : headers.getAcceptableMediaTypes()) {
                if (mediaType.isCompatible(MediaType.APPLICATION_XML_TYPE)) {
                    return MediaType.APPLICATION_XML_TYPE;
                }
            }
        }
        return MediaType.APPLICATION_JSON_TYPE;
    }
}
