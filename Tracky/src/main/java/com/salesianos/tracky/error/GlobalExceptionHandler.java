package com.salesianos.tracky.error;


import com.salesianos.tracky.error.notfound.DepartamentoNotFoundException;
import com.salesianos.tracky.error.notfound.EmpleadoNotFoundException;
import com.salesianos.tracky.error.notfound.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ProblemDetail handleEmpleadoNotFound(EntityNotFoundException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Empleado no encontrado");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://example.com/errors/not-found"));

        return problem;
    }


    @ExceptionHandler(DepartamentoNotFoundException.class)
    public ProblemDetail handleDepartamentoNotFound(EntityNotFoundException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Departento no encontrado");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://example.com/errors/not-found"));

        return problem;
    }



}
