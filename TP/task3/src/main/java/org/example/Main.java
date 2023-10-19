// then press Enter. You can now see whitespace characters in your code.
package org.example;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello and welcome!");

        String[][] matrix = {
                {"3","dead inside","3","3"},
                {"3","3","3","3"},
                {"3","16","3","3"},
                {"3","3","16","4"}
        };
        CheckMassive(matrix);
    }

    public static int[]  CentralNumber(int n){
        int[] centralNumbers = new int[n];
        for(int i = 0; i < n; i++){
            centralNumbers[i] = i*(i+1)/2 + 1;
        }
        return centralNumbers;
    }

    public static boolean EqualsNumberAtMassive(int[] array, int number){
        for(int i = 0; i < array.length; i++){
            if(number == array[i]){
                return true;
            }
        }
        return false;
    }
    public static void CheckMassive(String[][] array){
        if(!CheckRowsColumns(4, 4, array)){return;};
        //CheckRowsColumns(4, 4, array);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    try {
                        int number = Integer.parseInt(array[i][j]);
                        sum += number;
                    }
                    catch (NumberFormatException e){
                        throw new MyArrayDataException(i,j, array);
                    }

                }
                catch (MyArrayDataException e){
                    e.printStackTrace();
                    return;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]);
                    int[] intArray = CentralNumber(10);
                    if(EqualsNumberAtMassive(intArray, number)){
                        throw new MyCentralException(i, j, array);
                    }

                }
                catch (MyCentralException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Cумма чисел: "+sum);
    }

    public static boolean  CheckRowsColumns(int rows, int columns, String[][] array){
        boolean b = true;
        for (int i = 0; i < array.length; i++) {
            try {
                if(array.length != rows || array[i].length != columns){
                    throw new MyArraySizeException(rows, columns);
                }
            }
            catch (MyArraySizeException e){
                e.printStackTrace();
                b = false;

            }

        }
        return b;
    }
}


