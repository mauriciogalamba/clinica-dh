package com.mauricio.clinica.model;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class TurnoDTO implements Serializable {
    private Long id;
    private Date fecha;
    private Odontologo odontologo;
    private Paciente paciente;
}
