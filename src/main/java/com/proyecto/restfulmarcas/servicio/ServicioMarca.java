/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.servicio;

import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antonio Martinez Diaz
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.restfulmarcas.repositorio.RepositorioMarca;

@Service
public class ServicioMarca {
     
  @Autowired
  RepositorioMarca repositorio;

  
  @Autowired
  RepositorioIngeniero repositorioIngeniero;
  

          
  public List<Marca> getAllMarcas()
   {
        List<Marca> marcaList = repositorio.findAll();
         
        if(marcaList.size() > 0) {
            return marcaList;
        } else {
            return new ArrayList<Marca>();
        }
    }
     
    public Marca getMarcaById(Long id) throws RecordNotFoundException
    {
        Optional<Marca> marca = repositorio.findById(id);
         
        if(marca.isPresent()) {
            return marca.get();
        } else {
            throw new RecordNotFoundException("No marca record exist for given id",id);
        }
    }
    public Marca createMarca(Marca entity){
        entity = repositorio.save(entity);
        return entity;
    }
    public Marca UpdateMarca(Marca entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Marca> marca = repositorio.findById(entity.getId());
        
            if(marca.isPresent())
            {
                Marca newEntity = marca.get();
                //newEntity.setId(entity.getId());
                newEntity.setNombre(entity.getNombre());
                newEntity.setPais(entity.getPais());
                newEntity = repositorio.save(newEntity);
                
                return newEntity;
            } else {
                throw new RecordNotFoundException("marca not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of marca given",0l);
    	}	    
 }
     
    public void deleteMarcaById(Long id) throws RecordNotFoundException
    {
        Optional<Marca> marca = repositorio.findById(id);
         
        if(marca.isPresent())
        {
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("No marca record exist for given id",id);
        }
    }

  

    

}

    
    
    
   
    

