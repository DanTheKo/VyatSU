package org.indiv;

import org.base.Animals.Animal;

public abstract class RedBookAnimal extends Animal {

    protected int InWorldCount;
    public RedBookAnimal(String Name, int Age) {
        super(Name, Age);
    }


//    public String count(){
//        return String.valueOf(InWorldCount);
//    }
    public void count(){
        System.out.println(InWorldCount);
    }

}
