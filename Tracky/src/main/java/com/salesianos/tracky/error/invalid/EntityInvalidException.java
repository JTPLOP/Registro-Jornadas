package com.salesianos.tracky.error.invalid;

public class EntityInvalidException extends RuntimeException{

    public EntityInvalidException (String message) {
        super(message);
    }

}
