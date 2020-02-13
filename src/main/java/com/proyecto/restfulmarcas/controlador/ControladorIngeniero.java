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
import com.proyecto.restfulmarcas.interfaces.IMarca;
import com.proyecto.restfulmarcas.interfaces.IPrototipo;
import com.proyecto.restfulmarcas.modelo.Ingeniero;
import com.proyecto.restfulmarcas.modelo.Marca;
import com.proyecto.restfulmarcas.servicio.ServicioIngeniero;
import com.proyecto.restfulmarcas.servicio.ServicioMarca;
import com.proyecto.restfulmarcas.servicio.ServicioPrototipo;
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

//Swagger documentation imports
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ingeniero")
public class ControladorIngeniero {

    @Autowired
    ServicioIngeniero servicio;

    @Autowired
    ServicioPrototipo prototipo;

    @Autowired
    ServicioMarca servicioMarca;


    /*
    @ApiOperation(value = "Devuelve todos los ingenieros.", notes = "No necesita ningún valor.")
    @RequestMapping(value = "/ingeniero", method = RequestMethod.GET)
    public String getAllIngenierosSwagger(@RequestParam(required = false, defaultValue = "") String name) {
        if (name.isEmpty()) {
            return "Bienvenido a Swagger ";
        }
        return "Bienvenido " + name + "!";
    }
     */
    @ApiOperation(
            value = "Return: todos los ingenieros",
            notes = "No necesita pasarle ningún valor.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping
    public ResponseEntity<List<Ingeniero>> getAllIngenieros() {
        List<Ingeniero> list = servicio.getAllIngenieros();
        return new ResponseEntity<List<Ingeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un ingeniero",
            notes = "Necesita pasarle el id.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Ingeniero> getIngenieroById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Ingeniero entity = servicio.getIngenieroById(id);
        return new ResponseEntity<Ingeniero>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un ingeniero",
            notes = "Necesita pasarle el id de la marca.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PostMapping("/nuevoIngeniero/{id_marca}")
    public ResponseEntity<Ingeniero> createIngeniero(@PathVariable("id_marca") Long id_marca, @Valid @RequestBody Ingeniero myIngeniero) {
        Ingeniero created = servicio.createIngeniero(id_marca, myIngeniero);
        return new ResponseEntity<Ingeniero>(created, new HttpHeaders(), HttpStatus.OK);
    }

    //hacer inverso
    @ApiOperation(
            value = "Return: un ingeniero con un nuevo prototipo",
            notes = "Necesita pasarle el id del ingeniero y el id del prototipo",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping("/agregarPrototipo/{id_ingeniero}/{id_prototipo}")
    public ResponseEntity<Ingeniero> addIngenieroPrototipo(@PathVariable("id_prototipo") Long id_prototipo,
            @PathVariable("id_ingeniero") Long id_ingeniero/*,
            @Valid @RequestBody Ingeniero myIngeniero*/) {
        Ingeniero created = servicio.addIngenieroPrototipo(id_ingeniero, id_prototipo);
        return new ResponseEntity<Ingeniero>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: ingeniero modificado",
            notes = "Necesita pasarle un ingeniero mediante un body.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping
    public ResponseEntity<Ingeniero> UpdateIngeniero(@Valid @RequestBody Ingeniero myIngeniero)
            throws RecordNotFoundException {
        Ingeniero updated = servicio.UpdateIngeniero(myIngeniero);
        return new ResponseEntity<Ingeniero>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina un ingeniero según el id especificado",
            notes = "Necesita pasarle el id del ingeniero a borrar.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @DeleteMapping("/{id}")
    public HttpStatus deleteIngenieroById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        servicio.deleteIngenieroById(id);
        return HttpStatus.ACCEPTED;
    }

    //hacer inverso
    @ApiOperation(
            value = "Return: un ingeniero con un prototipo borrado",
            notes = "Necesita pasarle el id del ingeniero y el id del prototipo a borrar de dicho ingeniero",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @PutMapping("borrarPrototipoIngeniero/{id_ingeniero}/{id_prototipo}")
    public ResponseEntity<Ingeniero> removePrototipoByIdIngeniero(@PathVariable("id_prototipo") Long id_prototipo,
            @PathVariable("id_ingeniero") Long id_ingeniero/*,
            @Valid @RequestBody Ingeniero myIngeniero*/) {
        Ingeniero created = servicio.removePrototipoByIdIngeniero(id_ingeniero, id_prototipo);
        return new ResponseEntity<Ingeniero>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un ingeniero",
            notes = "Necesita pasarle el nombre del ingeniero a buscar.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/buscarIngenerioPorNombre/{nombre}")
    public ResponseEntity<List<Ingeniero>> getIngenieroByName(@PathVariable("nombre") String nombre) {
        List<Ingeniero> list = servicio.getIngenieroByName(nombre);

        return new ResponseEntity<List<Ingeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: un ingeniero",
            notes = "Necesita pasarle el dni del ingeniero a buscar.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/buscarIngenieroPorDni/{dni}")
    public ResponseEntity<List<Ingeniero>> getIngenieroByDNI(@PathVariable("dni") String dni) {
        List<Ingeniero> list = servicio.getIngenieroByDNI(dni);

        return new ResponseEntity<List<Ingeniero>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    
    @ApiOperation(
            value = "Return: la marca en la cual trabaja el ingeniero",
            notes = "Necesita pasarle el id del ingeniero.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/marcaTrabajaPorId/{id}")
    public ResponseEntity<List<IMarca>> getMarcasByIdIngeniero(@PathVariable("id") Long id) {
        List<IMarca> list = servicio.getMarcasByIdIngeniero(id);
        return new ResponseEntity<List<IMarca>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: las marcas en la cual trabaja el ingeniero",
            notes = "Necesita pasarle el dni del ingeniero.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/marcaTrabajaPorDni/{dni}")
    public ResponseEntity<List<IMarca>> getMarcasByIdIngeniero(@PathVariable("dni") String dni) {
        List<IMarca> list = servicio.getMarcasByDniIngeniero(dni);
        return new ResponseEntity<List<IMarca>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return: los prototipos que ha realizado cada ingeniero",
            notes = "Necesita pasarle el id del ingeniero.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/prototiposIngeniroPorId/{id}")
    public ResponseEntity<List<IPrototipo>> getPrototipoByIdIngeniero(@PathVariable("id") Long id) {
        List<IPrototipo> list = servicio.getPrototipoByIdIngeniero(id);
        return new ResponseEntity<List<IPrototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    
    @ApiOperation(
            value = "Return: los prototipos que ha realizado cada ingeniero",
            notes = "Necesita pasarle el dni del ingeniero a buscar.",
            response = Ingeniero.class,
            responseContainer = "List"
    )
    @GetMapping("/prototiposIngeniroPorDni/{dni}")
    public ResponseEntity<List<IPrototipo>> getPrototipoByDniIngeniero(@PathVariable("dni") String dni) {
        List<IPrototipo> list = servicio.getPrototipoByDniIngeniero(dni);
        return new ResponseEntity<List<IPrototipo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
