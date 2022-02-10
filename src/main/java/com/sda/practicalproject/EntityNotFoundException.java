package com.sda.practicalproject;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(){
        super("Entity not found!");
    }
}
