package org.indiv;

public class WhiteBear extends RedBookAnimal{

    static final int countInW = 26000;
    public WhiteBear(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 1000;
        this.maxSwimDist = 100;
        this.InWorldCount = countInW;
    }
}
