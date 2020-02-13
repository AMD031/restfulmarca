/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ManyToAny;
import org.springframework.core.annotation.AliasFor;

/**
 *
 * @author Antonio Martinez Diaz
 */
@Entity
@Table(name = "ingeniero")
public class Ingeniero implements Serializable{
  private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_ingeniero",updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingeniero;

    @NotBlank
    @Column(name = "nombre", length = 256)
    private String nombre;

    @NotBlank
    @Column(name = "dni", length = 256)
    private String dni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca_fk")
    @JsonIgnore
    private Marca marca;
    
    @JoinTable(
            name = "ingeniero_prototipo",
            joinColumns = @JoinColumn(name = "id_ingeniero_fk"),
            inverseJoinColumns = @JoinColumn(name = "id_prototipo_fk"))
   
   
    @JsonIgnoreProperties("ingenieros")
     @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    Set<Prototipo> prototipos = new HashSet<>();
    
    public Long getId_ingeniero() {
        return id_ingeniero;
    }

    public void setId_ingeniero(Long id_ingeniero) {
        this.id_ingeniero = id_ingeniero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<Prototipo> getPrototipos() {
        return prototipos;
    }

    public void setPrototipos(Set<Prototipo> prototipos) {
        this.prototipos = prototipos;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }


  
    
    
    

    public void addPrototio(Prototipo prototipo) {
        if (this.prototipos == null) {
            this.prototipos = new HashSet<>();
        }
        this.prototipos.add(prototipo);
        prototipo.getIngenieros().add(this);
    }

    public void removePrototipo(Prototipo prototipo) {
        if (this.prototipos == null) {
            this.prototipos = new HashSet<>();
        }
        this.prototipos.remove(prototipo);
        prototipo.getIngenieros().remove(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id_ingeniero);
        hash = 31 * hash + Objects.hashCode(this.nombre);
        hash = 31 * hash + Objects.hashCode(this.dni);
        hash = 31 * hash + Objects.hashCode(this.marca);
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
        final Ingeniero other = (Ingeniero) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.id_ingeniero, other.id_ingeniero)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return true;
    }

    
        public void removePrototipos() {
        Iterator<Prototipo> iterator = this.prototipos.iterator();

        while (iterator.hasNext()) {
            Prototipo prototipo = iterator.next();
            prototipo.getIngenieros().remove(this);
            iterator.remove();
        }
    }

        
        
    @Override
    public String toString() {
        return "Ingeniero{" + "id=" + id_ingeniero + ", nombre=" + nombre + ", dni=" + dni + ", marca=" + marca.getId_marca() + '}';
    }

}
