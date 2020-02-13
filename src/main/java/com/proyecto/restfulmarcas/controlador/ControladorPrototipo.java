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
import com.proyecto.restfulmarcas.interfaces.IIngeniero;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.servicio.ServicioIngeniero;
import com.proyecto.restfulmarcas.servicio.ServicioPrototipo;
import com.proyecto.restfulmarcas.servicio.ServicioMarca;
import io.swagger.annotations.ApiOperation;
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
    
    @Autowired
    ServicioMarca marca;
    


    @ApiOperation(
            value = "Return: todos los prototipos",
            notes = "No necesita pasarle nada.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping
    public ResponseEntity<List<Prototipo>> getAllPrototipos() {
        List<Prototipo> list = servicio.getAllPrototipos();
        return new ResponseEntity<List<Prototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un prototipo",
            notes = "Necesita pasarle el id del prototipo.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Prototipo> getPrototipoById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Prototipo entity = servicio.getPrototipoById(id);
        return new ResponseEntity<Prototipo>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un prototipo",
            notes = "No necesita pasarle nada, necesita body",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PostMapping
    public ResponseEntity<Prototipo> createPrototipo(@Valid @RequestBody Prototipo myPrototipo) throws Exception {
        Prototipo created = servicio.createPrototipo(myPrototipo);
        return new ResponseEntity<Prototipo>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un prototipo actualizado",
            notes = "No necesita pasarle nada, necesita body",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping
    public ResponseEntity<Prototipo> UpdatePrototipo(@Valid @RequestBody Prototipo myPrototipo)
            throws RecordNotFoundException {
        Prototipo updated = servicio.UpdatePrototipo(myPrototipo);
        return new ResponseEntity<Prototipo>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina un prototipo.",
            notes = "Necesita pasarle el id.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @DeleteMapping("/{id}")
    public HttpStatus deletePrototipoById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        servicio.deletePrototipoById(id);
        return HttpStatus.ACCEPTED;
    }
    
    
    @ApiOperation(
            value = "Return: un prototipo",
            notes = "Necesita pasarle el nombre clave del prototipo.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/buscarPrototipoPorNombre/{nombreclave}")
    public ResponseEntity<List<Prototipo>> getPrototipoByName(@PathVariable("nombreclave") String nombreClave) {
    	List<Prototipo> list = servicio.getPrototipoByName(nombreClave);
        
        return new ResponseEntity<List<Prototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "Return: todos los ingenieros que han realizado este prototipo",
            notes = "Necesita pasarle el id del prototipo.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/ingenierosPrototiposPorId/{id}")
     public ResponseEntity<List<IIngeniero>> getIngenierosByIdPrototipo(@PathVariable("id") Long id) {
    	List<IIngeniero> list = servicio.getIngenierosByIdPrototipo(id);
        return new ResponseEntity<List<IIngeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }
     
     
    @ApiOperation(
            value = "Return: todos los ingenieros que han realizado este prototipo",
            notes = "Necesita pasarle el nombre clave del prototipo",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/ingenierosPrototiposPorNombreClave/{nombreClave}")
     public ResponseEntity<List<IIngeniero>> getIngenierosByNombreClavePrototipo(@PathVariable("nombreClave") String nombreClave) {
    	List<IIngeniero> list = servicio.getIngenierosByNombreClavePrototipo(nombreClave);
        return new ResponseEntity<List<IIngeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    } 
    
    
    
    @ApiOperation(
            value = "Return: un prototipo con un nuevo ingeniero",
            notes = "Necesita pasarle el id del prototipo y el id del ingeniero",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping("/agregarIngeniero/{id_prototipo}/{id_ingeniero}")
    public ResponseEntity<Prototipo> addPrototipoIngeniero(@PathVariable("id_ingeniero") Long id_ingeniero,
            @PathVariable("id_prototipo") Long id_prototipo/*,
            @Valid @RequestBody Prototipo myPrototipo*/) {
        Prototipo created = servicio.addPrototipoIngeniero(id_prototipo, id_ingeniero);
        return new ResponseEntity<Prototipo>(created, new HttpHeaders(), HttpStatus.OK);
    }
   
    

    @ApiOperation(
            value = "Return: un prototipo con un ingeniero borrado",
            notes = "Necesita pasarle el id del prototipo y el id del ingeniero a borrar de dicho prototipo",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping("/borrarIngenieroPrototipo/{id_prototipo}/{id_ingeniero}")
    public ResponseEntity<Prototipo> removeIngenieroByPrototipo(@PathVariable("id_ingeniero") Long id_ingeniero,
            @PathVariable("id_prototipo") Long id_prototipo /*,
            @Valid @RequestBody Prototipo myPrototipo*/) {
        Prototipo created = servicio.removeIngenieroByPrototipo(id_prototipo, id_ingeniero);
        return new ResponseEntity<Prototipo>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    

}

