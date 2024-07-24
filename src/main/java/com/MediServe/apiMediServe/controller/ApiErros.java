package com.MediServe.apiMediServe.controller;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErros {
    private List<String> errors;

    public ApiErros(List<String> errors){
        this.errors = errors;
    }

    public ApiErros(String messageErrors){
        this.errors = Arrays.asList(messageErrors);
    }
}
