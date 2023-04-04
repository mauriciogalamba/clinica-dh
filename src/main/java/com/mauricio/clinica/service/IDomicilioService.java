package com.mauricio.clinica.service;

import com.mauricio.clinica.model.DomicilioDTO;

import java.util.Collection;

public interface IDomicilioService {
    public DomicilioDTO crearDomicilio(DomicilioDTO domicilio);
    DomicilioDTO buscarDomicilio(Long id);
    public DomicilioDTO editarDomicilio(DomicilioDTO domicilio);
    public boolean eliminarDomicilio(Long id);
    Collection<DomicilioDTO> listarDomicilios();
}
