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
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Antonio Martinez Diaz
 */

@Entity
@Table(name = "marca")
public class Marca  implements Serializable{
  private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_marca", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marca;
    
    @NotBlank
    @Column(name = "nombremarca", length = 256)
    private String nombreMarca;
   
    
    @NotBlank
    @Column(name = "pais", length = 256)
    private String pais;
    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)       
    Set<Ingeniero> ingenieros = new HashSet<>();;
    
    public Long getId_marca() {
        return id_marca;
    }



    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<Ingeniero> getIngenieros() {
        return ingenieros;
    }

    public void setIngenieros(Set<Ingeniero> ingenieros) {
        this.ingenieros = ingenieros;
    }

    
    
    
      public void addIngeniero(Ingeniero ingeniero) {
        if (  this.ingenieros== null) {
              this.ingenieros= new HashSet<>();
        }
          this.ingenieros.add(ingeniero);
          ingeniero.setMarca(this);
    }

      
    public void removeIngeniero(Ingeniero ingeniero) {
        if (this.ingenieros == null) {
            this.ingenieros = new HashSet<>();
        }
        this.ingenieros.remove(ingeniero);
        ingeniero.setMarca(null);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id_marca);
        hash = 29 * hash + Objects.hashCode(this.nombreMarca);
        hash = 29 * hash + Objects.hashCode(this.pais);
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
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.nombreMarca, other.nombreMarca)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.id_marca, other.id_marca)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Marca{" + "id=" + id_marca + ", nombre=" + nombreMarca + ", pais=" + pais + '}';
    }

   
    
    
    
    
   
}
