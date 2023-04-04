package com.mauricio.clinica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauricio.clinica.model.Domicilio;
import com.mauricio.clinica.model.DomicilioDTO;
import com.mauricio.clinica.repository.IDomicilioRepository;
import com.mauricio.clinica.service.IDomicilioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomicilioService implements IDomicilioService {

    private static final Logger logger = Logger.getLogger(DomicilioService.class);
    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Método para crear domicilios:
     * @param domicilio DomicilioDTO (String:calle, String:numero, String:localidad, String:provincia,
     *                  Paciente:paciente )
     * @return DomicilioDTO (Long:id, String:calle, String:numero, String:localidad, String:provincia,
     *                   Paciente:paciente)
     */
    @Override
    public DomicilioDTO crearDomicilio(DomicilioDTO domicilio) {
        domicilio.setId(0L);
        logger.info("Creando domicilio: " + domicilio);
        Domicilio domicilioGuardado = domicilioRepository.save(mapper.convertValue(domicilio, Domicilio.class));
        domicilio.setId(domicilio.getId());
        logger.info("Domicilio creado: " + domicilioGuardado);
        return mapper.convertValue(domicilioGuardado, DomicilioDTO.class);
    }

    /**
     * Metodo para Buscar domicilios
     * @param id Id de domicilio a buscar
     * @return DomicilioDTO (Long:id, String:calle, String:numero, String:localidad, String:provincia,
     *                       Paciente:paciente) o  null   en caso de no encontrarlo
     */
    @Override
    public DomicilioDTO buscarDomicilio(Long id) {
        logger.info("Buscando domicilio con ID: " + id);
         Optional<Domicilio> o = domicilioRepository.findById(id);
         if(o.isPresent()){
             logger.info("Domicilio con ID: " + id + " encontrado.");
             return mapper.convertValue(o, DomicilioDTO.class);
         }
        logger.info("Domicilio con ID: " + id + " no encontrado.");
         return null;
    }

    /**
     * Metodo para editar/modificar un domicilio
     * @param domicilio DomicilioDTO (Long:id, String:calle, String:numero, String:localidad, String:provincia,
     *                     Paciente:paciente)
     * @return DomicilioDTO (Long:id, String:calle, String:numero, String:localidad, String:provincia,
     *                     Paciente:paciente)
     * o null en caso de no existir el id.
     */
    @Override
    public DomicilioDTO editarDomicilio(DomicilioDTO domicilio) {
        logger.info("Editando Domicilio: "+ domicilio);
        if(buscarDomicilio(domicilio.getId()) != null) {
            Domicilio guardado = domicilioRepository.save(mapper.convertValue(domicilio, Domicilio.class));
            logger.info("Editado Domicilio: "+ guardado);
            return mapper.convertValue(guardado,DomicilioDTO.class);
        }
        return null;
    }

    /**
     * Metodo para eliminar un domicilio
     * @param id El id del domicilio a eliminar
     * @return true si pudo eliminarlo o false si no existe
     */
    @Override
    public boolean eliminarDomicilio(Long id) {
        logger.info("Eliminando domicilio con ID: " + id);
        if(buscarDomicilio(id) !=null){
            domicilioRepository.deleteById(id);
            logger.info("Domicilio con ID: " + id + " eliminando.");
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener el listado de Domicilios
     * @return Coleccion de DomicilioDTO
     */
    @Override
    public Collection<DomicilioDTO> listarDomicilios() {
        logger.info("Creando lista de domicilios...");
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        Set<DomicilioDTO> domiciliosDTO = new HashSet<>();
        for (Domicilio o:domicilioList) {
            domiciliosDTO.add(mapper.convertValue(o, DomicilioDTO.class));
        }
        logger.info("Lista de domicilios creada.");
        return domiciliosDTO;
    }
}
