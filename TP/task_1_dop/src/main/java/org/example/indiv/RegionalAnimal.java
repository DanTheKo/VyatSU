package org.example.indiv;

import org.example.Animal;
import org.example.Tiger;

public abstract class RegionalAnimal extends Animal {

    Class dangerClass;
    public RegionalAnimal(String Name, int Age){
        super(Name, Age);
    }

    public void dangerous(Animal dangerAnimal) {
        if(dangerAnimal.getClass() == dangerClass){
            System.out.println("Для " + this.getName() +" "+ dangerAnimal.getName() + " представляет опастность");
        }
    }
}
