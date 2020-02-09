/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.servicio;

import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.interfaces.IMarca;
import com.proyecto.restfulmarcas.interfaces.IPrototipo;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.modelo.Prototipo;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;
import com.proyecto.restfulmarcas.repositorio.RepositorioMarca;
import com.proyecto.restfulmarcas.repositorio.RepositorioPrototipo;
import java.sql.Time;
import org.omg.CORBA.TIMEOUT;

@Service
public class ServicioIngeniero{

    @Autowired
    RepositorioIngeniero repositorio;

    @Autowired
    RepositorioPrototipo repositorioPrototipo;

    @Autowired
    RepositorioMarca repositorioMarca;
    public List<Ingeniero> getAllIngenieros() {
    
        List<Ingeniero> ingenieroList = repositorio.findAll();
      
        if (ingenieroList.size() > 0) {
            return ingenieroList;
        } else {
            return new ArrayList<Ingeniero>();
        }
    }

    public Ingeniero getIngenieroById(Long id) throws RecordNotFoundException {
        Optional<Ingeniero> ingeniero = repositorio.findById(id);

        if (ingeniero.isPresent()) {
            return ingeniero.get();
        } else {
            throw new RecordNotFoundException("No ingeniero record exist for given id", id);
        }
    }

//    public Ingeniero createIngeniero(Ingeniero entity){
//        //entity = repositorio.save(entity);
//        return entity;
//    }
//    
    @Transactional(rollbackFor = Exception.class)
    public Ingeniero createIngeniero(Long id_marca, Ingeniero entity)throws RecordNotFoundException {
        Optional<Marca> marca = repositorioMarca.findById(id_marca);
        if (marca.isPresent()) {
            Marca marcaEditada = marca.get();
            entity.setMarca(marcaEditada);
            entity = repositorio.save(entity);
            marcaEditada.addIngeniero(entity);
            return entity;
        } else {
            throw new RecordNotFoundException("marca not found", id_marca);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Ingeniero UpdateIngeniero(Ingeniero entity) throws RecordNotFoundException {

        if (entity.getId_ingeniero() != null) {
            Optional<Ingeniero> ingeniero = repositorio.findById(entity.getId_ingeniero());

            if (ingeniero.isPresent()) {
                Ingeniero newEntity = ingeniero.get();
                //newEntity.setId(entity.getId());
                newEntity.setNombre(entity.getNombre());
                newEntity.setDni(entity.getDni());
                newEntity = repositorio.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("ingeniero not found", entity.getId_ingeniero());
            }
        } else {
            throw new RecordNotFoundException("No id of ingeniero given", 0l);
        }
    }

    
    @Transactional(rollbackFor = Exception.class)
    public void deleteIngenieroById(Long id) throws RecordNotFoundException {
        Optional<Ingeniero> ingeniero = repositorio.findById(id);
        if (ingeniero.isPresent()) {
           Ingeniero i = ingeniero.get();
            i.removePrototipos();
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("No ingeniero record exist for given id", id);
        }
    }

    public List<Ingeniero> getIngenieroByName(String nombre) {
        List<Ingeniero> ingenieros = repositorio.getIngenieroByName(nombre);

        if (ingenieros.size() > 0) {
            return ingenieros;
        } else {
            return new ArrayList<Ingeniero>();
        }
    }

    public List<Ingeniero> getIngenieroByDNI(String dni) {
        List<Ingeniero> ingenieros = repositorio.getIngenieroByDNI(dni);

        if (ingenieros.size() > 0) {
            return ingenieros;
        } else {
            return new ArrayList<Ingeniero>();
        }
    }

    public List<IMarca> getMarcasByIdIngeniero(Long id) {

        List<IMarca> marcas = repositorio.getMarcasByIdIngeniero(id);

        if (marcas.size() > 0) {
            return marcas;
        } else {
            return new ArrayList<IMarca>();
        }

    }

    public List<IMarca> getMarcasByDniIngeniero(String dni) {

        List<IMarca> marcas = repositorio.getMarcasByDniIngeniero(dni);
        if (marcas.size() > 0) {
            return marcas;
        } else {
            return new ArrayList<IMarca>();
        }

    }

    public List<IPrototipo> getPrototipoByIdIngeniero(Long id) {

        List<IPrototipo> prototipos = repositorio.getPrototipoByIdIngeniero(id);

        if (prototipos.size() > 0) {
            return prototipos;
        } else {
            return new ArrayList<IPrototipo>();
        }

    }

    public List<IPrototipo> getPrototipoByDniIngeniero(String dni) {
        List<IPrototipo> prototipos = repositorio.getPrototipoByDniIngeniero(dni);

        if (prototipos.size() > 0) {
            return prototipos;
        } else {
            return new ArrayList<IPrototipo>();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Ingeniero addIngenieroPrototipo(Long id_ingeniero, Long id_prototipo) {

        Optional<Ingeniero> ingeniero = repositorio.findById(id_ingeniero);
        Optional<Prototipo> prototipo = repositorioPrototipo.findById(id_prototipo);

        if (prototipo.isPresent()) {
            if (ingeniero.isPresent()) {
                Ingeniero ingenieroEditado = ingeniero.get();
                Prototipo prototipo1Editado = prototipo.get();
                ingenieroEditado.addPrototio(prototipo1Editado);
                return ingenieroEditado;
            } else {
                throw new RecordNotFoundException("ingeniero not found", id_ingeniero);
            }

        } else {
            throw new RecordNotFoundException("prototipo not found", id_prototipo);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Ingeniero removePrototipoByIdIngeniero(Long id_ingeniero, Long id_prototipo) {
        Optional<Ingeniero> ingeniero = repositorio.findById(id_ingeniero);
        Optional<Prototipo> prototipo = repositorioPrototipo.findById(id_prototipo);

        if (prototipo.isPresent()) {
            if (ingeniero.isPresent()) {
                Ingeniero ingenieroEditado = ingeniero.get();
                Prototipo prototipo1Editado = prototipo.get();
                ingenieroEditado.removePrototipo(prototipo1Editado);
                 return ingenieroEditado;
            } else {
                throw new RecordNotFoundException("ingeniero not found", id_prototipo);
            }

        } else {
            throw new RecordNotFoundException("prototipo not found", id_prototipo);
        }
    }

}
