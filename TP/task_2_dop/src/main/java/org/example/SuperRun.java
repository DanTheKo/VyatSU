package org.example;

public interface SuperRun {
    default boolean superRun(){
        System.out.print("(Супер) ");
        return true;
    }
}
