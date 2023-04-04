package com.mauricio.clinica.controller;
import com.mauricio.clinica.model.DomicilioDTO;
import com.mauricio.clinica.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    IDomicilioService domicilioService;

    @GetMapping("/listar")
    public Collection<DomicilioDTO> listarDomicilios(){
        return domicilioService.listarDomicilios();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarDomicilio(@RequestBody DomicilioDTO domicilio){
        return new ResponseEntity<DomicilioDTO>(
                domicilioService.crearDomicilio(domicilio),
                HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDomicilio(@PathVariable Long id) {
        DomicilioDTO domicilio = domicilioService.buscarDomicilio(id);
        if(domicilio != null){
            return new  ResponseEntity<DomicilioDTO>(domicilio, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Domicilio no encontrado", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarDomicilio(@RequestBody DomicilioDTO domicilio){
        DomicilioDTO domicilioGuardado = domicilioService.editarDomicilio(domicilio);
        if(domicilioGuardado != null){
            return new  ResponseEntity<DomicilioDTO>(domicilioGuardado, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Id de Domicilio inexistente", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id) {
        if(domicilioService.eliminarDomicilio(id)) {
            return new ResponseEntity<String>("Domicilio eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Domicilio no encontrado", HttpStatus.NOT_FOUND);
    }
}
