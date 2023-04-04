package com.mauricio.clinica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauricio.clinica.model.Paciente;
import com.mauricio.clinica.model.PacienteDTO;
import com.mauricio.clinica.repository.IPacienteRepository;
import com.mauricio.clinica.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger logger = Logger.getLogger(PacienteService.class);
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Método para crear pacientes:
     * @param paciente PacienteDTO (String:nombre, String:apellido, String:dni, Date:fecha_ingreso)
     * @return PacienteDTO (Long:id, String:nombre, String:apellido, String:dni, Date:fecha_ingreso)
     */
    @Override
    public PacienteDTO crearPaciente(PacienteDTO paciente) {
        paciente.setId(0L);
        logger.info("Creando paciente: " + paciente);
        Paciente pacienteGuardado = pacienteRepository.save(mapper.convertValue(paciente, Paciente.class));
        paciente.setId(paciente.getId());
        logger.info("Paciente creado: " + pacienteGuardado);
        return mapper.convertValue(pacienteGuardado, PacienteDTO.class);
    }

    /**
     * Metodo para Buscar pacientes
     * @param id Id de paciente a buscar
     * @return PacienteDTO (Long:id, String:nombre, String:apellido, String:dni, Date:fecha_ingreso)
     * o  null   en caso de no encontrarlo
     */
    @Override
    public PacienteDTO buscarPaciente(Long id) {
        logger.info("Buscando paciente con ID: " + id);
         Optional<Paciente> o = pacienteRepository.findById(id);
         if(o.isPresent()){
             logger.info("Paciente con ID: " + id + " encontrado.");
             return mapper.convertValue(o, PacienteDTO.class);
         }
        logger.info("Paciente con ID: " + id + " no encontrado.");
         return null;
    }

    /**
     * Metodo para editar/modificar un paciente
     * @param paciente PacienteDTO (Long:id, String:nombre, String:apellido, String:dni, Date:fecha_ingreso)
     * @return PacienteDTO (Long:id, String:nombre, String:apellido, String:dni, Date:fecha_ingreso) modificado
     * o null en caso de no existir el id.
     */
    @Override
    public PacienteDTO editarPaciente(PacienteDTO paciente) {
        logger.info("Editando Paciente: "+ paciente);
        if(buscarPaciente(paciente.getId()) != null) {
            Paciente guardado = pacienteRepository.save(mapper.convertValue(paciente, Paciente.class));
            logger.info("Editado Paciente: "+ guardado);
            return mapper.convertValue(guardado,PacienteDTO.class);
        }
        return null;
    }

    /**
     * Metodo para eliminar un paciente
     * @param id El id del paciente a eliminar
     * @return true si pudo eliminarlo o false si no existe
     */
    @Override
    public boolean eliminarPaciente(Long id) {
        logger.info("Eliminando paciente con ID: " + id);
        if(buscarPaciente(id) !=null){
            pacienteRepository.deleteById(id);
            logger.info("Paciente con ID: " + id + " eliminando.");
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener el listado de Pacientes
     * @return Coleccion de PacienteDTO
     */
    @Override
    public Collection<PacienteDTO> listarPacientes() {
        logger.info("Creando lista de pacientes...");
        List<Paciente> pacienteList = pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for (Paciente o:pacienteList) {
            pacientesDTO.add(mapper.convertValue(o, PacienteDTO.class));
        }
        logger.info("Lista de pacientes creada.");
        return pacientesDTO;
    }

}
