package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final int CARS_COUNT = 5;

    public static Lock outLock = new ReentrantLock();

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT+1);
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        //Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Race race = new Race(new Road(60));
        Car[] cars = new Car[CARS_COUNT];
        Object monitor = new Object();

        for (int i = 0; i < cars.length; i++) {
            //cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, cdl);
            cars[i] = new Car(race, 25, cb, cdl);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cb.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            cdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}




