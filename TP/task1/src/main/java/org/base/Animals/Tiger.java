package org.base.Animals;

public class Tiger extends Animal{
    public static int count;
    public Tiger(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 700;
        this.maxSwimDist = 15;
        count++;
    }
}
