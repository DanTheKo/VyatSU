package org.example;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TripService {
    private List<Trip> trips = new ArrayList<>();

    @PostConstruct
    private void init() {
        trips.add(new Trip(1, LocalDate.of(2023, 1, 15), "Москва", "Киров"));
        trips.add(new Trip(2, LocalDate.of(2023, 2, 16), "Якутск", "Казань"));
        trips.add(new Trip(3, LocalDate.of(2023, 3, 17), "Киров", "Москва"));
        trips.add(new Trip(4, LocalDate.of(2023, 4, 18), "Сочи", "Пермь"));
        trips.add(new Trip(5, LocalDate.of(2023, 5, 19), "Сеул", "Токио"));
        trips.add(new Trip(6, LocalDate.of(2023, 6, 20), "Пекин", "Токио"));
        trips.add(new Trip(7, LocalDate.of(2023, 7, 21), "Москва", "Париж"));
        trips.add(new Trip(8, LocalDate.of(2023, 8, 22), "Тегеран", "Москва"));
        trips.add(new Trip(9, LocalDate.of(2023, 9, 23), "Москва", "Берлин"));
        trips.add(new Trip(10, LocalDate.of(2023, 10, 24), "Варшава", "Рим"));
    }

    public Trip find(String date, String arrival) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date2 = LocalDate.parse(date, formatter);
        for (Trip trip : trips) {
            if (trip.getDate().equals(date2) && trip.getArrival().equals(arrival)) {
                return trip;
            }
        }
        return null;
    }

    public void printAll() {
        System.out.println("Список всех туров: ");
        for (Trip trip : trips) {
            System.out.println(trip);
        }
        System.out.println();
    }
}

