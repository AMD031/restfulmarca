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
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.servicio.ServicioIngeniero;
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
@RequestMapping("/ingeniero")
public class ControladorIngeniero
{
    @Autowired
    ServicioIngeniero servicio;
 
    @GetMapping
    public ResponseEntity<List<Ingeniero>> getAllIngenieros() {
        List<Ingeniero> list = servicio.getAllIngenieros();
        return new ResponseEntity<List<Ingeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Ingeniero> getIngenieroById(@PathVariable("id") Long id)                                           throws RecordNotFoundException {
    	Ingeniero entity = servicio.getIngenieroById(id);
        return new ResponseEntity<Ingeniero>(entity, new HttpHeaders(), HttpStatus.OK);
    }
  
    
   @PostMapping
    public ResponseEntity<Ingeniero> createIngeniero(@Valid @RequestBody Ingeniero myIngeniero){
    	Ingeniero created = servicio.createIngeniero(myIngeniero);
        return new ResponseEntity<Ingeniero>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Ingeniero> UpdateIngeniero(@Valid @RequestBody Ingeniero myIngeniero)
                                                    throws RecordNotFoundException {
    	Ingeniero updated = servicio.UpdateIngeniero(myIngeniero);
        return new ResponseEntity<Ingeniero>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteIngenieroById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        servicio.deleteIngenieroById(id);
        return HttpStatus.ACCEPTED;
    }
 
}
