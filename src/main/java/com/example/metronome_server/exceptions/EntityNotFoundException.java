package com.example.metronome_server.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){}

    public EntityNotFoundException(String message){
        super(message);
    }
}
