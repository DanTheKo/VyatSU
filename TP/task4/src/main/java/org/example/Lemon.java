package org.example;

public class Lemon extends  Fruit {
    protected Lemon(double weight) {
        super(weight);
    }
    public Lemon() {
        super(0.5d);
        name = "Лимон";
    }
}
