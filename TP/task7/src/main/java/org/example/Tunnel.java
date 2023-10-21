package org.example;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore semaphore = new Semaphore(4);
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {

            try {
                Main.outLock.lock();
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                Main.outLock.unlock();
                semaphore.acquire();
                Main.outLock.lock();
                System.out.println(c.getName() + " начал этап: " + description);
                Main.outLock.unlock();
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                Main.outLock.lock();
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
                synchronized (Car.monitor){

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
