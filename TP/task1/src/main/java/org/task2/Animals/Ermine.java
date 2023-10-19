package org.task2.Animals;

public class Ermine extends RedBookAnimal{

    static final int countInW = 900000;
    public Ermine(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 250;
        this.maxSwimDist = 15;
        this.InWorldCount = countInW;
    }
}
