package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TravelersCard {
    public ArrayList<Trip> trips = new ArrayList<>();
    //private LocalDate dateOfRegistration;
    public void addToCard(Request request) {
        if(!request.getTrips().isEmpty()) {
            trips.addAll(request.getTrips());
            System.out.println("Дорожная карта");
            for (Trip trip : trips) System.out.println(trip.toString());
        }
        else {
            System.out.println("Туров в заявке нет");
        }

    }
    public ArrayList<Trip> getTrips() {
        return trips;
    }

//    public LocalDate getDateOfRegistration() {
//        return dateOfRegistration;
//    }
//
//    public void setDateOfRegistration(LocalDate dateOfRegistration) {
//        this.dateOfRegistration = dateOfRegistration;
//    }
}