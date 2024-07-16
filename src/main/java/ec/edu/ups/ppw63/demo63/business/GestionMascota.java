package ec.edu.ups.ppw63.demo63.business;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import ec.edu.ups.ppw63.demo63.dao.MascotaDao;
import ec.edu.ups.ppw63.demo63.model.Mascotas;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
public class GestionMascota {
// implements GestionMascotaLocal, GestionMascotaRemoto

    @Inject
    private MascotaDao dao;

    private final Tracer tracer = GlobalTracer.get();

    public void guardar(Mascotas mascota) {
        Span span = tracer.buildSpan("guardar").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            dao.insert(mascota);
        } catch (Exception e) {
            span.log(e.getMessage());
            throw e;
        } finally {
            span.finish();
        }
    }
    
    public void actualizar(Mascotas mascota) {
        Mascotas mas = dao.getMascota(mascota.getId());
        Span span = tracer.buildSpan("actualizar").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            if (mas != null) {
                dao.update(mascota);
            }
        } catch (Exception e) {
            span.log(e.getMessage());
            throw e;
        } finally {
            span.finish();
        }
    }
    
    public Mascotas getMascota(int id) throws Exception {
        Mascotas mas = dao.getMascota(id);
        Span span = tracer.buildSpan("getMascota").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            if (mas != null) {
                return mas;
            } else {
                return null;
            }
        } catch (Exception e) {
            span.log(e.getMessage());
            throw e;
        } finally {
            span.finish();
        }
    }
    
    public List<Mascotas> getMascotas() {
        Span span = tracer.buildSpan("listar").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            return dao.getAll();
        } catch (Exception e) {
            span.log(e.getMessage());
            throw e;
        } finally {
            span.finish();
        }
    }
    
    public void borrar(int id) {
        Span span = tracer.buildSpan("eliminar").start();
        try {
            Mascotas mas = dao.getMascota(id);
            try (Scope scope = tracer.scopeManager().activate(span)) {
                if (mas != null) {
                    dao.remove(mas.getId());
                } else {
                    System.out.println("No existe");
                }
            }
        } catch (Exception e) {
            span.log(e.getMessage());
            throw e;
        } finally {
            span.finish();
        }
    }

}
