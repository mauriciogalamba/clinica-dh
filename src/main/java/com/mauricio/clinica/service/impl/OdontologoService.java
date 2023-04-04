package com.mauricio.clinica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauricio.clinica.model.Odontologo;
import com.mauricio.clinica.model.OdontologoDTO;
import com.mauricio.clinica.repository.IOdontologoRepository;
import com.mauricio.clinica.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Método para crear odontologos:
     * @param odontologo OdontologoDTO (String:nombre, String:apellido, Long:matricula)
     * @return OdontologoDTO (Long:id, String:nombre, String:apellido, Long:matricula)
     */
    @Override
    public OdontologoDTO crearOdontologo(OdontologoDTO odontologo) {
        odontologo.setId(0L);
        logger.info("Creando odontologo: " + odontologo);
        Odontologo odontologoGuardado = odontologoRepository.save(mapper.convertValue(odontologo, Odontologo.class));
        odontologo.setId(odontologo.getId());
        logger.info("Odontologo creado: " + odontologoGuardado);
        return mapper.convertValue(odontologoGuardado, OdontologoDTO.class);
    }

    /**
     * Metodo para Buscar odontologos
     * @param id Id de odontologo a buscar
     * @return OdontologoDTO (Long:id, String:nombre, String:apellido, Long:matricula)
     * o  null   en caso de no encontrarlo
     */
    @Override
    public OdontologoDTO buscarOdontologo(Long id) {
        logger.info("Buscando odontologo con ID: " + id);
         Optional<Odontologo> o = odontologoRepository.findById(id);
         if(o.isPresent()){
             logger.info("Odontologo con ID: " + id + " encontrado.");
             return mapper.convertValue(o, OdontologoDTO.class);
         }
        logger.info("Odontologo con ID: " + id + " no encontrado.");
         return null;
    }

    /**
     * Metodo para editar/modificar un odontologo
     * @param odontologo OdontologoDTO (Long:id, String:nombre, String:apellido, Long:matricula)
     * @return OdontologoDTO (Long:id, String:nombre, String:apellido, Long:matricula) modificado
     * o null en caso de no existir el id.
     */
    @Override
    public OdontologoDTO editarOdontologo(OdontologoDTO odontologo) {
        logger.info("Editando Odontologo: "+ odontologo);
        if(buscarOdontologo(odontologo.getId()) != null) {
            Odontologo guardado = odontologoRepository.save(mapper.convertValue(odontologo, Odontologo.class));
            logger.info("Editado Odontologo: "+ guardado);
            return mapper.convertValue(guardado,OdontologoDTO.class);
        }
        return null;
    }

    /**
     * Metodo para eliminar un odontologo
     * @param id El id del odontologo a eliminar
     * @return true si pudo eliminarlo o false si no existe
     */
    @Override
    public boolean eliminarOdontologo(Long id) {
        logger.info("Eliminando odontologo con ID: " + id);
        if(buscarOdontologo(id) !=null){
            odontologoRepository.deleteById(id);
            logger.info("Odontologo con ID: " + id + " eliminando.");
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener el listado de Odontologos
     * @return Coleccion de OdontologoDTO
     */
    @Override
    public Collection<OdontologoDTO> listarOdontologos() {
        logger.info("Creando lista de odontologos...");
        List<Odontologo> odontologoList = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for (Odontologo o:odontologoList) {
            odontologosDTO.add(mapper.convertValue(o, OdontologoDTO.class));
        }
        logger.info("Lista de odontologos creada.");
        return odontologosDTO;
    }
}

/*
@Configuration
    public class WebSecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((authz) -> authz
                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults());
            return http.build();
        }



*/