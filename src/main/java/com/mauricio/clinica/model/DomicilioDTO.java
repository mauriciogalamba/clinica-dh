package com.mauricio.clinica.model;
import lombok.Data;
import java.io.Serializable;

@Data
public class DomicilioDTO implements Serializable {
    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private Paciente paciente;
}
