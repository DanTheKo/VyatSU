package org.example.indiv;

import org.example.Animal;
import org.example.Tiger;

public class Hare extends RegionalAnimal{
    public Hare(String Name, int Age) {
        super(Name, Age);
        this.maxRunDist = 200;
        this.maxSwimDist = 15;
        this.dangerClass = Tiger.class;
    }
}
