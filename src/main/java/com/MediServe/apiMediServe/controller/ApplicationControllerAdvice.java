package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.exception.EmailAlreadyExists;
import com.MediServe.apiMediServe.exception.InvalidTimeFormatException;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleNotFoundExecption(RecordNotFoundException ex){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(errors);
    }

<<<<<<< HEAD
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        String message = "Erro de integridade de dados.";

        if (ex.getMessage().contains("email")) {
            message = "O e-mail já está em uso. Por favor, escolha outro e-mail.";
        } else if (ex.getMessage().contains("name")) {
            message = "O nome já está em uso. Por favor, escolha outro nome.";
        }

        return new ApiErros(message);
=======
    @ExceptionHandler(EmailAlreadyExists.class)
    @ResponseStatus()
    public ApiErros handleEmailAlreadyExists(EmailAlreadyExists ex){
        return new ApiErros(ex.getMessage());
>>>>>>> master
    }

    @ExceptionHandler(InvalidTimeFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleTimeFormatException(InvalidTimeFormatException ex){
        return  new ApiErros(ex.getMessage());
    }
}
