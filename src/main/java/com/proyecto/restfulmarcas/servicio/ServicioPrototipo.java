package com.proyecto.restfulmarcas.servicio;

import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.interfaces.IMarca;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Prototipo createPrototipo(Prototipo entity) {
        entity = repositorio.save(entity);
        return entity;
    }

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

    public void deletePrototipoById(Long id) throws RecordNotFoundException {
        Optional<Prototipo> prototipo = repositorio.findById(id);

        if (prototipo.isPresent()) {
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

}
