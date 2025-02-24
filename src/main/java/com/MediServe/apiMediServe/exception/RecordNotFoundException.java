package com.MediServe.apiMediServe.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(Long id) {
        super("Recurso não encontrado o com id "+ id);
    }

    public RecordNotFoundException(String menssagem){
        super(menssagem);
    }
}
