package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Request {
    private List<Trip> trips = new ArrayList<>();
    private LocalDate dateOfRegistration;

    @Autowired
    private TripService tripService;

    public boolean addTrip(String date, String arrival) {
        dateOfRegistration = LocalDate.now();
        Trip trip = tripService.find(date, arrival);
        if (trip != null && trip.getArrival().equals(arrival) && trip.getDate().toString().equals(date)) {
            trips.add(trip);
            System.out.println(trip + " добавлен в заявку");
            System.out.println("Дата оформления заявки: " + this.dateOfRegistration+ "\n");
            return true;
        }
        else {
            System.out.println("Такого тура не существует");
            return false;
        }
    }

        public void printAllTrips() {
        tripService.printAll();
        }


    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }
}