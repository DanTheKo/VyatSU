package org.example;

public class Example {
    public  static void main(String [] args){
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++){
                System.out.println(i);
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e ){
                    e.printStackTrace();
                }
            }
        });

        t.start();
        try {
            t.join();
        }catch (InterruptedException  e){
            throw new RuntimeException();
        }
    }
}
