package com.sda.practicalproject;

public class InvalidBookTitleException extends Exception{

    public InvalidBookTitleException(){
        super("Invalid book!");
    }
}
