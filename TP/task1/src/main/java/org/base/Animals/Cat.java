package org.base.Animals;

public class Cat extends Animal{
    public static int count;

    public Cat(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 200;
        this.maxSwimDist = 0;
        count++;
    }
}
