package com.devsuperior.bds02.controllers.exceptions;

import com.devsuperior.bds02.services.exceptions.ResourceDataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resultado não encontrado");
        err.setMessage(e.getMessage());
        err.setPaht(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceDataBaseException.class)
    public ResponseEntity<StandartError> dataBase(ResourceDataBaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError err = new StandartError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Violação do banco de dados");
        err.setMessage(e.getMessage());
        err.setPaht(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
