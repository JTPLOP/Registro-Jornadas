package com.salesianos.tracky.model;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class Fichaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime fechaAccion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TipoFichaje tipoFichaje;


    // ASOCIACION N:1 CON EMPLEADOS

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_employer",
            foreignKey = @ForeignKey (name = "fk_employer_tracker"))
    private Empleado empleado;

    public void addToEmployer (Empleado empleado) {

        this.empleado = empleado;
        empleado.getHistorialFichajes().add(this);
    }

    public void removeToEmployer (Empleado empleado) {

        empleado.getHistorialFichajes().remove(this);
        this.empleado = null;
    }


}
