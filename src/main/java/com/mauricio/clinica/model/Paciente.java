package com.mauricio.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable {
    @Id
    @SequenceGenerator(name = "paciente_seq", sequenceName = "paciente_seq")
    @GeneratedValue(generator = "paciente_seq")
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fecha_ingreso;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Domicilio> domicilios;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
