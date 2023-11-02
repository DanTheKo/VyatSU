package org.example;

public class Robot implements Contestant, SuperRun{
    private String name;
    private int jumpHeight;
    private int maxRunDist;

    private static int superRunCharges = 2;
    public Robot(String name, int jumpHeight, int maxRunDist){
        this.name = name;
        this.jumpHeight = jumpHeight;
        this.maxRunDist = maxRunDist;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean jump(int high) {
        return high <= jumpHeight;
    }

//    @Override
//    public boolean superRun() {
//        if(superRunCharges > 0){
//            System.out.print("(Супер) ");
//            Robot.superRunCharges--;
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean run(int dist) {
        if(dist > maxRunDist){
            if(superRunCharges > 0){
                superRunCharges--;
                return superRun();
            }
        }
        return dist <= maxRunDist;
    }
}
