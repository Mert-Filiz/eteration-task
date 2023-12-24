package com.eteration.simplebanking.model.exceptions;

public class InsufficientAmountException extends RuntimeException{
    public InsufficientAmountException(String message){super(message);}
}
