package com.mauricio.clinica.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="odontologos")
public class Odontologo implements Serializable {
    @Id
    @SequenceGenerator(name = "odontologo_seq", sequenceName = "odontologo_seq")
    @GeneratedValue(generator = "odontologo_seq")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Long matricula;
    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;

}
