package com.salesianos.tracky.error.notfound;

public class DepartamentoNotFoundException extends EntityNotFoundException{

    public DepartamentoNotFoundException () {super("Department not found");}


    public DepartamentoNotFoundException (Long id) {super("Department not found with id:" + id);}

    public DepartamentoNotFoundException (String message) {super(message);}
}
