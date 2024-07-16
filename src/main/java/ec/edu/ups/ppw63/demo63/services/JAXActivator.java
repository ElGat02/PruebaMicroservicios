package ec.edu.ups.ppw63.demo63.services;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("rs")
/*@OpenAPIDefinition(info = @Info(
        title = "Mascotas API",
        description = "API para gestionar Mascotas", version = "1.0.0"))*/
public class JAXActivator extends Application{

}
