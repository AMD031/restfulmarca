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
    @Column(name = "id_marca")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_marca;
    
    @NotBlank
    @Column(name = "nombremarca", length = 256)
    private String nombreMarca;
   
    
    @NotBlank
    @Column(name = "pais", length = 256)
    private String pais;
    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    @JsonBackReference        
    Set<Ingeniero> ingenieros;

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

    @Override
    public String toString() {
        return "Marca{" + "id=" + id_marca + ", nombre=" + nombreMarca + ", pais=" + pais + '}';
    }

   
    
    
    
    
   
}
