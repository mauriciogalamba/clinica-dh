package com.mauricio.clinica.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="turnos")
public class Turno implements Serializable {
    @Id
    @SequenceGenerator(name = "turnos_seq", sequenceName = "turnos_seq")
    @GeneratedValue(generator = "turnos_seq")
    private Long id;
    private Date fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
