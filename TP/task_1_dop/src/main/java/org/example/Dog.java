package org.example;

public class Dog extends Animal{
    public static int count;
    public Dog(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 500;
        this.maxSwimDist = 10;
        count++;
    }
}
