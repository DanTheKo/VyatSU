package org.example;

public class Human implements Contestant {

    private String name;
    private int jumpHeight;
    private int maxRunDist;
    private int superRunCharges = 1;
    public Human(String name, int jumpHeight, int maxRunDist){
        this.name = name;
        this.jumpHeight = jumpHeight;
        this.maxRunDist = maxRunDist;
        this.superRunCharges = 1;
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean superRun(){
        superRunCharges--;
        return true;
    }
    @Override
    public boolean jump(int high) {

        return high <= jumpHeight;
    }

    @Override
    public boolean run(int dist) {
        if(superRunCharges > 0 & dist > jumpHeight){
            System.out.print("Супер ");
            return superRun();
        }
        return dist <= maxRunDist;
    }
}
