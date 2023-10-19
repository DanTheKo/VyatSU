package org.example;

public enum Height {
    HIGH(15), MEDIUM(10), LOW(5);
    private int height;
    Height(int height){
        this.height =height;
    }

    public int getHeight() {
        return height;
    }
}
