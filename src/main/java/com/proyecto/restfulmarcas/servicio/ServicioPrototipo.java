package com.proyecto.restfulmarcas.servicio;

import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.interfaces.IIngeniero;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;


/**
 *
 * @Samuel Hermosilla Aguilera
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.restfulmarcas.repositorio.RepositorioPrototipo;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioPrototipo {

    @Autowired
    RepositorioPrototipo repositorio;
    
    @Autowired
    RepositorioIngeniero repositorioIngeniero;

    public List<Prototipo> getAllPrototipos() {
        List<Prototipo> prototipoList = repositorio.findAll();

        if (prototipoList.size() > 0) {
            return prototipoList;
        } else {
            return new ArrayList<Prototipo>();
        }
    }

    public Prototipo getPrototipoById(Long id) throws RecordNotFoundException {
        Optional<Prototipo> prototipo = repositorio.findById(id);

        if (prototipo.isPresent()) {
            return prototipo.get();
        } else {
            throw new RecordNotFoundException("No prototipo record exist for given id", id);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Prototipo createPrototipo(Prototipo entity) throws Exception {
        
        entity = repositorio.save(entity);
        if(entity==null){
          throw new Exception();
        }
       
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    public Prototipo UpdatePrototipo(Prototipo entity) throws RecordNotFoundException {

        if (entity.getId_prototipo()!= null) {
            Optional<Prototipo> prototipo = repositorio.findById(entity.getId_prototipo());

            if (prototipo.isPresent()) {
                Prototipo newEntity = prototipo.get();
                //newEntity.setId(entity.getId());
                newEntity.setNombreClave(entity.getNombreClave());
                newEntity.setDescripcion(entity.getDescripcion());
                newEntity = repositorio.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("prototipo not found", entity.getId_prototipo());
            }
        } else {
            throw new RecordNotFoundException("No id of prototipo given", 0l);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletePrototipoById(Long id) throws RecordNotFoundException {
        Optional<Prototipo> prototipo = repositorio.findById(id);

        if (prototipo.isPresent()) {
            Prototipo p = prototipo.get();
            p.removeIngenieros();
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("No prototipo record exist for given id", id);
        }
    }

    public List<Prototipo> getPrototipoByName(String nombre) {
      List<Prototipo> prototipos= repositorio.getPrototipoByName(nombre);
         
        if(prototipos.size() > 0) {
            return prototipos;
        } else {
            return new ArrayList<Prototipo>();
        }
        
        
    }

    public List<IIngeniero> getIngenierosByIdPrototipo(Long id) {
        List<IIngeniero> ingenieros= repositorio.getIngenieroByIdPrototipo(id);         
        if(ingenieros.size() > 0) {
            return ingenieros;
        } else {
            return new ArrayList<IIngeniero>();
        }
    }

    public List<IIngeniero> getIngenierosByNombreClavePrototipo(String nombreClave) {
        List<IIngeniero> ingenieros= repositorio.getIngenierosByNombreClavePrototipo(nombreClave);         
        if(ingenieros.size() > 0) {
            return ingenieros;
        } else {
            return new ArrayList<IIngeniero>();
        }
    }



}
