/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.repositorio;

import com.proyecto.restfulmarcas.interfaces.IIngeniero;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel Hermosilla Aguilera
 */
public interface RepositorioPrototipo 
        extends JpaRepository<Prototipo, Long>{
    
    @Query(value = "SELECT * FROM prototipo AS p WHERE p.nombreClave LIKE %?1%", nativeQuery = true)
    public List<Prototipo> getPrototipoByName(String nombre);

    @Query(value = "SELECT i.id_ingeniero, i.nombre, i.dni FROM prototipo p INNER JOIN ingeniero_prototipo ip on p.id_prototipo = ip.id_prototipo_fk INNER JOIN ingeniero i on i.id_ingeniero = ip.id_ingeniero_fk WHERE p.id_prototipo = ?", nativeQuery = true)
    public List<IIngeniero> getIngenieroByIdPrototipo(Long id);

    @Query(value = "SELECT i.id_ingeniero, i.nombre, i.dni FROM prototipo p INNER JOIN ingeniero_prototipo ip on p.id_prototipo = ip.id_prototipo_fk INNER JOIN\n" +
    "ingeniero i on i.id_ingeniero = ip.id_ingeniero_fk WHERE p.nombreClave like %?1%", nativeQuery = true)
    public List<IIngeniero> getIngenierosByNombreClavePrototipo(String nombreClave);

    
    
    
    
}
