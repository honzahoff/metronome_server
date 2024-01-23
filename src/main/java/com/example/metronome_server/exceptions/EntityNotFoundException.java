package com.example.metronome_server.exceptions;

/**
 * Custom EntityNotFoundException
 * - is thrown when entity has not been found
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){}

    public EntityNotFoundException(String message){
        super(message);
    }
}
