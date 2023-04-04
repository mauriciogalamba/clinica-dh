package com.mauricio.clinica.repository;

import com.mauricio.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
