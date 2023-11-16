package org.example.animals;

public abstract class RedBookAnimal extends Animal {
    protected int InWorldCount;

    public RedBookAnimal(String Name, int Age) {
        super(Name, Age);
    }
    public void count(){
        System.out.println(InWorldCount);
    }

}
