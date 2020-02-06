/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Marca {

  
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    @Column(name = "nombre", length = 256)
    private String nombre;
   
    
    @NotBlank
    @Column(name = "pais", length = 256)
    private String pais;
    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    @JsonBackReference        
    Set<Ingeniero> ingenieros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Marca{" + "id=" + id + ", nombre=" + nombre + ", pais=" + pais + '}';
    }

   
    
    
    
    
   
}
