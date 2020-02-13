/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.servicio;

import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.interfaces.IIngeniero;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;
import org.springframework.transaction.annotation.Transactional;

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
import com.proyecto.restfulmarcas.repositorio.RepositorioPrototipo;
import java.util.Set;

@Service
public class ServicioMarca {
     
  @Autowired
  RepositorioMarca repositorio;

  @Autowired
  RepositorioIngeniero repositorioIngeniero;
  
  @Autowired
  RepositorioPrototipo repositorioPrototipo;
  

          
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
    
    @Transactional(rollbackFor = Exception.class)
    public Marca createMarca(Marca entity) throws Exception{
        entity = repositorio.save(entity);
        if(entity ==null){
           throw new Exception("no se pudeo crear");
        }
        return entity;
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Marca UpdateMarca(Marca entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId_marca()!=null)
    	{
    	  Optional<Marca> marca = repositorio.findById(entity.getId_marca());
        
            if(marca.isPresent())
            {
                Marca newEntity = marca.get();
                //newEntity.setId(entity.getId());
                newEntity.setNombreMarca(entity.getNombreMarca());
                newEntity.setPais(entity.getPais());
                newEntity = repositorio.save(newEntity);
                
                return newEntity;
            } else {
                throw new RecordNotFoundException("marca not found",entity.getId_marca());
            }
        }else{
    		throw new RecordNotFoundException("No id of marca given",0l);
    	}	    
 }
   @Transactional(rollbackFor = Exception.class)
    public void deleteMarcaById(Long id) throws RecordNotFoundException
    {
        Optional<Marca> marca = repositorio.findById(id);
         
        if(marca.isPresent())
        {
           Marca m = marca.get();
           Set<Ingeniero> ingenieros =  m.getIngenieros();
           for (Ingeniero ingeniero : ingenieros) {
               for(Prototipo p : ingeniero.getPrototipos()){
                  repositorioPrototipo.delete(p);
               }
            }
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("No marca record exist for given id",id);
        }
    }

    public List<IIngeniero> getIngenierosByIdMarca(Long id) {
       
        List<IIngeniero> marcas = repositorio.getIngenierosByIdMarca(id);
         
        if(marcas.size() > 0) {
            return marcas;
        } else {
            return new ArrayList<IIngeniero>();
        }
        
        
    }

    public List<IIngeniero> getIngenierosByNameMarca(String nombreMarca) {
        List<IIngeniero> marcas = repositorio.getIngenierosByNameMarca(nombreMarca);
         
        if(marcas.size() > 0) {
            return marcas;
        } else {
            return new ArrayList<IIngeniero>();
        }
        
        
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    public Marca cambiarIngenieroMarca(Long id_marca_nueva, Long id_marca_antigua, Long id_ingeniero) {
         Optional<Marca> marcaAntigua = repositorio.findById(id_marca_antigua);
         Optional<Marca> marcaNueva= repositorio.findById(id_marca_nueva);
         Optional<Ingeniero> ingeniero= repositorioIngeniero.findById(id_ingeniero);
         
         if(marcaAntigua.isPresent()){
            if(marcaNueva.isPresent()){
              if(ingeniero.isPresent()){
                  
                   Marca marcaAntiguaEditada = marcaAntigua.get();
                   Marca marcaNuevaEditada =    marcaNueva.get();                    
                   Ingeniero ingenieroEditado = ingeniero.get();
                   
                   if(marcaAntiguaEditada.getIngenieros().contains(ingenieroEditado) && 
                      !marcaNuevaEditada.getIngenieros().contains(ingenieroEditado) ){
                      
                       marcaAntiguaEditada.removeIngeniero(ingenieroEditado);
                       marcaNuevaEditada.addIngeniero(ingenieroEditado);
                   }
             
                   
                   
                    return marcaNuevaEditada;
              }else{
                  throw new RecordNotFoundException("No ingeniero record exist for given id",id_ingeniero); 
              }            
            }else{
               throw new RecordNotFoundException("No marca nueva record exist for given id",id_marca_nueva); 
            }
          }else{
              throw new RecordNotFoundException("No marca antigua record exist for given id",id_marca_antigua);
         }
  
    }
  
    
    
    
    
   }

  

    
    


    
    
    
   
    

