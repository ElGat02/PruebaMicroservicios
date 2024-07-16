package ec.edu.ups.ppw63.demo63.business;

import java.util.List;

import ec.edu.ups.ppw63.demo63.model.Mascotas;

//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ejb.Remote;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

//@RegisterRestClient(baseUri = "http://localhost:8080/mascotas/rest")//
//@Remote
public interface GestionMascotaRemoto {

  void guardar(Mascotas mascota);
  void actualizar(Mascotas mascota);
  Mascotas getMascota(@PathParam("id") int id) throws Exception;
  List<Mascotas> getMascotas();
  void borrar(int id);
}
