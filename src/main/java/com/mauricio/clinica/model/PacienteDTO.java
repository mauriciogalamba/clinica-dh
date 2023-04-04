package com.mauricio.clinica.model;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class PacienteDTO implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fecha_ingreso;
}
