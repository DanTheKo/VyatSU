package org.example;

public class Treadmill implements Obstacle {
    Distance distance;

    public Treadmill(Distance distance){
        this.distance = distance;
    }
    @Override
    public boolean PassObstacle(Contestant contestant) {
        if(contestant.run(this.distance.getDist())){
            System.out.println(contestant.getName() + " пробежал");
            return true;
        }
        else {
            System.out.println(contestant.getName() + " не пробежал");
            return false;
        }
    }
}
