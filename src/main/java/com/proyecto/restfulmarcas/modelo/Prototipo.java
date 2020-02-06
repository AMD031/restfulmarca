/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Prototipo {
  
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @NotBlank
   @Column(name = "nombreclave", length = 256)
   private String nombreClave;
   
   @NotBlank
   @Column(name = "descripcion", length = 256)
   private String Descripcion;
     
  
   @ManyToMany(mappedBy = "prototipos", fetch = FetchType.LAZY)      
   @JsonBackReference            
   Set<Ingeniero>ingenieros ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Set<Ingeniero> getIngenieros() {
        return ingenieros;
    }

    public void setIngenieros(Set<Ingeniero> ingenieros) {
        this.ingenieros = ingenieros;
    }

   
   
   
   
    
}
