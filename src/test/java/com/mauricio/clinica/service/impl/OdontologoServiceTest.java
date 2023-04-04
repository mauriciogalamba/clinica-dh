package com.mauricio.clinica.service.impl;

import com.mauricio.clinica.model.OdontologoDTO;
import com.mauricio.clinica.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    private static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    @Autowired
    private IOdontologoService odontologoService;

    private static OdontologoDTO odontologo;
    private static OdontologoDTO guardado;

    @BeforeAll
    static void setUp() {
        odontologo = new OdontologoDTO();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Rompedientes");
        odontologo.setMatricula(2525L);

    }


    @Test
    @Order(1)
    void crearOdontologo() throws Exception {

        // guardo el DTO en la base y comparo los datos con la respuesta
        guardado = odontologoService.crearOdontologo(odontologo);

        assertEquals(guardado.getNombre(), "Pedro");
    }

    @Test
    @Order(2)
    void buscarOdontologo() {

        OdontologoDTO buscado = odontologoService.buscarOdontologo(guardado.getId());

        assertEquals(buscado.getId(), guardado.getId());

    }

    @Test
    @Order(3)
    void eliminarOdontologo() {

        Long id  = guardado.getId();

        odontologoService.eliminarOdontologo(id);

        guardado = odontologoService.buscarOdontologo(id);

        assertNull(guardado);
    }

}