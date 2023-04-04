package com.mauricio.clinica.controller;
import com.mauricio.clinica.model.TurnoDTO;
import com.mauricio.clinica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    ITurnoService turnoService;

    @GetMapping("/listar")
    public Collection<TurnoDTO> listarTurnos(){
        return turnoService.listarTurnos();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoDTO turno){
        return new ResponseEntity<TurnoDTO>(
                turnoService.crearTurno(turno),
                HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarTurno(@PathVariable Long id) {
        TurnoDTO turno = turnoService.buscarTurno(id);
        if(turno != null){
            return new  ResponseEntity<TurnoDTO>(turno, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Turno no encontrado", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarTurno(@RequestBody TurnoDTO turno){
        TurnoDTO turnoGuardado = turnoService.editarTurno(turno);
        if(turnoGuardado != null){
            return new  ResponseEntity<TurnoDTO>(turnoGuardado, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Id de Turno inexistente", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        if(turnoService.eliminarTurno(id)) {
            return new ResponseEntity<String>("Turno eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Turno no encontrado", HttpStatus.NOT_FOUND);
    }
}
