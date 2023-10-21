package org.example;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            Main.outLock.lock();
            System.out.println(c.getName() + " начал этап: " + description);
            Main.outLock.unlock();
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            Main.outLock.lock();
            System.out.println(c.getName() + " закончил этап: " + description);
        }
    }
}
