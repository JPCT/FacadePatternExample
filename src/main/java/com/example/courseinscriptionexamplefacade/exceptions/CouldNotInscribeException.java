package com.example.courseinscriptionexamplefacade.exceptions;

public class CouldNotInscribeException extends Exception {
    public CouldNotInscribeException(String errorMessage){
        super(errorMessage);
    }
}
