package org.example;

import java.util.*;

public class Main {
    static final int SIZE = 40_000_003;

    public static void main(String[] args) {
        float[] base = new float[SIZE];
        Arrays.fill(base, 1);
        float[] one = Calculate(base);
//        float[] splited2 = SplitInThreads(2, base);
        float[] splited4 = SplitInThreads(14, base);
        //System.out.println(Arrays.equals(splited2, one));


    }
    static float[] Calculate(float[] array){
        long start = System.currentTimeMillis();
        float[] newArray = new float[array.length];
        System.arraycopy( array, 0, newArray, 0, array.length );
        for (int i = 0; i< newArray.length; i++){
            newArray[i] = (float) (newArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();
        System.out.println( "Single: " + (end - start));
        System.out.println(newArray[newArray.length - 1]);
        return newArray;
    }
    static float[] Calculate(float[] array, int step){
        float[] newArray = array;
        for (int i = 0; i<newArray.length; i++){
            int newI = i + step;
            newArray[i] = (float) (newArray[i] * Math.sin(0.2f + newI / 5) * Math.cos(0.2f + newI / 5)
                    * Math.cos(0.4f + newI / 2));
        }
        return newArray;
    }
    static float[] SplitInThreads(int splits, float[] array){
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[splits];
        float[][] floats = new float[splits][array.length/splits];
        floats[floats.length-1] = new float[array.length/splits + array.length%splits];
        for (int i = 0; i<splits; i++){
            int step = 0;
            for(int j = 0; j<i; j++){
                step += floats[j].length;
            }
            if(i==splits-1){System.arraycopy(array, step, floats[i], 0, floats[i].length);}
            else {System.arraycopy(array, step, floats[i], 0, floats[i].length);}
            System.out.println(floats[i].length);
        }
        for (int i =0; i < splits; i++){
            int finalI = i;
            threads[i] = new Thread(() -> {
                int step = 0;
                for(int j = 0; j<finalI; j++){
                    step += floats[j].length;
                }
                Calculate(floats[finalI], step);
            });
        }
        for (Thread thread: threads){
            thread.start();
        }
        for (Thread thread: threads){
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        float[] resultArray = new float[0];
        for (int i =0; i < splits; i++){
            resultArray = CombineArray(resultArray, floats[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("Threads: " +splits + "; Millis: " +(end - start));
        System.out.println(resultArray[resultArray.length-1]);
        return resultArray;

    }
    static float[] CombineArray(float[] first, float[] second){
        float[] resultArray = new float[first.length + second.length];
        System.arraycopy(first, 0, resultArray, 0, first.length);
        System.arraycopy(second, 0, resultArray, first.length, second.length);
        return resultArray;
    }
}
