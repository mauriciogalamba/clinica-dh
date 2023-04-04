package com.mauricio.clinica.controller;
import com.mauricio.clinica.model.OdontologoDTO;
import com.mauricio.clinica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    IOdontologoService odontologoService;

    @GetMapping("/listar")
    public Collection<OdontologoDTO> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarOdontologo(@RequestBody OdontologoDTO odontologo){
        return new ResponseEntity<OdontologoDTO>(
                odontologoService.crearOdontologo(odontologo),
                HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarOdontologo(@PathVariable Long id) {
        OdontologoDTO odontologo = odontologoService.buscarOdontologo(id);
        if(odontologo != null){
            return new  ResponseEntity<OdontologoDTO>(odontologo, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Odontologo no encontrado", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody OdontologoDTO odontologo){
        OdontologoDTO odontologoGuardado = odontologoService.editarOdontologo(odontologo);
        if(odontologoGuardado != null){
            return new  ResponseEntity<OdontologoDTO>(odontologoGuardado, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Id de Odontologo inexistente", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        if(odontologoService.eliminarOdontologo(id)) {
            return new ResponseEntity<String>("Odontologo eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Odontologo no encontrado", HttpStatus.NOT_FOUND);
    }
}
