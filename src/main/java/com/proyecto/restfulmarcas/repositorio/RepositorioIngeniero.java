/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.repositorio;

import com.proyecto.restfulmarcas.interfaces.IMarca;
import com.proyecto.restfulmarcas.interfaces.IPrototipo;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import java.util.List;
import javax.persistence.NamedQuery;

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
    
    
    @Query(value = "SELECT m.id_marca, m.pais, m.nombreMarca FROM marca m INNER JOIN ingeniero i on m.id_marca = i.id_marca_fk WHERE i.id_ingeniero = ?" ,nativeQuery = true)
    public List<IMarca> getMarcasByIdIngeniero(Long id);
    
    @Query(value = "SELECT m.id_marca, m.pais, m.nombreMarca FROM marca m INNER JOIN ingeniero i on m.id_marca = i.id_marca_fk WHERE i.dni like %?1%" ,nativeQuery = true)
    public List<IMarca> getMarcasByDniIngeniero(String dni);
    
    @Query(value = "SELECT p.id_prototipo,p.nombreClave,p.descripcion FROM prototipo p INNER JOIN ingeniero_prototipo ip on p.id_prototipo = ip.id_prototipo_fk INNER JOIN\n" +
    "ingeniero i on i.id_ingeniero = ip.id_ingeniero_fk WHERE i.id_ingeniero = ?",nativeQuery = true)
    public List<IPrototipo> getPrototipoByIdIngeniero(Long id);
  
    @Query(value = "SELECT p.id_prototipo,p.nombreClave,p.descripcion FROM prototipo p INNER JOIN ingeniero_prototipo ip on p.id_prototipo = ip.id_prototipo_fk INNER JOIN\n" +
    "ingeniero i on i.id_ingeniero = ip.id_ingeniero_fk WHERE i.dni like %?1%",nativeQuery = true)
    public List<IPrototipo> getPrototipoByDniIngeniero(String dni);

    
    
 

    
    
    
    
    
    
    
}
