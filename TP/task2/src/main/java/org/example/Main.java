package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Contestant[] contestants = new Contestant[]{
                new Human("Bob", 6, 6),
                new Human("Todd", 6, 6),
                new Cat("Murzik", 15, 4),
                new Robot("Bzzt", 4, 16)
        };
        Obstacle[] obstacles = new Obstacle[]{
                new Treadmill(Distance.SHORT),
                new Treadmill(Distance.LONG),
                new Treadmill(Distance.LONG),
                new Wall(Height.LOW),
                new Treadmill(Distance.MEDIUM)
        };
        for (Contestant contestant : contestants) {
            for (Obstacle obstacle : obstacles) {
                if (!obstacle.PassObstacle(contestant)) {
                    break;
                }
            }
        }
    }
}