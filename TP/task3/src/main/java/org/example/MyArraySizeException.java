package org.example;

public class MyArraySizeException extends MyException{
    public MyArraySizeException(int rows, int columns){
        super("Размер массива должен быть " + rows + "x" + columns);
    }
}
