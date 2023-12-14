package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TravelService travelService = context.getBean("travelService", TravelService.class);

        travelService.printTrips();

        travelService.order("2023-05-19", "Токио");
        travelService.order("2023-10-24", "Рим");
        travelService.order("2023-09-23", "Берлин");



    }
}