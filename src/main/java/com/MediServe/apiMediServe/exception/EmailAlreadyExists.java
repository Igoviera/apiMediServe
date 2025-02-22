package com.MediServe.apiMediServe.exception;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists(){super("E-mail já cadastrado");}
}
