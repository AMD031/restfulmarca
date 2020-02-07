/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.repositorio;

import com.proyecto.restfulmarcas.interfaces.IIngeniero;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Antonio Martinez Diaz
 */
public interface RepositorioMarca
        extends JpaRepository<Marca, Long> {

@Query(value = "SELECT i.id_ingeniero, i.nombre, i.dni from marca m INNER JOIN ingeniero i on m.id_marca = i.id_marca_fk WHERE m.id_marca =?",nativeQuery = true)
public List<IIngeniero> getIngenierosByIdMarca(Long id);


@Query(value = "SELECT i.id_ingeniero, i.nombre, i.dni from marca m INNER JOIN ingeniero i on m.id_marca = i.id_marca_fk WHERE  m.nombreMarca like %?%" ,nativeQuery = true)
public List<IIngeniero> getIngenierosByNameMarca(String nombreMarca);

 
    
    
    
    
    
    
}
