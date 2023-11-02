package org.example;


import org.example.indiv.Beaver;
import org.example.indiv.Hare;
import org.example.indiv.Moose;
import org.example.indiv.RegionalAnimal;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Animal[] animals ={
                new Cat("Jojo", 5),
                new Dog("Tom", 2),
                new Tiger("Rollo", 10),
                new Moose("Лось", 5),
                new Hare("Зайчишка", 3),
                new Beaver("Бобр", 1)
        };

        for(Animal a:animals){
            a.run(145);
            a.swim(12);
        }
        ((RegionalAnimal) animals[5]).dangerous(new Tiger("Тигрыч", 10));

//        RegionalAnimal[] regionalAnimals ={
//                new Moose("Лось", 5),
//                new Hare("Зайчишка", 3),
//                new Beaver("Бобр", 1)
//        };
//
//
//        for(RegionalAnimal a: regionalAnimals){
//            a.dangerous(new Tiger("Тигрыч", 10));
//        }
//        for(RegionalAnimal a: regionalAnimals){
//            a.dangerous(new Cat("Кот", 10));
//        }
//        System.out.println();
//        System.out.println("Количество котов: "+ Cat.count);
//        System.out.println("Количество собак: "+ Dog.count);
//        System.out.println("Количество тигров: "+ Tiger.count);
//        System.out.println("Всего животных: " + Animal.count);

    }

}
