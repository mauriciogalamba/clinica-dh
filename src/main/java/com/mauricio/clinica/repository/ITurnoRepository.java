package com.mauricio.clinica.repository;

import com.mauricio.clinica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository <Turno,Long> {
}
