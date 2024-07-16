package ec.edu.ups.ppw63.demo63.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Mascotas {

    //private static final long serialVersionUID = 1L;
    
    @Id    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mascotas_seq")
    @SequenceGenerator(name = "mascotas_seq", sequenceName = "mascotas_seq", allocationSize = 1)
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private String dueño;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }
    public String getDueño() {
        return dueño;
    }
    public void setDueño(String dueño) {
        this.dueño = dueño;
    }
    
    @Override
    public String toString() {
        return "Mascotas [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", raza=" + raza + ", dueño=" + dueño + "]";
    }
    
}
