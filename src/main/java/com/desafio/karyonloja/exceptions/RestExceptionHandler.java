package com.desafio.karyonloja.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleConstraintValidation(ConstraintViolationException ex){
        ErrorDetails error = createErrorDatails(ex) ;
        error.setTitle("Erro de validação");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<?> handDataIntegrityViolationException(DataIntegrityViolationException ex){
        ErrorDetails error =  createErrorDatails(ex);
        error.setTitle("Violação de integridade de dados");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<?> handleStoreNotFound(StoreNotFoundException ex){
        ErrorDetails error = createErrorDatails(ex) ;
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTitle("Loja não foi encontrada!");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<?> handleStoreNotFound(CompanyNotFoundException ex){
        ErrorDetails error = createErrorDatails(ex);
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTitle("Empresa não encontrada!");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    private ErrorDetails createErrorDatails(Exception ex){
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(new Date().getTime());
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());
        return error;
    }

}
