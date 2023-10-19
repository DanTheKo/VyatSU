package org.example;

public abstract class Fruit{
    protected String name;
    private double weight;
    public String getName(){
        return name;
    }
    public Fruit(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

}
