package ec.edu.ups.ppw63.demo63.business;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import ec.edu.ups.ppw63.demo63.model.Mascotas;
import ec.edu.ups.ppw63.demo63.dao.MascotaDao;
import jakarta.ejb.Local;
import jakarta.ws.rs.PathParam;

// @Local // Descomentar si decides utilizarlo como un EJB local
public interface GestionMascotaLocal {

    void guardar(Mascotas mascota);
    void actualizar(Mascotas mascota);
    Mascotas getMascota(@PathParam("id") int id) throws Exception;
    List<Mascotas> getMascotas();
    void borrar(int id);

}