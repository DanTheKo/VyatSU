package org.example;

public class Wall implements Obstacle {

    Height height;

    public Wall(Height height){
        this.height = height;
    }
    @Override
    public boolean PassObstacle(Contestant contestant) {
        if(contestant.jump(this.height.getHeight())){
            System.out.println(contestant.getName() + " перепрыгнул");
            return true;
        }
        else {
            System.out.println(contestant.getName() + " не перепрыгнул");
            return false;
        }
    }

}
