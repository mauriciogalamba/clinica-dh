package com.mauricio.clinica.service;

import com.mauricio.clinica.model.TurnoDTO;

import java.util.Collection;

public interface ITurnoService {
    public TurnoDTO crearTurno(TurnoDTO turno);
    TurnoDTO buscarTurno(Long id);
    public TurnoDTO editarTurno(TurnoDTO turno);
    public boolean eliminarTurno(Long id);
    Collection<TurnoDTO> listarTurnos();
}
