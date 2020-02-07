/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.controlador;

/**
 *
 * @author Samuel Hermosilla Aguilera
 */
import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.servicio.ServicioIngeniero;
import com.proyecto.restfulmarcas.servicio.ServicioPrototipo;
import com.proyecto.restfulmarcas.servicio.ServicioMarca;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/prototipo")
public class ControladorPrototipo {

    @Autowired
    ServicioPrototipo servicio;
    
    @Autowired
    ServicioIngeniero ingeniero;
    

  
    @GetMapping
    public ResponseEntity<List<Prototipo>> getAllPrototipos() {
        List<Prototipo> list = servicio.getAllPrototipos();
        return new ResponseEntity<List<Prototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prototipo> getPrototipoById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Prototipo entity = servicio.getPrototipoById(id);
        return new ResponseEntity<Prototipo>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prototipo> createPrototipo(@Valid @RequestBody Prototipo myPrototipo) {
        Prototipo created = servicio.createPrototipo(myPrototipo);
        return new ResponseEntity<Prototipo>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Prototipo> UpdatePrototipo(@Valid @RequestBody Prototipo myPrototipo)
            throws RecordNotFoundException {
        Prototipo updated = servicio.UpdatePrototipo(myPrototipo);
        return new ResponseEntity<Prototipo>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePrototipoById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        servicio.deletePrototipoById(id);
        return HttpStatus.ACCEPTED;
    }
    
    
    @GetMapping("/buscarPrototipoPorNombre/{nombreclave}")
    public ResponseEntity<List<Prototipo>> getPrototipoByName(@PathVariable("nombreclave") String nombreClave) {
    	List<Prototipo> list = servicio.getPrototipoByName(nombreClave);
        
        return new ResponseEntity<List<Prototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
   
    
    
    
    /*
    @GetMapping("/buscardni/{dni}")
    public ResponseEntity<List<Prototipo>> getPrototipoByDNI(@PathVariable("dni") String dni) {
    	List<Prototipo> list = servicio.getPrototipoByDNI(dni);
 
        return new ResponseEntity<List<Prototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/marcas/{id}")
    public ResponseEntity<List<Marca>> getMarcasByIdPrototipo(@PathVariable("id") Long id) {
    	List<Marca> list = servicio.getMarcasByIdPrototipo(id);
        return new ResponseEntity<List<Marca>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    */
    
    

}

