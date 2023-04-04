package com.mauricio.clinica.model;
import lombok.Data;
import java.io.Serializable;


@Data
public class OdontologoDTO implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private Long matricula;


}
