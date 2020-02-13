/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Antonio Martinez Diaz
 */

@Entity
@Table(name = "prototipo")
public class Prototipo implements Serializable{
    private static final long serialVersionUID = 1L;
   @Id
   @Column(name = "id_prototipo", updatable = false, nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id_prototipo;
   
   @NotBlank
   @Column(name = "nombreclave", length = 256)
   private String nombreClave;
   
   @NotBlank
   @Column(name = "descripcion", length = 256)
   private String descripcion;
     
  
   @ManyToMany(mappedBy = "prototipos", fetch = FetchType.LAZY)      
   @JsonIgnoreProperties("prototipos")        
   Set<Ingeniero>ingenieros = new HashSet<>();

    public Long getId_prototipo() {
        return id_prototipo;
    }

    public void setId_prototipo(Long id_prototipo) {
        this.id_prototipo = id_prototipo;
    }

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public Set<Ingeniero> getIngenieros() {
        return ingenieros;
    }

    public void setIngenieros(Set<Ingeniero> ingenieros) {
        this.ingenieros = ingenieros;
    }

   
     public void addIngeniero(Ingeniero ingeniero) {
        if (this.ingenieros == null) {
            this.ingenieros = new HashSet<>();
        }
        this.ingenieros.add(ingeniero);
        ingeniero.getPrototipos().add(this);
    }

    public void removeIngeniero(Ingeniero ingeniero) {
        if (this.ingenieros == null) {
            this.ingenieros = new HashSet<>();
        }
        this.ingenieros.remove(ingeniero);
        ingeniero.getPrototipos().remove(this);
    }

        public void removeIngenieros() {
        Iterator<Ingeniero> iterator = this.ingenieros.iterator();
        while (iterator.hasNext()) {
            Ingeniero ingeniero = iterator.next();
           ingeniero.getPrototipos().remove(this);
           iterator.remove();
        }
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id_prototipo);
        hash = 89 * hash + Objects.hashCode(this.nombreClave);
        hash = 89 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prototipo other = (Prototipo) obj;
        if (!Objects.equals(this.nombreClave, other.nombreClave)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id_prototipo, other.id_prototipo)) {
            return false;
        }
        return true;
    }
   
   
    
    
    
}
