/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.repositorio;

import com.proyecto.restfulmarcas.modelo.Ingeniero;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Antonio Martinez Diaz
 */
public interface RepositorioIngeniero
        extends JpaRepository<Ingeniero, Long> {

    @Query(value = "SELECT * FROM ingeniero AS i WHERE i.nombre LIKE %?1%", nativeQuery = true)
    public List<Ingeniero> getIngenieroByName(String nombre);

    
    @Query(value = "SELECT * FROM ingeniero AS i WHERE i.dni LIKE %?1%", nativeQuery = true)
    public List<Ingeniero> getIngenieroByDNI(String dni);
}
