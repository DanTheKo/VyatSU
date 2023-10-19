package org.task1;

import org.task1.Animals.*;
import org.task2.Animals.*;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Animal[] animals ={
                new Cat("Jojo", 5),
                new Dog("Tom", 2),
                new Tiger("Rollo", 10),
                new AmurTiger("Tooto", 5)};

        for(Animal a:animals){
            a.run(145);
            a.swim(12);
        }
        ((RedBookAnimal) animals[3]).count();


        RedBookAnimal[] redBookAnimals = {
                new Ermine("Willow", 1),
                new WhiteBear("Snowy", 12),
                new AmurTiger("Sharhan", 15)};


/*
        System.out.println("Количество горностаев в мире: " + redBookAnimals[0].count());
        System.out.println("Количество белых медведей в мире: "+ redBookAnimals[1].count());
        System.out.println("Количество амурских тигров в мире: "+ redBookAnimals[2].count());
*/

        System.out.println();
        System.out.println("Количество котов: "+ Cat.count);
        System.out.println("Количество собак: "+ Dog.count);
        System.out.println("Количество тигров: "+ Tiger.count);
        System.out.println("Всего животных: " + Animal.count);

    }

}
