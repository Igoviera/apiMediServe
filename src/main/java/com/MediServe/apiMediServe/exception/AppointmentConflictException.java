package com.MediServe.apiMediServe.exception;

public class AppointmentConflictException extends RuntimeException{
    public AppointmentConflictException(String message){
        super(message);
    }
}
