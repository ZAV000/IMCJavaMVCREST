package mx.tecmilenio.imc.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        packages("mx.tecmilenio.imc.rest", "mx.tecmilenio.imc.exception");
    }
}
