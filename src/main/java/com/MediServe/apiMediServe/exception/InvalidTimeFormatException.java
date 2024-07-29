package com.MediServe.apiMediServe.exception;

public class InvalidTimeFormatException extends RuntimeException{
    public InvalidTimeFormatException(){
        super("Formato de hora inválido. Use HH:mm.");
    }
}
