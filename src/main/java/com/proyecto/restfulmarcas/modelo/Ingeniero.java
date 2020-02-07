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

/**
 *
 * @author Antonio Martinez Diaz
 */

@Entity
@Table(name = "ingeniero")
public class Ingeniero {
    @Id
    @Column(name = "id_ingeniero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingeniero;
    
    @NotBlank
    @Column(name = "nombre", length = 256)
    private String nombre;
    
    @NotBlank
    @Column(name = "dni", length = 256)
    private String dni;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_marca_fk")
    @JsonBackReference
    private Marca marca;
    

    @JoinTable(       
    name = "ingeniero-prototipo", 
    joinColumns = @JoinColumn(name = "id_ingeniero_fk" ), 
    inverseJoinColumns = @JoinColumn(name = "id_prototipo_fk"))
    @JsonBackReference   
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Prototipo>prototipos;

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
    
    @Override
    public String toString() {
        return "Ingeniero{" + "id=" + id_ingeniero + ", nombre=" + nombre + ", dni=" + dni + ", marca=" + marca.getId_marca()+ '}';
    }
 
   
    


}