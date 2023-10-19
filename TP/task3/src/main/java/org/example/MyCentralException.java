package org.example;

public class MyCentralException extends MyException{
    public MyCentralException(int row, int column, String[][] array){
        super("Недопустимые данные в строке, центральное многоугольное число в строке " + (row+1) + " столбце " + (column+1) + ", " + array[row][column]);
    }
}
