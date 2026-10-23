package com.salesianos.tracky.error;

public class FichajeDuplicadoException extends RuntimeException{

    public FichajeDuplicadoException(String message) {
        super(message);
    }

    public FichajeDuplicadoException(Long idEmpleado, String tipo) {
        super(String.format("El empleado con ID %d ya registró un fichaje consecutivo de tipo %s", idEmpleado, tipo));
    }

}
