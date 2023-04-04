package com.mauricio.clinica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauricio.clinica.model.Turno;
import com.mauricio.clinica.model.TurnoDTO;
import com.mauricio.clinica.repository.ITurnoRepository;
import com.mauricio.clinica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger logger = Logger.getLogger(TurnoService.class);
    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Método para crear turnos:
     * @param turno TurnoDTO (Date:fecha, Odontologo:odontologo, Paciente:paciente)
     * @return TurnoDTO (Long:id, Date:fecha, Odontologo:odontologo, Paciente:paciente)
     */
    @Override
    public TurnoDTO crearTurno(TurnoDTO turno) {
        turno.setId(0L);
        logger.info("Creando turno: " + turno);
        Turno turnoGuardado = turnoRepository.save(mapper.convertValue(turno, Turno.class));
        turno.setId(turno.getId());
        logger.info("Turno creado: " + turnoGuardado);
        return mapper.convertValue(turnoGuardado, TurnoDTO.class);
    }

    /**
     * Metodo para Buscar turnos
     * @param id Id de turno a buscar
     * @return TurnoDTO (Long:id, Date:fecha, Odontologo:odontologo, Paciente:paciente)
     * o  null   en caso de no encontrarlo
     */
    @Override
    public TurnoDTO buscarTurno(Long id) {
        logger.info("Buscando turno con ID: " + id);
         Optional<Turno> o = turnoRepository.findById(id);
         if(o.isPresent()){
             logger.info("Turno con ID: " + id + " encontrado.");
             return mapper.convertValue(o, TurnoDTO.class);
         }
        logger.info("Turno con ID: " + id + " no encontrado.");
         return null;
    }

    /**
     * Metodo para editar/modificar un turno
     * @param turno TurnoDTO (Long:id, Date:fecha, Odontologo:odontologo, Paciente:paciente)
     * @return TurnoDTO (Long:id, Date:fecha, Odontologo:odontologo, Paciente:paciente) modificado
     * o null en caso de no existir el id.
     */
    @Override
    public TurnoDTO editarTurno(TurnoDTO turno) {
        logger.info("Editando Turno: "+ turno);
        if(buscarTurno(turno.getId()) != null) {
            Turno guardado = turnoRepository.save(mapper.convertValue(turno, Turno.class));
            logger.info("Editado Turno: "+ guardado);
            return mapper.convertValue(guardado,TurnoDTO.class);
        }
        return null;
    }

    /**
     * Metodo para eliminar un turno
     * @param id El id del turno a eliminar
     * @return true si pudo eliminarlo o false si no existe
     */
    @Override
    public boolean eliminarTurno(Long id) {
        logger.info("Eliminando turno con ID: " + id);
        if(buscarTurno(id) !=null){
            turnoRepository.deleteById(id);
            logger.info("Turno con ID: " + id + " eliminando.");
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener el listado de Turnos
     * @return Coleccion de TurnoDTO
     */
    @Override
    public Collection<TurnoDTO> listarTurnos() {
        logger.info("Creando lista de turnos...");
        List<Turno> turnoList = turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for (Turno o:turnoList) {
            turnosDTO.add(mapper.convertValue(o, TurnoDTO.class));
        }
        logger.info("Lista de turnos creada.");
        return turnosDTO;
    }
}
