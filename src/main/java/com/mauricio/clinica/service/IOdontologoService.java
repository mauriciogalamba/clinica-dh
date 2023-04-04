package com.mauricio.clinica.service;

import com.mauricio.clinica.model.OdontologoDTO;

import java.util.Collection;

public interface IOdontologoService {
    public OdontologoDTO crearOdontologo(OdontologoDTO odontologo);
    OdontologoDTO buscarOdontologo(Long id);
    public OdontologoDTO editarOdontologo(OdontologoDTO odontologo);
    public boolean eliminarOdontologo(Long id);
    Collection<OdontologoDTO> listarOdontologos();
}
