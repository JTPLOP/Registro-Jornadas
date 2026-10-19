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

    private LocalDateTime fechaAccion;

    @Enumerated(EnumType.STRING)
    private TipoFichaje tipoFichaje;



}
