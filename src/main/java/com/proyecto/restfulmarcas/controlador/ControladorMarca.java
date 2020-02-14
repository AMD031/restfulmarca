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
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.modelo.Prototipo;
import com.proyecto.restfulmarcas.repositorio.RepositorioIngeniero;
import com.proyecto.restfulmarcas.repositorio.RepositorioMarca;
import com.proyecto.restfulmarcas.servicio.ServicioIngeniero;
import com.proyecto.restfulmarcas.servicio.ServicioMarca;
import com.proyecto.restfulmarcas.servicio.ServicioPrototipo;
import io.swagger.annotations.ApiOperation;
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
  
   @Autowired
   ServicioPrototipo prototipo;
   
   
    @ApiOperation(
            value = "Return: todas las marcas",
            notes = "No necesita pasarle ningún valor.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> list = servicio.getAllMarcas();
        return new ResponseEntity<List<Marca>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: una marca.",
            notes = "No necesita pasarle el id.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Marca entity = servicio.getMarcaById(id);
        return new ResponseEntity<Marca>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: una marca",
            notes = "No necesita pasarle ningún valor, necesita body",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PostMapping
    public ResponseEntity<Marca> createMarca(@Valid @RequestBody Marca myMarca) throws Exception {
        Marca created = servicio.createMarca(myMarca);
        return new ResponseEntity<Marca>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: una marca actualizada",
            notes = "No necesita pasarle ningún valor, necesita body",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping
    public ResponseEntity<Marca> UpdateMarca(@Valid @RequestBody Marca myMarca)
            throws RecordNotFoundException {
        Marca updated = servicio.UpdateMarca(myMarca);
        return new ResponseEntity<Marca>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina una marca según su id",
            notes = "necesitas pasarle el id de la marca a borrar.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @DeleteMapping("/{id}")
    public HttpStatus deleteMarcaById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        servicio.deleteMarcaById(id);
        return HttpStatus.ACCEPTED;
    }
    
    @ApiOperation(
            value = "Return: todos los ingenieros que trabajan para esa marca",
            notes = "Necesita el id de la marca.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/ingenierosPorIdmarca/{id}")
    public ResponseEntity<List<IIngeniero>> getIngenierosByIdMarca(@PathVariable("id") Long id) {
    	List<IIngeniero> list = servicio.getIngenierosByIdMarca(id);
        return new ResponseEntity<List<IIngeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @ApiOperation(
            value = "Return: todos los ingenieros que trabajan para esa marca",
            notes = "Necesita pasarle el nombre de la marca.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/ingenierosPorNombreMarca/{nombreMarca}")
    public ResponseEntity<List<IIngeniero>> getIngenierosByNombreClaveMarca(@PathVariable("nombreMarca") String nombreMarca) {
    	List<IIngeniero> list = servicio.getIngenierosByNameMarca(nombreMarca);
        return new ResponseEntity<List<IIngeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
   
        @ApiOperation(
            value = "Return: devuelve una marca con un ingeniero cambiado",
            notes = "Necesita pasarle el id del ingeniero, id de la marca antigua y el id de la nueva marca",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping("/cambiarIngeneroMarca/{id_marca_antigua}/{id_marca_nueva}/{id_ingeniero}")
    public ResponseEntity<Marca> cambiarIngenieroMarca(@PathVariable("id_marca_antigua") Long id_marca_antigua,
            @PathVariable("id_marca_nueva") Long id_marca_nueva, @PathVariable("id_ingeniero") Long id_ingeniero /*,
            @Valid @RequestBody Prototipo myPrototipo*/) {
        Marca created = servicio.cambiarIngenieroMarca( id_marca_nueva, id_marca_antigua, id_ingeniero);
        return new ResponseEntity<Marca>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
}
