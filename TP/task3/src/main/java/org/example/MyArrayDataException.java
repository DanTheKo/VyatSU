package org.example;

public class MyArrayDataException extends MyException{
    public MyArrayDataException(int row, int column, String[][] array){
        super("Недопустимые данные в строке " + (row+1) + " столбце " + (column+1) + ", " + array[row][column]);
    }
}
