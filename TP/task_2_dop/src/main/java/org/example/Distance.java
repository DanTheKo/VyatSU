package org.example;

public enum Distance {
    LONG(15), MEDIUM(10), SHORT(5);
    private int dist;
    Distance(int dist){
        this.dist =dist;
    }

    public int getDist() {
        return dist;
    }
}
