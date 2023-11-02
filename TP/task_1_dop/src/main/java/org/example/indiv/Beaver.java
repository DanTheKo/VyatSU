package org.example.indiv;

import org.example.Animal;
import org.example.Tiger;

public class Beaver extends RegionalAnimal{
    public Beaver(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 500;
        this.maxSwimDist = 1000;
        this.dangerClass = Tiger.class;
    }
}
