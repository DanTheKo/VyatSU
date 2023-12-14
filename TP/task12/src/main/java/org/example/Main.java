package org.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.PessimisticLockException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static final int rows = 40;
    private static final int threadsCount = 8;
    private static final int iterations = 8000 / threadsCount;
    private static final Random random = new Random();
    private static final Configuration configuration = new Configuration();
    private static SessionFactory factory;
    private static int rollbacksCount;
    private static String temp = "";

    public static void main(String[] args) throws InterruptedException {
        configuration.addAnnotatedClass(Item.class).configure("hibernate.cfg.xml").buildSessionFactory();
        factory = configuration.buildSessionFactory();

        fillItems();

        CountDownLatch countDownLatch = new CountDownLatch(threadsCount);
        ThreadsRun(0, countDownLatch);

        countDownLatch = new CountDownLatch(threadsCount);
        ThreadsRun(1, countDownLatch);

        countDownLatch = new CountDownLatch(threadsCount);
        ThreadsRun(2, countDownLatch);

        System.out.println(temp);
    }

    private static void ThreadsRun(int type, CountDownLatch countDownLatch) throws InterruptedException {

        rollbacksCount = 0;

        clearItems();

        long start = System.currentTimeMillis();
        if (type == 0) {
            for (int i = 0; i < threadsCount; i++) {
                new Thread(() -> incrementValuesPessimistic(countDownLatch)).start();
            }
        } else if (type == 1) {
            for (int i = 0; i < threadsCount; i++) {
                new Thread(() -> incrementValuesOptimistic(countDownLatch)).start();
            }
        } else if (type == 2) {
            for (int i = 0; i < threadsCount; i++) {
                new Thread(() -> incrementValuesNew(countDownLatch)).start();
            }
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();

        if (type == 0) {
            temp += "Пессимистичная-----------------\nЗатраченное время: " + ((double) (end - start) / 1000) + " сек.\n";
            resultPessimistic();
        } else if (type == 1) {
            temp += "Оптимистичная-----------------\nЗатраченное время: " + ((double) (end - start) / 1000) + " сек.\n";
            resultOptimistic();
        } else if (type == 2) {
            temp += "Оптимистичная без version-----------------\nЗатраченное время: " + ((double) (end - start) / 1000) + " сек.\n";
            resultOptimistic();
        }

    }


    private static void clearItems() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            for (long i = 1; i < rows + 1; i++) {
                Item item = session.get(Item.class, i);
                item.setValue(0);
                item.setValueNew(0);
                session.save(item);
            }
            session.getTransaction().commit();
        }
    }

    private static void fillItems() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (int i = 0; i < rows; i++) {
                session.save(new Item());
            }
            session.getTransaction().commit();
        }
    }

    private static void incrementValuesPessimistic(CountDownLatch countDownLatch) {

        for (int i = 0; i < iterations; i++) {
            Session session = factory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            long randomId = random.nextLong(40) + 1;

            Item item = session.get(Item.class, randomId, LockMode.PESSIMISTIC_WRITE);
            item.increment();

            try {
                Thread.sleep(3);
                transaction.commit();

            } catch (PessimisticLockException e) {
                transaction.rollback();
                rollbacksCount++;
            } catch (InterruptedException e) {
                transaction.rollback();
            }
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            randomId = random.nextLong(40) + 1;


            item = session.get(Item.class, randomId, LockMode.PESSIMISTIC_WRITE);
            item.increment2();

            try {
                transaction.commit();

            } catch (PessimisticLockException e) {
                transaction.rollback();
                rollbacksCount++;
            }
        }
        countDownLatch.countDown();
    }

    private static void incrementValuesOptimistic(CountDownLatch countDownLatch) {
        long randomId = random.nextLong(40) + 1;
        boolean success = false;

        for (int i = 0; i < iterations; i++) {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            if (success) {
                randomId = random.nextLong(40) + 1;
            }

            Item item = session.get(Item.class, randomId);
            item.increment();

            try {
                Thread.sleep(3);
                session.save(item);
                success = true;
                session.getTransaction().commit();

            } catch (Exception e) {
                rollbacksCount++;
                success = false;
                i--;
                session.getTransaction().rollback();
            }
        }
        countDownLatch.countDown();

    }

    private static void incrementValuesNew(CountDownLatch countDownLatch) {
        for (int i = 0; i < iterations; i++) {
            Session session = factory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            long randomId = random.nextLong(40) + 1;

            Item item = session.get(Item.class, randomId, LockMode.PESSIMISTIC_WRITE);
            item.increment2();

            try {
                Thread.sleep(3);
                transaction.commit();

            } catch (PessimisticLockException e) {
                transaction.rollback();
                rollbacksCount++;
            } catch (InterruptedException e) {
                transaction.rollback();
            }
        }
        countDownLatch.countDown();

    }

    private static void resultPessimistic() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        int sum = session.createQuery("FROM Item", Item.class)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultStream()
                .mapToInt(Item::getValue)
                .sum();
        session.getTransaction();

        int sum2 = session.createQuery("FROM Item", Item.class)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultStream()
                .mapToInt(Item::getValueNew)
                .sum();
        session.getTransaction().commit();
        temp += "Количество откатов: " + rollbacksCount +
                "\nНеобходимая сумма: " + (iterations * threadsCount) +
                "\nПолученная сумма: " + sum + "\nПолученная сумма 2: " + sum2 + "\n\n";

    }

    private static void resultOptimistic() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        long sum = (long) session.createQuery("SELECT SUM(value) FROM Item")
                .getSingleResult();
        session.getTransaction();
        long sum2 = (long) session.createQuery("SELECT SUM(valueNew) FROM Item")
                .getSingleResult();
        session.getTransaction().commit();
        temp += "Количество откатов: " + rollbacksCount +
                "\nНеобходимая сумма: " + (iterations * threadsCount) +
                "\nПолученная сумма: " + sum + "\nПолученная сумма 2: " + sum2 + "\n\n";

    }
}