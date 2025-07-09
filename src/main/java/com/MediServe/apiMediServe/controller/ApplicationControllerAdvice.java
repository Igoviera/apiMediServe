package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.exception.AppointmentConflictException;
import com.MediServe.apiMediServe.exception.DuplicateException;
import com.MediServe.apiMediServe.exception.EmailAlreadyExists;
//import com.MediServe.apiMediServe.exception.InvalidTimeFormatException;
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
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErros handleDuplicateException(DuplicateException ex){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(AppointmentConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErros handleAppoitmentConclict(AppointmentConflictException ex){
        return new ApiErros(ex.getMessage());
    }

//    @ExceptionHandler(InvalidTimeFormatException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErros handleTimeFormatException(InvalidTimeFormatException ex){
//        return  new ApiErros(ex.getMessage());
//    }

    @ExceptionHandler(EmailAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleEmailAlreadyExistsException(EmailAlreadyExists ex){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ApiErros(ex.getMessage());
    }

}
