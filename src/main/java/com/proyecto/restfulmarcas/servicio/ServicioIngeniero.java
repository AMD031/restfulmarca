/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.servicio;

import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.modelo.Ingeniero;

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
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;

@Service
public class ServicioIngeniero {
     
  @Autowired
  RepositorioIngeniero repositorio;
  public List<Ingeniero> getAllIngenieros()
   {
        List<Ingeniero> ingenieroList = repositorio.findAll();
         
        if(ingenieroList.size() > 0) {
            return ingenieroList;
        } else {
            return new ArrayList<Ingeniero>();
        }
    }
     
    public Ingeniero getIngenieroById(Long id) throws RecordNotFoundException
    {
        Optional<Ingeniero> ingeniero = repositorio.findById(id);
         
        if(ingeniero.isPresent()) {
            return ingeniero.get();
        } else {
            throw new RecordNotFoundException("No ingeniero record exist for given id",id);
        }
    }
    public Ingeniero createIngeniero(Ingeniero entity){
        entity = repositorio.save(entity);
        return entity;
    }
    public Ingeniero UpdateIngeniero(Ingeniero entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Ingeniero> ingeniero = repositorio.findById(entity.getId());
        
            if(ingeniero.isPresent())
            {
                Ingeniero newEntity = ingeniero.get();
                //newEntity.setId(entity.getId());
                newEntity.setNombre(entity.getNombre());
                newEntity.setDni(entity.getDni());
                newEntity = repositorio.save(newEntity);
                
                return newEntity;
            } else {
                throw new RecordNotFoundException("ingeniero not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of ingeniero given",0l);
    	}	    
 }
     
    public void deleteIngenieroById(Long id) throws RecordNotFoundException
    {
        Optional<Ingeniero> ingeniero = repositorio.findById(id);
         
        if(ingeniero.isPresent())
        {
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("No ingeniero record exist for given id",id);
        }
    }
    
    
    
    
  
}
