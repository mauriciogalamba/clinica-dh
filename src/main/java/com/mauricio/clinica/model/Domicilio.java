package com.mauricio.clinica.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="domicilios")
public class Domicilio implements Serializable {
    @Id
    @SequenceGenerator(  name = "domicilio_seq", sequenceName = "domicilio_seq")
    @GeneratedValue(generator = "domicilio_seq")
    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
