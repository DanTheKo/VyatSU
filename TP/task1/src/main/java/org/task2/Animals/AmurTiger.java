package org.task2.Animals;

public class AmurTiger extends RedBookAnimal{
    static final int countInW = 750;
    public AmurTiger(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 1000;
        this.maxSwimDist = 25;
        this.InWorldCount = countInW;
    }
}
