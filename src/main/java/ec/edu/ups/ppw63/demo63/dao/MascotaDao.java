package ec.edu.ups.ppw63.demo63.dao;

import java.util.List;

import ec.edu.ups.ppw63.demo63.model.Mascotas;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Stateless
public class MascotaDao {

    @PersistenceContext
    private EntityManager em;
    
    public void insert(Mascotas mascota) {
        em.merge(mascota);
    }
    
    public void update(Mascotas mascota) {
        em.merge(mascota);
    }
    
    public void remove(int id) {
        Mascotas mascota = em.find(Mascotas.class, id);
        em.remove(mascota);
    }
    
    public Mascotas read(int id) {
        Mascotas mascota = em.find(Mascotas.class, id);
        return mascota;
    }
    
    public List<Mascotas> getAll() {
        String jpql = "SELECT m FROM Mascotas m";
        Query q = em.createQuery(jpql, Mascotas.class);
        return q.getResultList();
    }
    
    public Mascotas getMascota(int id) {
        String jpql = "SELECT m FROM Mascotas m WHERE m.id = :id";
        Query q = em.createQuery(jpql, Mascotas.class);
        q.setParameter("id", id);
        List<Mascotas> mascotas = q.getResultList();
        if (mascotas.size() > 0)
            return mascotas.get(0);
        return null;
    }
}
