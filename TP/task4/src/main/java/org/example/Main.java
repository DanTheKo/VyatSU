package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        System.out.println("(1)_____________________");
//        Integer[] integers = {5, 234, 9, 77,1};
//        String[] strings = {"Java", "C#", "C++","JavaScript","Python"};
//        System.out.println(Arrays.toString(integers));
//        SwapElements(integers, 1,3);
//        System.out.println(Arrays.toString(integers));
//
//        System.out.println(Arrays.toString(strings));
//        SwapElements(strings, 0,3);
//        System.out.println(Arrays.toString(strings));
//
//        System.out.println("(2)_____________________");
//        ArrayList<String> arrayList = ConvertToList(strings);
//        System.out.println(arrayList);



        Box<Apple> appleBox1  = new Box<Apple>(new Apple(),new Apple(),new Apple());
        Box<Apple> appleBox2 = new Box<Apple>(new Apple(),new Apple());
        Box<Orange> orangeBox  = new Box<Orange>(new Orange());
        LemonBox lemonBox  = new LemonBox(new Lemon());


        appleBox2.AddObject(new Apple());
        orangeBox.AddObject(new Orange());
        lemonBox.AddObject(new Lemon());
//        lemonBox.AddObject(new Apple());
//        appleBox2.AddObject(new Lemon());
//        orangeBox.AddObject(new Apple());
//        appleBox2.AddObject(new Orange());
        appleBox2.TransferObject(appleBox1);
//        appleBox1.TransferObject(orangeBox);
       // appleBox1.TransferObject(orangeBox);
        orangeBox.TransferObject(lemonBox);
        lemonBox.PrintBox();
        lemonBox.TransferObject(lemonBox);
        lemonBox.PrintBox();

        //System.out.println(appleBox2.Compare(orangeBox));
//        appleBox2.AddObject(new Apple());
//        lemonBox.TransferObject(appleBox1);
//        lemonBox.AddObject(new Apple());
//        lemonBox.PrintBox();
    }

    private static <T> void SwapElements(T[] array, int a, int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    private static <V> ArrayList<V> ConvertToList(V[] array){
        return new ArrayList<>(Arrays.asList(array));
    }
}
