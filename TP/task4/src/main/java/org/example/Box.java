package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Box<T extends Fruit> {
    protected ArrayList<T> objs;
    public ArrayList<T> getObjectsList(){
        return objs;
    }
    public Box(T ... objs) {
        this.objs = new ArrayList<T>(Arrays.asList(objs));
    }
    public void AddObject(T obj) {
        System.out.print("Объект " + obj.getName() +" добавлен в коробку " + this+" ");
        objs.add(obj);
        PrintBox();
    }

    public void TransferObject(Box<T>box) {
        if (box != this){
        System.out.println("Фрукты переложены");
        box.getObjectsList().addAll( objs);
        objs.clear();}
    }
    public void TransferObject(LemonBox box) {
        if (box != this){
            System.out.println("Фрукты переложены");
            box.getObjectsList().addAll((Collection<? extends Lemon>) objs);
            objs.clear();
        }

    }
    double getWeight() {
        double weight = 0;
        if (objs.isEmpty()) {
            return 0;
        }
        else {
            for (Fruit obj : objs) {
                weight += obj.getWeight();
            }
            return weight;
        }
    }
    boolean Compare (Box<?> box){
        return Math.abs(this.getWeight()- box.getWeight()) < 0.0001;
    }
    void PrintBox() {
        if (objs.isEmpty()){
            System.out.println("Коробка пустая");
        }
        else{
            System.out.print("[ ");
            for (T obj : objs) {

                System.out.print(obj + " ");

            }
            System.out.print("]");
            System.out.print(" Вес коробки: " + this.getWeight() + "\n");
        }
    }

}
//    void AddObject(T obj) {
//        if(obj.getClass() == this.objs.getFirst().getClass()){
//            System.out.print("Объект " + obj.getName() +" добавлен в коробку " + this+" ");
//            objs.add(obj);
//            PrintBox();
//        }
//        else {
//            System.out.print("Объект " + obj.getName() +" не добавлен в коробку\n");
//        }
//
//    }

//        {
//        if(objs.getFirst().getClass()== box.objs.getFirst().getClass()){
//            System.out.println("Фрукты переложены");
//            box.getObjectsList().addAll(objs);
//            objs.clear();
//        } else  if(box.objs.getFirst().getClass() == Lemon.class){
//            System.out.println("Фрукты переложены, так как это коробка лимонов");
//            box.getObjectsList().addAll(objs);
//            objs.clear();
//        }
//        else {
//            System.out.println("Фрукты не переложены");
//        }
//    }