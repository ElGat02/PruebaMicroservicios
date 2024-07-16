package ec.edu.ups.ppw63.demo63.services;

import java.util.List;

import ec.edu.ups.ppw63.demo63.business.GestionMascota;
import ec.edu.ups.ppw63.demo63.model.Mascotas;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("mascotas")
public class MascotaServices {
 
    @Inject
    private GestionMascota ges;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Mascotas mascota) {
        try {
            ges.guardar(mascota);
            return Response.ok(mascota).build();
        } catch (Exception ex) {
            ErrorMessage error = new ErrorMessage(500, "Error al guardar mascota: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Mascotas mascota) {
        try {
            ges.actualizar(mascota);
            return Response.ok(mascota).build();
        } catch (Exception ex) {
            ErrorMessage error = new ErrorMessage(406, "Error al actualizar mascota: " + ex.getMessage());
            return Response.status(Response.Status.NOT_MODIFIED).entity(error).build();
        }
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@QueryParam("id") int id) {
        try {
            ges.borrar(id);
            return Response.ok(id).build();
        } catch (Exception ex) {
            ErrorMessage error = new ErrorMessage(99, "Error al borrar la mascota: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("id") int id) {
        try {
            Mascotas mascota = ges.getMascota(id);
            return Response.ok(mascota).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(404, "Mascota no existe: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leerPorId(@PathParam("id") int id) {
        try {
            Mascotas mascota = ges.getMascota(id);
            return Response.ok(mascota).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(404, "Mascota no existe: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getMascotas() {
        try {
            List<Mascotas> mascotas = ges.getMascotas();
            return Response.ok(mascotas).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(404, "No se registran mascotas");
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
    
}
