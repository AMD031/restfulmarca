/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.restfulmarcas.controlador;

/**
 *
 * @author Antonio Martinez Diaz
 */
import com.proyecto.restfulmarcas.excepcion.RecordNotFoundException;
import com.proyecto.restfulmarcas.interfaces.IIngeniero;
import com.proyecto.restfulmarcas.interfaces.IMarca;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;
import com.proyecto.restfulmarcas.repositorio.RepositorioMarca;
import com.proyecto.restfulmarcas.servicio.ServicioIngeniero;
import com.proyecto.restfulmarcas.servicio.ServicioMarca;
import java.awt.print.Pageable;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/marca")
public class ControladorMarca {

   @Autowired
    ServicioMarca servicio;

   @Autowired
    ServicioIngeniero ingeniero;
 
   
   
    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> list = servicio.getAllMarcas();
        return new ResponseEntity<List<Marca>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Marca entity = servicio.getMarcaById(id);
        return new ResponseEntity<Marca>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Marca> createMarca(@Valid @RequestBody Marca myMarca) {
        Marca created = servicio.createMarca(myMarca);
        return new ResponseEntity<Marca>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Marca> UpdateMarca(@Valid @RequestBody Marca myMarca)
            throws RecordNotFoundException {
        Marca updated = servicio.UpdateMarca(myMarca);
        return new ResponseEntity<Marca>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteMarcaById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        servicio.deleteMarcaById(id);
        return HttpStatus.ACCEPTED;
    }
    
    @GetMapping("/ingenierosPorIdmarca/{id}")
    public ResponseEntity<List<IIngeniero>> getMarcasByIdIngeniero(@PathVariable("id") Long id) {
    	List<IIngeniero> list = servicio.getIngenierosByIdMarca(id);
        return new ResponseEntity<List<IIngeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/ingenierosPormarca/{nombreMarca}")
    public ResponseEntity<List<IIngeniero>> getMarcasByIdIngeniero(@PathVariable("nombreMarca") String nombreMarca) {
    	List<IIngeniero> list = servicio.getIngenierosByNameMarca(nombreMarca);
        return new ResponseEntity<List<IIngeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    
 
    
    

}
