package com.mauricio.clinica.service.impl;

import com.mauricio.clinica.model.PacienteDTO;
import com.mauricio.clinica.service.IPacienteService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {
    private static final Logger logger = Logger.getLogger(PacienteServiceTest.class);
    @Autowired
    private IPacienteService pacienteService;

    private static PacienteDTO paciente;
    private static PacienteDTO guardado;

    @BeforeAll
    static void setUp() {
        paciente = new PacienteDTO();
        paciente.setNombre("Luis Antonio");
        paciente.setApellido("Dientes Rotos");
        paciente.setDni("35444777");
        paciente.setFecha_ingreso(new Date(2020,10,15));

    }


    @Test
    @Order(1)
    void crearPaciente() throws Exception {

        // guardo el DTO en la base y comparo los datos con la respuesta
        guardado = pacienteService.crearPaciente(paciente);
        assertEquals(guardado.getNombre(), "Luis Antonio");
    }

    @Test
    @Order(2)
    void buscarPaciente() throws Exception {

        PacienteDTO buscado = pacienteService.buscarPaciente(guardado.getId());
        assertNotNull(buscado);
        assertEquals(buscado.getId(), guardado.getId());

    }

    @Test
    @Order(3)
    void eliminarPaciente() {

        Long id  = guardado.getId();

        pacienteService.eliminarPaciente(id);

        guardado = pacienteService.buscarPaciente(id);

        assertNull(guardado);
    }

}