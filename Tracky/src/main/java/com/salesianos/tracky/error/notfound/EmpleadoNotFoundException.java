package com.salesianos.tracky.error.notfound;

import com.salesianos.tracky.model.Empleado;

public class EmpleadoNotFoundException  extends EntityNotFoundException{

    public EmpleadoNotFoundException() {
        super("Empleado not found");
    }

    public EmpleadoNotFoundException(Long id){
        super("Empleado not found with id "+ id);
    }

    public EmpleadoNotFoundException(String message) {

        super(message);
    }

}
