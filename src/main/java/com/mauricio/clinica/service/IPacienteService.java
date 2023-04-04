package com.mauricio.clinica.service;
import com.mauricio.clinica.model.PacienteDTO;

import java.util.Collection;

public interface IPacienteService {
    public PacienteDTO crearPaciente(PacienteDTO paciente);
    PacienteDTO buscarPaciente(Long id);
    public PacienteDTO editarPaciente(PacienteDTO paciente);
    public boolean eliminarPaciente(Long id);
    Collection<PacienteDTO> listarPacientes();
}
