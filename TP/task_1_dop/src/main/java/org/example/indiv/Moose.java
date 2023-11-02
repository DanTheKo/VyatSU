package org.example.indiv;

import org.example.Animal;
import org.example.Tiger;

public class Moose extends RegionalAnimal{
    public Moose(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 1000;
        this.maxSwimDist = 100;
        this.dangerClass = Tiger.class;
    }

}
