package org.example;

public abstract class MyException extends RuntimeException{

    public MyException(String message){
        super(message);
    };
}
