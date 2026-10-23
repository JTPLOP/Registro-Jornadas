package com.salesianos.tracky.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PresupuestoSuficienteValidator.class)
@Documented
public @interface PresupuestoSuficiente {

    String message() default "Presupuesto insuficiente para todos los empleados.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
