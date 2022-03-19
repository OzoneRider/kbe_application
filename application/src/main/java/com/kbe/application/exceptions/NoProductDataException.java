package com.kbe.application.exceptions;

public class NoProductDataException extends RuntimeException{

    public NoProductDataException(){
        super("No Product Data found ");
    }
}
