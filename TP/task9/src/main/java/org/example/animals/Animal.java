package org.example.animals;


public abstract class Animal {
    private String name;
    private int age;
    protected int maxRunDist;
    protected int maxSwimDist;
    public static int count;
    public Animal(String Name, int Age){
        this.name = Name;
        this.age = Age;
        count++;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void run(int dist){
        if(dist <= this.maxRunDist){
            System.out.println(name + " пробежал " + dist);
        }
        else {
            System.out.println(name + " не пробежал " + dist);
        }
    }
    public void swim(int dist){
        if(maxSwimDist >0){
            if(dist <= this.maxSwimDist){
                System.out.println(name + " проплыл " + dist);
            }
            else {
                System.out.println(name + " не проплыл " + dist);
            }
        }
        else {
            System.out.println(name + " не умеет плавать");
        }

    }
}
