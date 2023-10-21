package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    public static final Object monitor = new Object();

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private Lock lock = new ReentrantLock();
    private int place = -1;
    private String name;
    private CyclicBarrier cb;
    private CountDownLatch cdl;
    public static List<Car> cars = new ArrayList<>();
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        this.cb = cb;
        this.cdl = cdl;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public void GetWinner(Car c) {
        synchronized (monitor){
            if (cars.size() < 3) {
                cars.add(c);
                c.place = cars.size();
                //System.out.println(c.getName() + " занял " + cars.size() + " место!");
                PrintWinner(c);

            }
        }

    }
    public void PrintWinner(Car c) {
            System.out.println(c.getName() + " занял " + c.place + " место!");
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500);
            //Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);

                if (i < race.getStages().size() - 1)
                {
                    Main.outLock.unlock();
                }
            }
            GetWinner(this);
            Main.outLock.unlock();
            cdl.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}