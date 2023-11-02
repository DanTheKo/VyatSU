package org.example;

public class Cat implements Contestant {
    private String name;
    private int jumpHeight;
    private int maxRunDist;
    public Cat(String name, int jumpHeight, int maxRunDist){
        this.name = name;
        this.jumpHeight = jumpHeight;
        this.maxRunDist = maxRunDist;
    }
    @Override
    public boolean jump(int high) {
        return high <= jumpHeight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean run(int dist) {
        return dist <= maxRunDist;
    }
}
